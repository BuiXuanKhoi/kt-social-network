package com.example.socialnetworkproject.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.socialnetworkproject.services.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

@RestController
@RequestMapping("/api/test")
public class TestController {

    private final Cloudinary cloudinary;
    private final AssetService assetService;

    @Autowired
    public TestController(Cloudinary cloudinary, AssetService assetService) {
        this.cloudinary = cloudinary;
        this.assetService = assetService;
    }

    @PostMapping(path = "/c", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<String> uploadConcuren(@RequestParam("images") MultipartFile[] multipartFiles, HttpServletRequest request) throws IOException, InterruptedException {
        System.out.println("Concurency Controller");
        long startTime = System.nanoTime();
        System.out.println(Arrays.toString(request.getParameterValues("images")));
        ExecutorService executorService = Executors.newFixedThreadPool(multipartFiles.length);
        List<Callable<Void>> tasks = new LinkedList<>();
        List<String> urls = new LinkedList<>();
        for (MultipartFile multipartFile : multipartFiles){
            System.out.println("It's MultipartFile");
            tasks.add(() -> {
                String message = upload(multipartFile);
                urls.add(message);
                return null;
            });
        }

        try {
            executorService.invokeAll(tasks);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        finally {
            executorService.shutdown();
        }

        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        System.out.println("The Execute time in concurency duration is : " + duration );



        return urls;
    }

    @PostMapping(path = "/p")
    public List<String> uploadParallel(@RequestParam("images") MultipartFile[] multipartFiles){
        long startTime = System.nanoTime();
        ExecutorService executorService = Executors.newFixedThreadPool(multipartFiles.length);
        List<Future<Void>> tasks = new LinkedList<>();
        List<String> urls = new LinkedList<>();

        for (MultipartFile multipartFile : multipartFiles){
            Callable<Void> task = () ->{
                System.out.println("Execute upload");
                String url = upload(multipartFile);
                urls.add(url);
                return null;
            };
            tasks.add(executorService.submit(task));
        }

        for (Future<Void> future : tasks){
            try {
                future.get();
            }catch (InterruptedException | ExecutionException ex){
                ex.printStackTrace();
            }finally {
                executorService.shutdown();
            }
        }
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("The Execute time in parallel duration is : " + duration );
        return urls;
    }

    @PostMapping("/j")
    public String uploadOne(@RequestParam(value = "images", required = false) MultipartFile multipartFile) throws IOException {
        return upload(multipartFile);
    }

    private String upload(MultipartFile multipartFile){
        try {
            Map uploadedResult = cloudinary.uploader().upload(multipartFile.getBytes(), ObjectUtils.emptyMap());
            return (String) uploadedResult.get("secure_url");

        }catch (Exception exception){
            exception.printStackTrace();
            System.out.println("Error upload");
            return null;
        }
    }

    @PostMapping("/t")
    public String testUp( @RequestParam("images") MultipartFile multipartFile,  HttpServletRequest httpServletRequest){
        System.out.println(httpServletRequest.getContentType());
        System.out.println(httpServletRequest.getRequestURL());
        System.out.println(httpServletRequest.getParameterMap());
        System.out.println(multipartFile.getSize());
        System.out.println(multipartFile.getOriginalFilename());
        System.out.println(multipartFile.getContentType());
        return "Hello";
    }

    private File convertToFile(MultipartFile bookImage) throws IOException {
        File convertedFile = new File(Objects.requireNonNull(bookImage.getOriginalFilename()));
        FileOutputStream fileOutputStream = new FileOutputStream(convertedFile);
        fileOutputStream.write(bookImage.getBytes());
        fileOutputStream.close();
        return convertedFile;
    }
}

package com.example.socialnetworkproject.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.socialnetworkproject.models.entities.Assets;
import com.example.socialnetworkproject.repositories.AssetRepository;
import com.example.socialnetworkproject.repositories.BaseRepository;
import com.example.socialnetworkproject.services.AssetService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.*;

@Service
public class AssetServiceImpl extends BaseServiceImpl<Assets> implements AssetService {

    private final AssetRepository assetRepository;
    private final Cloudinary cloudinary;


    @Autowired
    public AssetServiceImpl(AssetRepository assetRepository, Cloudinary cloudinary) {
        super(assetRepository);
        this.assetRepository = assetRepository;
        this.cloudinary = cloudinary;
    }

    @Override
    public Assets upload(MultipartFile multipartFile) {

        try {
            Long fileSize = multipartFile.getSize();
            LocalDateTime now = LocalDateTime.now();
            String fileTitle = multipartFile.getOriginalFilename();

            Map uploadResult = cloudinary.uploader().upload(multipartFile, ObjectUtils.emptyMap());
            String url = uploadResult.get("url").toString();

            Assets assets = new Assets();
            assets.setAssetTitle(fileTitle);
            assets.setAssetSize(fileSize + " bytes");
            assets.setAssetUrl(url);
            assets.setAssetUploadDate(now);
            return assets;
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Assets> uploadAll(MultipartFile[] files) {
        int filesCount = files.length;
        ExecutorService executorService = Executors.newFixedThreadPool(filesCount);
        List<Assets> assets = new LinkedList<>();
        List<Future<Void>> futures = new LinkedList<>();

        for (MultipartFile multipartFile : files){
            Callable<Void> task = () -> {
                Assets uploadedAsset = upload(multipartFile);
                assets.add(uploadedAsset);
                return null;
            };
            futures.add(executorService.submit(task));
        }


        for (Future<Void> task : futures){
            try
            {
                task.get();
            }
            catch (InterruptedException intr)
            {
                intr.printStackTrace();
            }
            catch (ExecutionException exe)
            {
                exe.printStackTrace();
            }
        }

        executorService.shutdown();
        return assets;
    }


}

package com.example.socialnetworkproject.controllers;

import com.example.socialnetworkproject.models.entities.Assets;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    @GetMapping
    public List<Assets> retrieveAllAssets(@RequestParam("file")MultipartFile file,
                                          @RequestParam("authorId") Integer authorId,
                                          @RequestParam("fileTitle") String fileTitle
            ){
        return null;
    }
}

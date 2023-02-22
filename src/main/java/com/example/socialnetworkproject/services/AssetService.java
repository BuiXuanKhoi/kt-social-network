package com.example.socialnetworkproject.services;

import com.example.socialnetworkproject.models.entities.Assets;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AssetService extends BaseService<Assets>{

    Assets upload(MultipartFile file);

    List<Assets> uploadAll(MultipartFile[] files);


}

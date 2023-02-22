package com.example.socialnetworkproject.configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {



    @Bean
    public Cloudinary cloudinary(){
        final String API_KEY = "138932813353361";
        final String API_SECRET_KEY = "Qdzg7QfhfANVDNbqrRSk4q1Y0Hk";
        final String CLOUD_NAME = "dnjpnisa1";

        return new  Cloudinary(ObjectUtils.asMap(
                "cloud_name", CLOUD_NAME,
                "api_key", API_KEY,
                "api_secret", API_SECRET_KEY
        ));
    }
}

package com.example.socialnetworkproject.configuration;

import com.example.socialnetworkproject.validation.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidatorConfig {

    @Bean
    public Validator validation(){
        return new Validator();
    }
}

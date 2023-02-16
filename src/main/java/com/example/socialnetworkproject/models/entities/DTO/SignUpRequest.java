package com.example.socialnetworkproject.models.entities.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SignUpRequest {
    private String userName;

    private String password;

    private String email;

    private String firstName;

    private String lastName;

}

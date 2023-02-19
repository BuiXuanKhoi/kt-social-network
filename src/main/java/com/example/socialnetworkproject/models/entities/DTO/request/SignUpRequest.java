package com.example.socialnetworkproject.models.entities.DTO.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Getter
@Setter
public class SignUpRequest {

    @NotNull(message = "Password must not null !!!")
    @NotEmpty(message = "User name must not be empty")
    private String userName;

    @NotNull(message = "Password must not null !!!")
    @NotEmpty(message = "Password must not be empty !!!")
    private String password;

    @NotNull(message = "Email must not be null !!!")
    @NotEmpty(message = "Email must not be empty")
    private String email;

    @NotNull(message = "First name must not be null !!!")
    @NotEmpty(message = "First name must be filled !!!")
    private String firstName;

    @NotNull(message = "Last name must not be null !!!")
    @NotEmpty(message = "Last name must be filled !!!")
    private String lastName;

}

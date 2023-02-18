package com.example.socialnetworkproject.models.entities.DTO.responds;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoginRespond {
    private String jwt;
    private String refreshToken;
    private String userName;
    private LocalDateTime timestamp;
}

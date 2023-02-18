package com.example.socialnetworkproject.models.entities.DTO.responds;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorRespond {
    private int statusCode;
    private String message;
}

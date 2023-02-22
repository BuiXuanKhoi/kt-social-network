package com.example.socialnetworkproject.constants;

import com.example.socialnetworkproject.exceptions.ResourceNotFoundException;

import java.util.Arrays;

public enum Role {
    USER("USER"),
    ADMIN("ADMIN");

    private String value;

    Role(String value) {
        this.value = value;
    }

    public static Role of(String expectRole){
        return Arrays.stream(values())
                .filter(role -> role.value.equals(expectRole))
                .findFirst()
                .orElseThrow(
                        () -> new ResourceNotFoundException("Role not exist")
                );
    }
}

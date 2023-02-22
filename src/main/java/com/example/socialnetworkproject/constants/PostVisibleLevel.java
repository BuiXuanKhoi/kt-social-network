package com.example.socialnetworkproject.constants;

import com.example.socialnetworkproject.exceptions.ResourceNotFoundException;
import com.example.socialnetworkproject.models.entities.Posts;

import java.util.Arrays;

public enum PostVisibleLevel {

    PRIVATE ("PRIVATE"),
    FRIEND_ONLY("FRIEND_ONLY"),
    PUBLIC ("PUBLIC");

    private String value;

    PostVisibleLevel(String visibleLevel) {
        this.value = visibleLevel;
    }

    public static PostVisibleLevel of(String expectVisibleLevel){
        return Arrays.stream(PostVisibleLevel.values())
                .filter(postVisibleLevel -> postVisibleLevel.value.equals(expectVisibleLevel))
                .findFirst()
                .orElseThrow(
                        () -> new ResourceNotFoundException("Not Exist This Visible Level !!!")
                );
    }
}

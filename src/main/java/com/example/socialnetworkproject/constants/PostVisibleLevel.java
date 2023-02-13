package com.example.socialnetworkproject.constants;

public enum PostVisibleLevel {

    PRIVATE ("PRIVATE"),
    FRIEND_ONLY("FRIEND_ONLY"),
    PUBLIC ("PUBLIC");

    private String visibleLevel;

    PostVisibleLevel(String visibleLevel) {
        this.visibleLevel = visibleLevel;
    }
}

package com.example.socialnetworkproject.models.entities.DTO.request;

import com.example.socialnetworkproject.models.entities.Hashtags;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@AllArgsConstructor
public class CreatePostRequest {
    private String postContent;
    private String postVisibleLevel;
    private boolean isCommentAllowed;
    private List<Hashtags> hashtags;
}

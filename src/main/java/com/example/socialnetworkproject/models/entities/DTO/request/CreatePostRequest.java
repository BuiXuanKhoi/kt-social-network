package com.example.socialnetworkproject.models.entities.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CreatePostRequest {
    private String postContent;
    private String postVisibleLevel;
    private Boolean isCommentAllowed;
    private List<UUID> hashtags;
    private MultipartFile[] files;
}

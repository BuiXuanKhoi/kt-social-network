package com.example.socialnetworkproject.models.entities.DTO.request;

import com.example.socialnetworkproject.constants.PostVisibleLevel;
import com.example.socialnetworkproject.models.entities.Assets;
import com.example.socialnetworkproject.models.entities.UserTagged;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePostRequest {
    private String postContent;

    private PostVisibleLevel postVisibleLevel;

    private Boolean isCommentAllowed;

    private List<UserTagged> userTaggeds;

    private List<Assets> assets;

}

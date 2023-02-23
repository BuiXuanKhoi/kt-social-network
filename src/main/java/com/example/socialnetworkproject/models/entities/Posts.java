package com.example.socialnetworkproject.models.entities;

import com.example.socialnetworkproject.constants.PostVisibleLevel;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "posts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Posts {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "post_id")
    private UUID postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users author;

    @Column(name = "post_content")
    private String postContent;

    @Column(name = "post_visible_level")
    @Enumerated(EnumType.STRING)
    private PostVisibleLevel postVisibleLevel;

    @Column(name = "is_commet_allow")
    private Boolean isCommentAllowed;

    @OneToMany(mappedBy = "posts", cascade = CascadeType.ALL)
    private List<Comments> comments;

    @OneToMany(mappedBy = "posts", cascade = CascadeType.ALL)
    private List<PostRecords> postRecords;

    @OneToMany(mappedBy = "posts", cascade = CascadeType.ALL)
    private List<Assets> assets;

    @OneToMany(mappedBy = "posts", cascade = CascadeType.ALL)
    private List<Expressions> expressions;

    @OneToMany(mappedBy = "posts")
    private List<UserTagged> userTaggeds;

    @OneToMany(mappedBy = "posts")
    private List<SystemTagged> systemTaggeds;

    @OneToMany(mappedBy = "posts")
    private List<PostNotification> postNotifications;


}

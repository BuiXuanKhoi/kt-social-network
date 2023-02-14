package com.example.socialnetworkproject.models.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "post_comments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Comments {

    @Column(name = "comment_id")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Id
    private UUID commentId;

    @Column(name = "comment_content", columnDefinition = "text")
    private String commentContent;

    @Column(name = "comment_create_time")
    @CreationTimestamp
    private Date commentCreateTime;

    @Column(name = "comment_update_time")
    @UpdateTimestamp
    private Date commentUpdateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Posts posts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;
}

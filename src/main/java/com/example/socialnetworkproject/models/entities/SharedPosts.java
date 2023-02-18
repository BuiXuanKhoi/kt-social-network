package com.example.socialnetworkproject.models.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "shared_posts")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SharedPosts extends Posts {

    @OneToOne
    @JoinColumn(name = "post_id")
    private Posts posts;


    @ManyToOne
    @JoinColumn(name = "shared_from_id")
    private Users sharedFrom;
}

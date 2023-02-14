package com.example.socialnetworkproject.models.entities;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Users {


    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "user_email")
    private String email;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Posts> posts;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comments> comments;

    @OneToOne(mappedBy = "users", fetch = FetchType.LAZY)
    private Information information;

    @OneToMany(mappedBy = "users", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Expressions> expressions;


    @OneToMany(mappedBy = "follower", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Follow> followers;

    @OneToMany(mappedBy = "followed", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Follow> followeds;

    @OneToMany(mappedBy = "requestFrom", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Requests> requestFrom;

    @OneToMany(mappedBy = "requestTo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Requests> requestTo;




}

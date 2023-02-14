package com.example.socialnetworkproject.models.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "user_information")
public class Information {


    @Column(name = "information_id")
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID informationId;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "description")
    private String description;
    @Column(name = "school")
    private String school;
    @Column(name = "hobby")
    private String hobby;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "avatar_asset_id")
    private Assets avatarAsset;

    @OneToOne
    @JoinColumn(name = "background_asset_id")
    private Assets backgroundAsset;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Users users;
}

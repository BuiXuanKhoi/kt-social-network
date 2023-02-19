package com.example.socialnetworkproject.models.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "information")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name = "date_of_birth")
    private LocalDateTime birthday;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "avatar_asset_id")
    private Assets avatarAsset;

    @OneToOne
    @JoinColumn(name = "background_asset_id")
    private Assets backgroundAsset;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id")
    private Users users;

}

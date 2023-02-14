package com.example.socialnetworkproject.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "followers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Follow {


    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "follower_id")
    private UUID followerId;

    @Column(name = "follow_from")
    private LocalDateTime followFrom;

    @ManyToOne
    @JoinColumn(name = "followed_id")
    private Users followed;

    @ManyToOne
    @JoinColumn(name = "follower_id")
    private Users follower;
}

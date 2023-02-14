package com.example.socialnetworkproject.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "friends")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Friends {

    @Id
    @Column(name = "friend_id")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID friendId;

    @Column(name = "friend_from")
    private LocalDateTime friendFrom;

}

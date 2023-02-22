package com.example.socialnetworkproject.models.entities;



import com.example.socialnetworkproject.constants.FriendLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "friends")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@DiscriminatorColumn(name = "friends")
public class Friends extends Relations {


    @OneToOne
    @JoinColumn(name = "relation_id")
    private Relations relation;

    @Column(name = "friend_level")
    @Enumerated(EnumType.STRING)
    private FriendLevel friendLevel;
}


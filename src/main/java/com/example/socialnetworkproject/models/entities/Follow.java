package com.example.socialnetworkproject.models.entities;

import com.example.socialnetworkproject.constants.FollowVisibleLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "followers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Follow extends Relations {

    @Column(name = "follow_visible_level")
    @Enumerated(EnumType.STRING)
    private FollowVisibleLevel followVisibleLevel;

    @OneToOne
    @JoinColumn(name = "relation_id")
    private Relations relations;
}

package com.example.socialnetworkproject.models.entities;

import com.example.socialnetworkproject.constants.FollowVisibleLevel;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Table(name = "follows")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Follow extends Relations {

    @Column(name = "follow_visible_level")
    @Enumerated(EnumType.STRING)
    private FollowVisibleLevel followVisibleLevel;

    @OneToOne(mappedBy = "follow", cascade = CascadeType.ALL)
    @JoinColumn(name = "relation_id")
    private Relations relations;


}

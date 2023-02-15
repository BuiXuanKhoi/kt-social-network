package com.example.socialnetworkproject.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "friend_request")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class FriendRequest extends Requests{

    @OneToOne
    @JoinColumn(name = "request_id")
    private Requests requests;
}

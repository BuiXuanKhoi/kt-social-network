package com.example.socialnetworkproject.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.ast.Not;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "post_notification")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostNotification extends Notifications {

    @OneToOne
    @JoinColumn(name = "notification_id")
    private Notifications notifications;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Posts posts;
}

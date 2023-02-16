package com.example.socialnetworkproject.models.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
//import org.springframework.security.core.userdetails.User;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "notification")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class Notifications {

    @Id
    @Column(name = "notification_id")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID notificationId;

    @Column(name = "notification_content")
    private String notificationContent;

    @Column(name = "notification_at")
    private LocalDateTime notificationAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @OneToOne(mappedBy = "notifications", cascade = CascadeType.ALL)
    private PostNotification postNotification;
}

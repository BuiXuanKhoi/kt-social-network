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
@Table(name = "user_tagged")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserTagged {

    @Id
    @Column(name = "user_tagged_id")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID userTaggedId;

    @Column(name = "tagged_date")
    private LocalDateTime taggedDate;

    @ManyToOne
    @JoinColumn(name = "hash_tag_id")
    private Hashtags hashtags;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Posts posts;
}

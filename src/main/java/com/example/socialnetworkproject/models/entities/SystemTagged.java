package com.example.socialnetworkproject.models.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "system_tagged")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SystemTagged {

    @Id
    @Column(name = "system_tagged_id")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID systemTaggedId;

    @Column(name = "system_tagged_at")
    private LocalDateTime systemTaggedAt;


    @ManyToOne
    @JoinColumn(name = "post_id")
    private Posts posts;

    @ManyToOne
    @JoinColumn(name = "system_tag_id")
    private SystemTag systemTag;
}

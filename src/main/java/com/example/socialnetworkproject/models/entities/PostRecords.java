package com.example.socialnetworkproject.models.entities;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "post_records")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostRecords {

    @Id
    @Column(name = "post_record_id")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID postRecordId;

    @Column(name = "post_record_content", columnDefinition = "text")
    private String postRecordContent;

    @Column(name = "post_record_create_at")
    @CreationTimestamp
    private LocalDateTime postRecordCreateAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Posts posts;
}

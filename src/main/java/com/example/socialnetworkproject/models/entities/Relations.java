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
@Table(name = "relation")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Relations {

    @Column(name = "relation_id")
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID relationId;

    @Column(name = "relation_create_date")
    private LocalDateTime relationCreateDate;

    @ManyToOne
    @JoinColumn(name = "relation_from_id")
    private Users relationFrom;

    @ManyToOne
    @JoinColumn(name = "relation_to_id")
    private Users relationTo;

    @OneToOne(mappedBy = "relation", cascade = CascadeType.ALL)
    private Friends friends;

    @OneToOne(mappedBy = "relations", cascade = CascadeType.ALL)
    private Follow follow;
}


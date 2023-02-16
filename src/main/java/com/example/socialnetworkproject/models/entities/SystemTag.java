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
@Table(name = "system_tags")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SystemTag {

    @Id
    @Column(name = "system_tag_id")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID systemTagId;

    @Column(name = "system_tag_name")
    private String systemTagName;

    @Column(name = "system_tag_description")
    private String systemTagDescription;

    @OneToMany(mappedBy = "systemTag", cascade = CascadeType.ALL)
    private List<SystemTagged> systemTaggeds;

}

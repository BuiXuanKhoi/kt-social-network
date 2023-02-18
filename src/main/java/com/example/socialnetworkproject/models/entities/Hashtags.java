package com.example.socialnetworkproject.models.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "hash_tags")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Hashtags {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "hash_tag_id")
    private UUID hashtagId;

    @Column(name = "hash_tag_name")
    private String hashtagName;

    @Column(name = "hash_tag_description", columnDefinition = "text")
    private String hashtagDescription;

    @OneToMany(mappedBy = "hashtags", cascade = CascadeType.ALL)
    private List<UserTagged> userTagged;


}

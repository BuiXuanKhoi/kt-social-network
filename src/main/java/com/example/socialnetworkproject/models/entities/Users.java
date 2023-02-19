package com.example.socialnetworkproject.models.entities;



import com.example.socialnetworkproject.models.entities.document.UserDocument;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.solr.core.mapping.SolrDocument;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users", indexes = {@Index(name = "idx_user_name", columnList = "user_name")
                                    ,@Index(name = "idx_email", columnList = "user_email")
                                    ,@Index(name = "idx_user_uuid",columnList = "user_id")})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Users {


    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "user_id")
    private UUID userId;


    @Column(name = "user_name")
    private String userName;


    @Column(name = "password")
    private String password;


    @Column(name = "user_email")
    private String email;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Posts> posts;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comments> comments;

    @OneToOne(mappedBy = "users")
    private Information information;

    @OneToMany(mappedBy = "users", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Expressions> expressions;



    @OneToMany(mappedBy = "requestFrom", cascade = CascadeType.ALL)
    private List<Requests> requestReceived;

    @OneToMany(mappedBy = "requestTo", cascade = CascadeType.ALL)
    private List<Requests> requestSent;

    @OneToMany(mappedBy = "firstUser", cascade = CascadeType.ALL)
    private List<Conversations> conversationsAsFirst;

    @OneToMany(mappedBy = "secondUser", cascade = CascadeType.ALL)
    private List<Conversations> conversationsAsSecond;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Notifications> notifications;

//    @OneToMany(mappedBy = "relationFrom", cascade = CascadeType.ALL)
//    private List<Relations> relationFrom;
//
//    @OneToMany(mappedBy = "relationTo", cascade = CascadeType.ALL)
//    private List<Relations> relationTo;

    @OneToMany(mappedBy = "sharedFrom", cascade = CascadeType.ALL)
    private List<SharedPosts> sharedPosts;

}

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
@Table(name = "conversations")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Conversations {

    @Id
    @Column(name = "conversation_id")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID conversationId;

    @Column(name = "conversation_create_date")
    private LocalDateTime conversationCreateDate;

    @ManyToOne
    @JoinColumn(name = "first_user_id")
    private Users firstUser;

    @ManyToOne
    @JoinColumn(name = "second_user_id")
    private Users secondUser;

    @OneToMany(mappedBy = "conversations", cascade = CascadeType.ALL)
    private List<Messages> messages;
}

package com.example.socialnetworkproject.models.entities;

import com.example.socialnetworkproject.constants.RequestStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "requests")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Requests {

    @Column(name = "request_id")
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID requestId;


    @ManyToOne
    @JoinColumn(name = "request_from_id")
    private Users requestFrom;

    @ManyToOne
    @JoinColumn(name = "request_to_id")
    private Users requestTo;

    @Column(name = "request_at")
    private LocalDateTime requestAt;

    @Column(name = "request_status")
    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;

    @OneToOne(mappedBy = "requests", cascade = CascadeType.ALL)
    private FriendRequest friendRequest;
}

package com.example.socialnetworkproject.models.entities;

import com.example.socialnetworkproject.constants.ExpressionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Table(name = "expressions")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Expressions {

    @Id
    @Column(name = "expression_id")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID expressionId;

    @Column(name = "expression_type")
    @Enumerated(EnumType.STRING)
    private ExpressionType expressionType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Posts posts;
}

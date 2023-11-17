package com.devdialogue.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {
    // Generated id, createdAt, updatedAt properties are inherited from BaseEntity

    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "author")
    @JsonIgnoreProperties({"author"})
    private List<Question> questionList;

    @OneToMany(mappedBy = "author")
    @JsonIgnoreProperties({"author"})
    private List<Answer> answerList;

    @OneToMany(mappedBy = "author")
    @JsonIgnoreProperties({"author"})
    private List<Comment> commentList;

    @OneToOne
    @JoinColumn
    @JsonIgnoreProperties({"user", "password"})
    private SecuredUser securedUser;
}

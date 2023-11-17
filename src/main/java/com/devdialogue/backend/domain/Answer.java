package com.devdialogue.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Answer extends BaseEntity {
    // Generated id, createdAt, updatedAt properties are inherited from BaseEntity

    @Enumerated(value = EnumType.STRING)
    private Category category;

    @JoinColumn
    @ManyToOne
    @JsonIgnoreProperties({"answerList"})
    private SecuredUser author;

    @JoinColumn
    @ManyToOne
    @JsonIgnoreProperties({"answerList"})
    private Question question;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @OneToMany(mappedBy = "answer")
    @JsonIgnoreProperties({"answer"})
    private List<Comment> commentList;

    private Boolean accepted = false;
}

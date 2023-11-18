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
public class Question extends BaseEntity {
    // Generated id, createdAt, updatedAt properties are inherited from BaseEntity

    @Enumerated(value = EnumType.STRING)
    private Category category;

    @JoinColumn
    @ManyToOne
    @JsonIgnoreProperties({"questionList", "answerList", "commentList", "securedUser"})
    private User author;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @OneToMany(mappedBy = "question")
    @JsonIgnoreProperties({"question"})
    private List<Answer> answerList;

    @OneToMany(mappedBy = "question")
    @JsonIgnoreProperties({"question"})
    private List<Comment> commentList;
}

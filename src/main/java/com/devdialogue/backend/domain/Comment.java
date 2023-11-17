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
public class Comment extends BaseEntity {
    // Generated id, createdAt, updatedAt properties are inherited from BaseEntity

    @Enumerated(value = EnumType.STRING)
    private Category category;

    @JoinColumn
    @ManyToOne
    @JsonIgnoreProperties({"questionList", "answerList", "commentList"})
    private SecuredUser author;

    @ManyToOne
    @JoinColumn
    private Question question;

    @ManyToOne
    @JoinColumn
    private Answer answer;

    @Column(nullable = false)
    private String content;
}
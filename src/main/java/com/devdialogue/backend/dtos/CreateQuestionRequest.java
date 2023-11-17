package com.devdialogue.backend.dtos;

import com.devdialogue.backend.domain.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateQuestionRequest {
    @NotBlank
    private String title;

    @NotNull
    private Category category;

    @NotNull
    private String content;

    public Question to() {
        return Question.builder()
                .title(this.title)
                .content(this.content)
                .category(this.category)
                .build();
    }
}

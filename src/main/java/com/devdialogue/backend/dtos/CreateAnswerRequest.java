package com.devdialogue.backend.dtos;

import com.devdialogue.backend.domain.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateAnswerRequest {
    @NotNull
    @NotBlank
    private String content;

    public Answer to(User author, Question question) {
        return Answer.builder()
                .author(author)
                .question(question)
                .content(this.content)
                .build();
    }
}

package com.devdialogue.backend.dtos;

import com.devdialogue.backend.domain.User;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private String usertag;

    public User to() {
        return User.builder()
                .name(this.name)
                .email(this.email)
                .usertag(this.usertag)
                .build();
    }
}

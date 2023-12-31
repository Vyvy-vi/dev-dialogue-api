package com.devdialogue.backend.dtos;

import com.devdialogue.backend.domain.SecuredUser;
import com.devdialogue.backend.domain.User;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserRequest {
    private String username;
    private String password;

    @NotBlank
    private String name;
    @NotBlank
    private String email;

    public User to() {
        return User.builder()
                .name(this.name)
                .email(this.email)
                .securedUser(SecuredUser.builder()
                        .username(this.username)
                        .password(this.password)
                        .build())
                .build();
    }
}

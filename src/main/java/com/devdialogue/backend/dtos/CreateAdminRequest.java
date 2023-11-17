package com.devdialogue.backend.dtos;

import com.devdialogue.backend.domain.Admin;
import com.devdialogue.backend.domain.SecuredUser;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateAdminRequest {
    private String username;
    private String password;

    @NotBlank
    private String name;
    @NotBlank
    private String email;

    public Admin to() {
        return Admin.builder()
                .name(this.name)
                .email(this.email)
                .securedUser(SecuredUser.builder()
                        .username(this.username)
                        .password(this.password)
                        .build())
                .build();
    }
}

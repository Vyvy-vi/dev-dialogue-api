package com.devdialogue.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Admin extends BaseEntity {
    // Generated id, createdAt, updatedAt properties are inherited from BaseEntity

    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToOne
    @JoinColumn
    @JsonIgnoreProperties({"admin", "password"})
    private SecuredUser securedUser;
}

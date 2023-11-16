package com.devdialogue.backend.repositories;

import com.devdialogue.backend.domain.SecuredUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecuredUserRepository extends JpaRepository<SecuredUser, Integer> {
    SecuredUser findByEmail(String name);
}

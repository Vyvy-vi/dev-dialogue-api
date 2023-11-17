package com.devdialogue.backend.repositories;

import com.devdialogue.backend.domain.SecuredUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecuredUserRepository extends JpaRepository<SecuredUser, Integer> {
    SecuredUser findByUsername(String username);
}

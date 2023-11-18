package com.devdialogue.backend.repositories;

import com.devdialogue.backend.domain.SecuredUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface SecuredUserRepository extends JpaRepository<SecuredUser, Integer> {
    SecuredUser findByUsername(String username);

    @Modifying
    @Transactional
    @Query("update SecuredUser su set su.username=:username, su.password=:password where su.id = :id")
    void updateSecuredUser(int id, String username, String password);
}

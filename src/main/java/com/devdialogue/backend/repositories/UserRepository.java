package com.devdialogue.backend.repositories;

import com.devdialogue.backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Modifying
    @Transactional
    @Query("update User u set u.name=:name, u.email=:email where u.id = :id")
    void updateUser(int id, String name, String email);
}

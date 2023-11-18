package com.devdialogue.backend.repositories;

import com.devdialogue.backend.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    @Modifying
    @Transactional
    @Query("update Admin a set a.name=:name, a.email=:email where a.id = :id")
    void updateAdmin(int id, String name, String email);
}

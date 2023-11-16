package com.devdialogue.backend.repositories;

import com.devdialogue.backend.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
}

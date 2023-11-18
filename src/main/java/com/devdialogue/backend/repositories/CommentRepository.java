package com.devdialogue.backend.repositories;

import com.devdialogue.backend.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Modifying
    @Transactional
    @Query("update Comment c set c.content=:content where c.id = :id")
    void updateComment(int id, String content);
}

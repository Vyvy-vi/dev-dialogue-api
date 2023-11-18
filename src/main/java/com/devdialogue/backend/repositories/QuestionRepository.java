package com.devdialogue.backend.repositories;

import com.devdialogue.backend.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    @Modifying
    @Transactional
    @Query("update Question q set q.title=:title, q.content=:content where q.id = :id")
    void updateQuestion(int id, String title, String content);
}

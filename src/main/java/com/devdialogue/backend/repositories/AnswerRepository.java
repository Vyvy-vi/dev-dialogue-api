package com.devdialogue.backend.repositories;

import com.devdialogue.backend.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    @Modifying
    @Transactional
    @Query("update Answer a set a.content=:content where a.id = :id")
    void updateAnswer(int id, String content);
}

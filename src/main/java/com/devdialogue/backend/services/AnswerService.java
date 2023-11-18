package com.devdialogue.backend.services;

import com.devdialogue.backend.domain.Answer;
import com.devdialogue.backend.repositories.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    public List<Answer> findAll() {
        return answerRepository.findAll();
    }

    public Answer findById(int id) {
        return answerRepository.findById(id).orElse(null);
    }

    public Answer createAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    public void deleteAnswer(int id) {
        if (answerRepository.findById(id).isPresent()) answerRepository.deleteById(id);
    }
}

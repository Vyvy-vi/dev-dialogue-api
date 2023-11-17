package com.devdialogue.backend.services;

import com.devdialogue.backend.domain.Question;
import com.devdialogue.backend.domain.SecuredUser;
import com.devdialogue.backend.domain.User;
import com.devdialogue.backend.repositories.QuestionRepository;
import com.devdialogue.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    public Question findById(int id) {
        return questionRepository.findById(id).orElse(null);
    }

    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    public void deleteQuestion(int id) {
        if (questionRepository.findById(id).isPresent()) questionRepository.deleteById(id);
    }
}

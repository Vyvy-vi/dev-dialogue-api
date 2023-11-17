package com.devdialogue.backend.controllers;

import com.devdialogue.backend.domain.Question;
import com.devdialogue.backend.domain.SecuredUser;
import com.devdialogue.backend.domain.User;
import com.devdialogue.backend.dtos.CreateQuestionRequest;
import com.devdialogue.backend.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping
    public List<Question> findAllQuestions() {
        return questionService.findAll();
    }

    @PostMapping
    public Question createQuestion(@RequestBody @Valid CreateQuestionRequest createQuestionRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecuredUser securedUser = (SecuredUser) authentication.getPrincipal();
        Question question = createQuestionRequest.to();
        question.setAuthor(securedUser.getUser());
        return questionService.createQuestion(question);
    }


    @GetMapping("/{id}")
    public Question findQuestionById(@PathVariable("id") int id) {
        return questionService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable("id") int id) {
        Question question = questionService.findById(id);
        if (question == null) {
            return new ResponseEntity<>("Question with id not found", HttpStatus.NOT_FOUND);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecuredUser securedUser = (SecuredUser) authentication.getPrincipal();
        User user = securedUser.getUser();
        if (user == null || question.getAuthor() != null && question.getAuthor().getId() == user.getId()) {
            questionService.deleteQuestion(id);
            return new ResponseEntity<>("Question successfully deleted", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Can not delete a question you didn't create", HttpStatus.FORBIDDEN);
        }
    }
}

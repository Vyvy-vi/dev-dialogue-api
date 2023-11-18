package com.devdialogue.backend.controllers;

import com.devdialogue.backend.domain.Answer;
import com.devdialogue.backend.domain.Question;
import com.devdialogue.backend.domain.SecuredUser;
import com.devdialogue.backend.domain.User;
import com.devdialogue.backend.dtos.CreateAnswerRequest;
import com.devdialogue.backend.services.AnswerService;
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
public class AnswerController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private AnswerService answerService;

    @PostMapping("/questions/{questionId}/answers")
    public ResponseEntity<?> createAnswer(@PathVariable("questionId") int questionId, @RequestBody @Valid CreateAnswerRequest createAnswerRequest) {
        Question question = questionService.findById(questionId);
        if (question == null) {
            return new ResponseEntity<>("Question with id not found", HttpStatus.NOT_FOUND);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecuredUser securedUser = (SecuredUser) authentication.getPrincipal();
        User user = securedUser.getUser();

        if (user == null) return new ResponseEntity<>("Invalid User", HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(answerService.createAnswer(createAnswerRequest.to(user, question)), HttpStatus.CREATED);
    }


    @GetMapping("/questions/{questionId}/answers")
    public ResponseEntity<?> getAllAnswersForQuestion(@PathVariable("questionId") int questionId) {
        Question question = questionService.findById(questionId);
        if (question == null) {
            return new ResponseEntity<>("Question with id not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(question.getAnswerList(), HttpStatus.OK);
    }

    @GetMapping("/answers")
    public List<Answer> getAllAnswers() {
        return answerService.findAll();
    }

    @GetMapping("/answers/{answerId}")
    public Answer getAnswerById(@PathVariable("answerId") int answerId) {
        return answerService.findById(answerId);
    }

    @DeleteMapping("/answers/{answerId}")
    public ResponseEntity<?> deleteAnswer(@PathVariable("answerId") int answerId) {
        Answer answer = answerService.findById(answerId);
        if (answer == null) {
            return new ResponseEntity<>("Answer with id not found", HttpStatus.NOT_FOUND);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecuredUser securedUser = (SecuredUser) authentication.getPrincipal();
        User user = securedUser.getUser();
        if (user == null || answer.getAuthor() != null && answer.getAuthor().getId() == user.getId()) {
            answerService.deleteAnswer(answerId);
            return new ResponseEntity<>("Answer successfully deleted", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Can not delete an answer you didn't create", HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/answers/{answerId}")
    public ResponseEntity<?> updateAnswer(@PathVariable("answerId") int answerId) {
        Answer answer = answerService.findById(answerId);
        if (answer == null) {
            return new ResponseEntity<>("Answer with id not found", HttpStatus.NOT_FOUND);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecuredUser securedUser = (SecuredUser) authentication.getPrincipal();
        User user = securedUser.getUser();

        if (user != null && answer.getAuthor() != null && answer.getAuthor().getId() == user.getId()) {
            // questionService.deleteQuestion(questionId);
            return new ResponseEntity<>("Answer successfully updated", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Can not update an answer you didn't create", HttpStatus.FORBIDDEN);
        }
    }
}

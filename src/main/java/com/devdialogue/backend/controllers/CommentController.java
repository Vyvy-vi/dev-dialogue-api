package com.devdialogue.backend.controllers;

import com.devdialogue.backend.domain.Comment;
import com.devdialogue.backend.domain.Question;
import com.devdialogue.backend.domain.SecuredUser;
import com.devdialogue.backend.domain.User;
import com.devdialogue.backend.dtos.CreateQuestionRequest;
import com.devdialogue.backend.services.CommentService;
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
public class CommentController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;

    @PostMapping("/questions/{questionId}/comment")
    public ResponseEntity<?> createComment(@PathVariable("questionId") int questionId, @RequestBody @Valid CreateQuestionRequest createQuestionRequest) {
        Question question = questionService.findById(questionId);
        if (question == null) {
            return new ResponseEntity<>("Question with id not found", HttpStatus.NOT_FOUND);
        }
        return null;
    }

    @GetMapping("/comments")
    public List<Comment> getAllComments() {
        return commentService.findAll();
    }

    @GetMapping("/comments/{commentId}")
    public Comment getCommentById(@PathVariable("commentId") int commentId) {
        return commentService.findById(commentId);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable("commentId") int commentId) {
        Comment comment = commentService.findById(commentId);
        if (comment == null) {
            return new ResponseEntity<>("Comment with id not found", HttpStatus.NOT_FOUND);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecuredUser securedUser = (SecuredUser) authentication.getPrincipal();
        User user = securedUser.getUser();
        if (user == null || comment.getAuthor() != null && comment.getAuthor().getId() == user.getId()) {
            commentService.deleteComment(commentId);
            return new ResponseEntity<>("Comment successfully deleted", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Can not delete an Comment you didn't create", HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable("commentId") int commentId) {
        Comment comment = commentService.findById(commentId);
        if (comment == null) {
            return new ResponseEntity<>("Comment with id not found", HttpStatus.NOT_FOUND);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecuredUser securedUser = (SecuredUser) authentication.getPrincipal();
        User user = securedUser.getUser();

        if (user != null && comment.getAuthor() != null && comment.getAuthor().getId() == user.getId()) {
            // questionService.deleteQuestion(questionId);
            return new ResponseEntity<>("Comment successfully updated", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Can not update an Comment you didn't create", HttpStatus.FORBIDDEN);
        }
    }
}

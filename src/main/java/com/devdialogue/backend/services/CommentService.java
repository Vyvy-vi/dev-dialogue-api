package com.devdialogue.backend.services;

import com.devdialogue.backend.domain.Comment;
import com.devdialogue.backend.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public Comment findById(int id) {
        return commentRepository.findById(id).orElse(null);
    }

    public Comment createComment(Comment answer) {
        return commentRepository.save(answer);
    }

    public void deleteComment(int id) {
        if (commentRepository.findById(id).isPresent()) commentRepository.deleteById(id);
    }
}

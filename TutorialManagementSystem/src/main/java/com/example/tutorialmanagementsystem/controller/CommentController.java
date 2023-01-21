package com.example.tutorialmanagementsystem.controller;

import com.example.tutorialmanagementsystem.exception.ResourceNotFoundException;
import com.example.tutorialmanagementsystem.model.Comment;
import com.example.tutorialmanagementsystem.repository.CommentRepository;
import com.example.tutorialmanagementsystem.repository.TutorialRepository;
import com.example.tutorialmanagementsystem.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/tutorials/{tutorialId}/comments")
    public ResponseEntity<List<Comment>> getAllCommentsByTutorialId(@PathVariable(value = "tutorialId") Long tutorialId) {
        return new ResponseEntity<>( commentService.findByTutorialId(tutorialId), HttpStatus.OK);
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<Comment> getCommentsByTutorialId(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(commentService.getCommentByTutorialId(id), HttpStatus.OK);
    }

    @PostMapping("/tutorials/{tutorialId}/comments")
    public ResponseEntity<Comment> createComment(@PathVariable(value = "tutorialId") Long tutorialId,
                                                 @RequestBody Comment commentRequest) {
        return new ResponseEntity<>(commentService.createComment(tutorialId,commentRequest), HttpStatus.CREATED);
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable("id") long id, @RequestBody Comment commentRequest) {
        return new ResponseEntity<>(commentService.updateComment(id,commentRequest), HttpStatus.OK);
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable("id") long id) {
        commentService.deleteComment(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/tutorials/{tutorialId}/comments")
    public ResponseEntity<List<Comment>> deleteAllCommentsOfTutorial(@PathVariable(value = "tutorialId") Long tutorialId) {
         commentService.deleteAllCommentsOfTutorial(tutorialId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
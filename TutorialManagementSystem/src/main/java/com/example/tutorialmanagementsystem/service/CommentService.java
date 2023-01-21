package com.example.tutorialmanagementsystem.service;

import com.example.tutorialmanagementsystem.exception.ResourceNotFoundException;
import com.example.tutorialmanagementsystem.model.Comment;
import com.example.tutorialmanagementsystem.model.Tutorial;
import com.example.tutorialmanagementsystem.repository.CommentRepository;
import com.example.tutorialmanagementsystem.repository.TutorialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TutorialRepository tutorialRepository;

    public List<Comment> findByTutorialId(Long tutorialId) {
        if (!tutorialRepository.existsById(tutorialId)) {
            throw new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId);
        }

        return commentRepository.findByTutorialId(tutorialId);
    }

    public Comment getCommentByTutorialId(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Comment with id = " + id));
    }

    public Comment createComment(Long tutorialId,Comment commentRequest) {
        return tutorialRepository.findById(tutorialId).map(tutorial -> {
            commentRequest.setTutorial(tutorial);
            return commentRepository.save(commentRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId));
    }

    public Comment updateComment(Long id,Comment commentRequest) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CommentId " + id + "not found"));

        comment.setContent(commentRequest.getContent());

        return comment;
    }

    public void deleteComment(long id) {
        commentRepository.deleteById(id);
    }

    public void deleteAllCommentsOfTutorial(Long tutorialId) {
        if (!tutorialRepository.existsById(tutorialId)) {
            throw new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId);
        }

        commentRepository.deleteByTutorialId(tutorialId);
    }
}

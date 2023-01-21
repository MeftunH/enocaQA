package com.example.tutorialmanagementsystem.service;

import com.example.tutorialmanagementsystem.exception.ResourceNotFoundException;
import com.example.tutorialmanagementsystem.model.Tutorial;
import com.example.tutorialmanagementsystem.repository.TutorialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TutorialService {

    @Autowired
    private final TutorialRepository tutorialRepository;
    public List<Tutorial> getAllTutorials(  String title) {
        List<Tutorial> tutorials = new ArrayList<>();

        if (title == null)
            tutorialRepository.findAll().forEach(tutorials::add);
        else
            tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);

        if (tutorials.isEmpty()) {
           return new ArrayList<>();
        }
        return tutorials;

    }

    public Tutorial getTutorialById(long id) {
        return tutorialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));
    }

    public Tutorial createTutorial(Tutorial tutorial) {
        return tutorialRepository.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), true));
    }

    public Tutorial save(long id, Tutorial tutorial) {
        Tutorial _tutorial = tutorialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));

        _tutorial.setTitle(tutorial.getTitle());
        _tutorial.setDescription(tutorial.getDescription());
        _tutorial.setPublished(tutorial.isPublished());
        return tutorialRepository.save(_tutorial);
    }

    public void deleteTutorial(long id) {
        tutorialRepository.deleteById(id);
    }

    public void deleteAllTutorials() {
        tutorialRepository.deleteAll();
    }

    public List<Tutorial> findByPublished() {
        List<Tutorial> tutorials = tutorialRepository.findByPublished(true);

        if (tutorials.isEmpty()) {
            return new ArrayList<>();
        }
        return tutorials;
    }
}

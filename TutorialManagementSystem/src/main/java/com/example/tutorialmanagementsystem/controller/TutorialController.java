package com.example.tutorialmanagementsystem.controller;

import com.example.tutorialmanagementsystem.exception.ResourceNotFoundException;
import com.example.tutorialmanagementsystem.model.Tutorial;
import com.example.tutorialmanagementsystem.repository.TutorialRepository;
import com.example.tutorialmanagementsystem.service.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TutorialController {

    @Autowired
    private TutorialService tutorialService;
    @Autowired
    private TutorialRepository tutorialRepository;

    @GetMapping("/tutorials")
    public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {

        return new ResponseEntity<>(tutorialService.getAllTutorials(title), HttpStatus.OK);
    }

    @GetMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
        return new ResponseEntity<>(tutorialService.getTutorialById(id), HttpStatus.OK);
    }

    @PostMapping("/tutorials")
    public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
        return new ResponseEntity<>(tutorialService.createTutorial(tutorial), HttpStatus.CREATED);
    }

    @PutMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
        return new ResponseEntity<>(tutorialService.save(id,tutorial), HttpStatus.OK);
    }

    @DeleteMapping("/tutorials/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        tutorialService.deleteTutorial(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/tutorials")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        tutorialService.deleteAllTutorials();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/tutorials/published")
    public ResponseEntity<List<Tutorial>> findByPublished() {
        return new ResponseEntity<>(tutorialService.findByPublished(), HttpStatus.OK);
    }
}



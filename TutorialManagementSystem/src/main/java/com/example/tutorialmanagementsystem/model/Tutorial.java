package com.example.tutorialmanagementsystem.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tutorials")
@Data
public class Tutorial {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tutorial_generator")
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "published")
    private boolean published;

    public Tutorial() {

    }

    public Tutorial(String title, String description, boolean published) {
        this.title = title;
        this.description = description;
        this.published = published;
    }

}


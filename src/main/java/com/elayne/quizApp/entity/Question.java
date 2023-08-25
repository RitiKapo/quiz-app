package com.elayne.quizApp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int id;
    @Column(name = "question_title")
    private String questionTitle;
    @Column(name = "option1")
    private String option1;
    @Column(name = "option2")
    private String option2;
    @Column(name = "option3")
    private String option3;
    @Column(name = "option4")
    private String option4;
    @Column(name = "correct_answer")
    private String correctAnswer;
    @Column(name = "difficulty_level")
    private String difficultyLevel;
    @Column(name = "category")
    private String category;

}

package com.elayne.quizApp.controller;

import com.elayne.quizApp.entity.QuestionWrapper;
import com.elayne.quizApp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category,
                                             @RequestParam int numberOfQuestions,
                                             @RequestParam String title) {
        return quizService.createQuiz(category, numberOfQuestions, title);
    }

    @GetMapping("/get/{quizID}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int quizID) {
        return quizService.getQuizQuestions(quizID);
    }
}

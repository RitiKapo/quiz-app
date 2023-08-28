package com.elayne.quizApp.service;

import com.elayne.quizApp.dao.QuestionDao;
import com.elayne.quizApp.dao.QuizDao;
import com.elayne.quizApp.entity.Question;
import com.elayne.quizApp.entity.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {
    QuizDao quizDao;
    QuestionDao questionDao;

    @Autowired
    public QuizService(QuizDao quizDao, QuestionDao questionDao) {
        this.quizDao = quizDao;
        this.questionDao = questionDao;
    }

    public ResponseEntity<String> createQuiz(String category, int numberOfQuestions, String title) {
        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numberOfQuestions);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }
}

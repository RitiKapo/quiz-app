package com.elayne.quizApp.service;

import com.elayne.quizApp.dao.QuestionDao;
import com.elayne.quizApp.dao.QuizDao;
import com.elayne.quizApp.entity.Question;
import com.elayne.quizApp.entity.QuestionWrapper;
import com.elayne.quizApp.entity.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int quizID) {
        Optional<Quiz> quiz = quizDao.findById(quizID);
        List<Question> questionsFromQuiz;
        if(quiz.isPresent()) {
            questionsFromQuiz = quiz.get().getQuestions();
        } else {
            throw new RuntimeException("Quiz with quiz ID " + quizID + " does not exist.");
        }
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for(Question question : questionsFromQuiz) {
            QuestionWrapper questionWrapper = new QuestionWrapper(question.getId(), question.getQuestionTitle(), question.getOption1(), question.getOption2(), question.getOption3(), question.getOption4());
            questionsForUser.add(questionWrapper);
        }

        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }
}

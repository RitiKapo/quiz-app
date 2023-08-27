package com.elayne.quizApp.service;

import com.elayne.quizApp.dao.QuestionDao;
import com.elayne.quizApp.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    QuestionDao questionDao;

    @Autowired
    public QuestionService(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try {
            question.setId(0);
            questionDao.save(question);
            return new ResponseEntity<>("Successfully added the question!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Couldn't save the question.", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> updateQuestion(Question question) {
        try {
            Question question1 = findById(question.getId());
            questionDao.save(question1);
            return new ResponseEntity<>("Updated the question having ID: " + question.getId(), HttpStatus.CREATED);
        } catch (Exception e) {
            // since the null part will be handled inside the 'findById()'
            // method, no need to handle it here.
            return new ResponseEntity<>("Couldn't update the question with question ID: " + question.getId(), HttpStatus.NOT_FOUND);
        }
    }

    public Question findById(int questionID) {
        Optional<Question> question = questionDao.findById(questionID);
        Question question1;
        if (question.isPresent()) {
            question1 = question.get();
        } else {
            throw new RuntimeException("Question with question ID " + questionID + " does not exist.");
        }
        return question1;
    }

    public ResponseEntity<String> deleteQuestion(int questionID) {
        try {
            Question question = findById(questionID);
            // since the null part will be handled inside the 'findById()' method above.
            questionDao.deleteById(questionID);
            return new ResponseEntity<>("Question with question ID " + questionID + " deleted.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Couldn't find the question with ID " + questionID + " to delete.", HttpStatus.NOT_FOUND);
        }
    }
}

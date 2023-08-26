package com.elayne.quizApp.service;

import com.elayne.quizApp.dao.QuestionDao;
import com.elayne.quizApp.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    QuestionDao questionDao;

    @Autowired
    public QuestionService(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public List<Question> getAllQuestions() {
        return questionDao.findAll();
    }

    public List<Question> getQuestionsByCategory(String category) {
        return questionDao.findByCategory(category);
    }

    public Question addQuestion(Question question) {
        question.setId(0);
        return questionDao.save(question);
    }

    public Question updateQuestion(Question question) {
        Question question1 = findById(question.getId());

        // since the null part will be handled inside the 'findById()'
        // method, no need to handle it here.
        return questionDao.save(question);
    }

    public Question findById(int questionID) {
        Optional<Question> question = questionDao.findById(questionID);
        Question question1 = null;
        if (question.isPresent()) {
            question1 = question.get();
        } else {
            throw new RuntimeException("Question with question ID " + questionID + " does not exist.");
        }
        return question1;
    }

    public String deleteQuestion(int questionID) {
        Question question = findById(questionID);

        // since the null part will be handled inside the 'findById()' method above.
        questionDao.deleteById(questionID);
        return "Question with question ID " + questionID + " deleted.";
    }
}

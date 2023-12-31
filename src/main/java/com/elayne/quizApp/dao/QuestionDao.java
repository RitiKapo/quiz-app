package com.elayne.quizApp.dao;

import com.elayne.quizApp.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
    List<Question> findByCategory(String category);

    @Query(value = "SELECT * FROM question q WHERE q.category= :category ORDER BY RAND() LIMIT :numberOfQuestions",
            nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int numberOfQuestions);
}
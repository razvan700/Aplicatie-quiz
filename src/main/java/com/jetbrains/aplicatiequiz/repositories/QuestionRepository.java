package com.jetbrains.aplicatiequiz.repositories;

import com.jetbrains.aplicatiequiz.dto.QuestionDTO;
import com.jetbrains.aplicatiequiz.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {

    List<Question> findQuestionsByQuizId(@Param("quizId") Long quizId);

    Question findQuestionById(@Param("id") Long id);

    void deleteAllByQuizId(@Param("quizId") Long quizId);

    boolean existsByQuizId(Long quizId);
}

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

    @Query("SELECT q FROM Question q WHERE q.quiz.id = :quizId")
    List<QuestionDTO> findQuestionsByQuizId(@Param("quizId") Long quizId);

    @Query("SELECT new com.jetbrains.aplicatiequiz.dto.QuestionDTO(q) FROM Question q WHERE q.id = :id")
    QuestionDTO findQuestionDTOById(@Param("id") Long id);


    boolean existsByQuizId(Long quizId);
}

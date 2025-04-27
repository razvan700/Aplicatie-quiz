package com.jetbrains.aplicatiequiz.repositories;

import com.jetbrains.aplicatiequiz.models.AnswerChoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerChoiceRepository extends JpaRepository<AnswerChoice, Long> {

    void deleteAllByQuizId(@Param("quizId") Long quizId);
}

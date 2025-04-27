package com.jetbrains.aplicatiequiz.repositories;

import com.jetbrains.aplicatiequiz.models.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    void deleteAllByQuizId(@Param("quizId") Long quizId);
}

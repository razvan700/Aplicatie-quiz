package com.jetbrains.aplicatiequiz.repositories;

import com.jetbrains.aplicatiequiz.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz,Long> {
    Quiz findQuizById(Long id);
}





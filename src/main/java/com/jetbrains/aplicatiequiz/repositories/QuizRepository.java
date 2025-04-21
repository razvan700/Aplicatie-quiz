package com.jetbrains.aplicatiequiz.repositories;

import com.jetbrains.aplicatiequiz.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz,Long> {
}

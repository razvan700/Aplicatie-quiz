package com.jetbrains.aplicatiequiz.repositories;

import com.jetbrains.aplicatiequiz.dto.AttemptDTO;
import com.jetbrains.aplicatiequiz.models.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttemptRepository extends JpaRepository<Attempt, Long> {
    List<Attempt> findByUserId(Long userId);
    List<Attempt> findByQuizId(Long quizId);
}

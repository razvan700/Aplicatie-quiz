package com.jetbrains.aplicatiequiz.services;

import com.jetbrains.aplicatiequiz.dto.AttemptDTO;
import com.jetbrains.aplicatiequiz.models.Attempt;

import java.util.List;

public interface AttemptService {

    Attempt submitAttempt(Attempt attempt, String username, Long quizId);

    List<Attempt> getAttemptsByUserId(Long userId);

    List<Attempt> getAttemptsByQuizId(Long quizId);

    Attempt getAttemptById(Long attemptId);
}

package com.jetbrains.aplicatiequiz.services;

import com.jetbrains.aplicatiequiz.dto.AttemptDTO;
import com.jetbrains.aplicatiequiz.models.Attempt;

import java.util.List;

public interface AttemptService {

    Attempt submitAttempt(AttemptDTO attemptDTO);

    List<AttemptDTO> getAttemptsByUserId(Long userId);

    List<AttemptDTO> getAttemptsByQuizId(Long quizId);

    Attempt getAttemptById(Long attemptId);
}

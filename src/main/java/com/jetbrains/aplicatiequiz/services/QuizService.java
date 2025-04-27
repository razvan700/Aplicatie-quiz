package com.jetbrains.aplicatiequiz.services;

import com.jetbrains.aplicatiequiz.dto.QuizDTO;
import com.jetbrains.aplicatiequiz.models.Quiz;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface QuizService {

    Quiz create(Quiz quiz);

    List<Quiz> list();

    Quiz get(Long id);

    Quiz update(Quiz server);

    Boolean delete(Long id);
}

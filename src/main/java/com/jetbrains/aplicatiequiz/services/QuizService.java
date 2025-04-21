package com.jetbrains.aplicatiequiz.services;

import com.jetbrains.aplicatiequiz.models.Quiz;

import java.util.Collection;

public interface QuizService {

    Quiz create(Quiz quiz);

    Collection<Quiz> list(int limit);

    Quiz get(Long id);

    Quiz update(Quiz server);
    Boolean delete(Long id);
}

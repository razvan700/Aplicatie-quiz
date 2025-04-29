package com.jetbrains.aplicatiequiz.services;

import com.jetbrains.aplicatiequiz.dto.QuizDTO;
import com.jetbrains.aplicatiequiz.models.Quiz;

import java.util.List;

public interface QuizService {

    Quiz create(Quiz quiz);

    List<Quiz> list();

    Quiz get(Long id);

    Quiz update(Quiz quiz);

    Boolean delete(Long id);

    Quiz updateWithNested(Long id, QuizDTO quizDTO);
}

package com.jetbrains.aplicatiequiz.services;

import com.jetbrains.aplicatiequiz.dto.QuizDTO;

import java.util.Collection;

public interface QuizService {

    QuizDTO create(QuizDTO quiz);

    Collection<QuizDTO> list();

    QuizDTO get(Long id);

    QuizDTO update(QuizDTO server);

    Boolean delete(Long id);
}

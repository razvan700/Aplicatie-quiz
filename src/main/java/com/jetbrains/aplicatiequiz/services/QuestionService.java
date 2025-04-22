package com.jetbrains.aplicatiequiz.services;

import com.jetbrains.aplicatiequiz.dto.QuestionDTO;
import com.jetbrains.aplicatiequiz.models.Question;

import java.util.List;

public interface QuestionService {

    QuestionDTO createQuestion(Long quizId, QuestionDTO question);

    List<QuestionDTO> getQuestionsByQuiz(Long quizId);

    QuestionDTO getQuestion(Long id);

    QuestionDTO updateQuestion(Long id, QuestionDTO question);

    boolean deleteQuestion(Long id);
}

package com.jetbrains.aplicatiequiz.services;

import com.jetbrains.aplicatiequiz.models.Question;

import java.util.List;

public interface QuestionService {

    Question createQuestion(Long quizId, Question question);

    List<Question> getQuestionsByQuiz(Long quizId);

    Question getQuestion(Long id);

    Question updateQuestion(Long id, Question question);

    boolean deleteQuestion(Long id);
}

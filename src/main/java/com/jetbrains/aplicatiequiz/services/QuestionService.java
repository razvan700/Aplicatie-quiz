package com.jetbrains.aplicatiequiz.services;

import com.jetbrains.aplicatiequiz.dto.QuestionDTO;
import com.jetbrains.aplicatiequiz.exceptions.ResourceNotFoundException;
import com.jetbrains.aplicatiequiz.models.Question;

import java.util.List;


public interface QuestionService {

    Question createQuestion(Long quizId, Question question) throws ResourceNotFoundException;

    List<Question> getQuestionsByQuizId(Long quizId) throws ResourceNotFoundException;

    Question getQuestion(Long id) throws ResourceNotFoundException;

    Question updateQuestion(Long id, Question question) throws ResourceNotFoundException;

    void deleteQuestion(Long id) throws ResourceNotFoundException;

}
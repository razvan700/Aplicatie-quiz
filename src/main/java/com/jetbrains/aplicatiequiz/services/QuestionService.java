package com.jetbrains.aplicatiequiz.services;

import com.jetbrains.aplicatiequiz.dto.QuestionDTO;
import com.jetbrains.aplicatiequiz.exceptions.ResourceNotFoundException;

import java.util.List;


public interface QuestionService {

    QuestionDTO createQuestion(Long quizId, QuestionDTO questionDTO) throws ResourceNotFoundException;

    List<QuestionDTO> getQuestionsByQuizId(Long quizId) throws ResourceNotFoundException;

    QuestionDTO getQuestion(Long id) throws ResourceNotFoundException;

    QuestionDTO updateQuestion(Long id, QuestionDTO questionDTO) throws ResourceNotFoundException;

    void deleteQuestion(Long id) throws ResourceNotFoundException;

}
package com.jetbrains.aplicatiequiz.mappers;

import com.jetbrains.aplicatiequiz.dto.QuizDTO;
import com.jetbrains.aplicatiequiz.models.Quiz;
import org.springframework.stereotype.Component;

@Component
public class QuizMapper {

    public QuizDTO toDTO(Quiz quiz){
        QuizDTO dto = new QuizDTO();
        dto.setId(quiz.getId());
        dto.setQuestions(quiz.getQuestions());
        dto.setTitle(quiz.getTitle());
        return dto;
    }

    public Quiz toEntity(QuizDTO dto){
        Quiz quiz = new Quiz();
        quiz.setId(dto.getId());
        quiz.setQuestions(dto.getQuestions());
        quiz.setTitle(dto.getTitle());
        return quiz;
    }
}

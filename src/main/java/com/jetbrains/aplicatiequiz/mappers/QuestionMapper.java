package com.jetbrains.aplicatiequiz.mappers;

import com.jetbrains.aplicatiequiz.dto.QuestionDTO;
import com.jetbrains.aplicatiequiz.models.Question;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper {
    public QuestionDTO toDto(Question question){
        QuestionDTO dto= new QuestionDTO();
        dto.setId(question.getId());
        dto.setText(question.getText());
        dto.setType(question.getType());
        dto.setOptions(question.getOptions());
        dto.setCorrectAnswer(question.getCorrectAnswer());
        return dto;
    }

    public Question toEntity(QuestionDTO dto){
        Question question = new Question();
        question.setId(dto.getId());
        question.setOptions(dto.getOptions());
        question.setText(dto.getText());
        question.setType(dto.getType());
        question.setCorrectAnswer(dto.getCorrectAnswer());
        return question;
    }
}

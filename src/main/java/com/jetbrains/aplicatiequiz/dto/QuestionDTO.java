package com.jetbrains.aplicatiequiz.dto;

import com.jetbrains.aplicatiequiz.enums.QuestionType;
import com.jetbrains.aplicatiequiz.models.Question;

import java.util.List;
import java.util.stream.Collectors;

public class QuestionDTO {

    private Long id;
    private String text;
    private QuestionType type;

    private List<ChoiceDTO> choices;

    public QuestionDTO() {
    }



    public QuestionDTO(Question question) {
        this.id = question.getId();
        this.text = question.getText();
        this.type = question.getType();
        if (question.getChoices() != null) {
            this.choices = question.getChoices().stream()
                    .map(ChoiceDTO::new)
                    .collect(Collectors.toList());
        }
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public QuestionType getType() {
        return type;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<ChoiceDTO> getChoices() {
        return choices;
    }

    public void setChoices(List<ChoiceDTO> choices) {
        this.choices = choices;
    }
}

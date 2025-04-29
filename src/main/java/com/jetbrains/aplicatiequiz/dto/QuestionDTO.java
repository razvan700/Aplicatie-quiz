package com.jetbrains.aplicatiequiz.dto;

import com.jetbrains.aplicatiequiz.models.Question;

import java.util.List;
import java.util.stream.Collectors;

public class QuestionDTO {

    private Long id;
    private String text;
    private String type;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ChoiceDTO> getChoices() {
        return choices;
    }

    public void setChoices(List<ChoiceDTO> choices) {
        this.choices = choices;
    }
}

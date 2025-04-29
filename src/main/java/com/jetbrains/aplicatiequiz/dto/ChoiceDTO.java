package com.jetbrains.aplicatiequiz.dto;

import com.jetbrains.aplicatiequiz.models.Choice;

public class ChoiceDTO {

    private Long id;
    private String text;

    public ChoiceDTO() {
    }

    public ChoiceDTO(Choice choice) {
        this.id = choice.getId();
        this.text = choice.getText();
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
}

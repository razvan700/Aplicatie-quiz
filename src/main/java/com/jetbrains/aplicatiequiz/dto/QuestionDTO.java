package com.jetbrains.aplicatiequiz.dto;

import com.jetbrains.aplicatiequiz.models.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionDTO {

    private Long id;
    private String text;
    private String type;
    private Long quizId;

    private List<String> choiceTexts;


    private List<ChoiceDTO> choices;

    public QuestionDTO() {
    }

    public QuestionDTO(Question question) {
        this.id = question.getId();
        this.text = question.getText();
        this.type = question.getType();
        this.quizId = question.getQuiz() != null ? question.getQuiz().getId() : null;

        // Map Choice entities to DTOs
        if (question.getChoices() != null) {
            this.choices = new ArrayList<>();
            question.getChoices().forEach(choice -> this.choices.add(new ChoiceDTO(choice)));
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

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public List<String> getChoiceTexts() {
        return choiceTexts;
    }

    public void setChoiceTexts(List<String> choiceTexts) {
        this.choiceTexts = choiceTexts;
    }

    public List<ChoiceDTO> getChoices() {
        return choices;
    }

    public void setChoices(List<ChoiceDTO> choices) {
        this.choices = choices;
    }
}

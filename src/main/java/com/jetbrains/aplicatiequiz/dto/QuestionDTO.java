package com.jetbrains.aplicatiequiz.dto;

import com.jetbrains.aplicatiequiz.models.Question;
import java.util.List;

public class QuestionDTO {
    private Long id;
    private String text;
    private String type;
    private List<String> options;
    private List<String> correctAnswers;
    private Long quizId;


    public QuestionDTO() {
    }


    public QuestionDTO(Question question) {
        this.id = question.getId();
        this.text = question.getText();
        this.type = question.getType();
        this.options = question.getOptions();
        this.correctAnswers = question.getCorrectAnswers();
        this.quizId = question.getQuiz() != null ? question.getQuiz().getId() : null;
    }


    public QuestionDTO(String text, String type, List<String> options,
                       List<String> correctAnswers, Long quizId) {
        this.text = text;
        this.type = type;
        this.options = options;
        this.correctAnswers = correctAnswers;
        this.quizId = quizId;
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

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public List<String> getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(List<String> correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }
}
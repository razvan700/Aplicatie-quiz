package com.jetbrains.aplicatiequiz.dto;

import java.util.List;

public class AttemptDTO {
    private Long quizId;
    private List<AnswerDTO> answers;

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public List<AnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDTO> answers) {
        this.answers = answers;
    }
}

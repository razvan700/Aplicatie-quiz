package com.jetbrains.aplicatiequiz.dto;

import com.jetbrains.aplicatiequiz.models.Answer;
import com.jetbrains.aplicatiequiz.models.AnswerChoice;

import java.util.ArrayList;
import java.util.List;

public class AnswerDTO {
    private Long questionId;
    private String textResponse;
    private List<Long> selectedChoiceIds;

    public AnswerDTO() {
    }

    public AnswerDTO(Answer answer) {
        if (answer.getQuestion() != null) {
            this.questionId = answer.getQuestion().getId();
        }

        this.textResponse = answer.getTextResponse();

        this.selectedChoiceIds = new ArrayList<>();
        if (answer.getAnswerChoices() != null) {
            for (AnswerChoice ac : answer.getAnswerChoices()) {
                if (ac.getChoice() != null) {
                    this.selectedChoiceIds.add(ac.getChoice().getId());
                }
            }
        }
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getTextResponse() {
        return textResponse;
    }

    public void setTextResponse(String textResponse) {
        this.textResponse = textResponse;
    }

    public List<Long> getSelectedChoiceIds() {
        return selectedChoiceIds;
    }

    public void setSelectedChoiceIds(List<Long> selectedChoiceIds) {
        this.selectedChoiceIds = selectedChoiceIds;
    }
}

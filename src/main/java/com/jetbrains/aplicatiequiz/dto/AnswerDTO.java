package com.jetbrains.aplicatiequiz.dto;

import java.util.List;

public class AnswerDTO {
    private Long questionId;
    private String textResponse;
    private List<Long> selectedChoiceIds;

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

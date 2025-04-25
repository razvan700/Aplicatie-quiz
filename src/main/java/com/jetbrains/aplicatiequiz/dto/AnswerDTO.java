package com.jetbrains.aplicatiequiz.dto;

import com.jetbrains.aplicatiequiz.models.Answer;
import java.util.List;
import java.util.stream.Collectors;

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

    public AnswerDTO(Answer answer) {
        this.questionId = answer.getQuestion().getId();
        this.textResponse = answer.getTextResponse();
        this.selectedChoiceIds = answer.getAnswerChoices().stream()
                .map(ac -> ac.getChoice().getId())
                .collect(Collectors.toList());
    }
}

package com.jetbrains.aplicatiequiz.dto;

import com.jetbrains.aplicatiequiz.models.Attempt;

import java.util.List;
import java.util.stream.Collectors;

public class AttemptDTO {

    private Long quizId;
    private Long userId;
    private List<AnswerDTO> answers;

    private String shareableLink;

    public String getShareableLink() {
        return shareableLink;
    }

    public void setShareableLink(String shareableLink) {
        this.shareableLink = shareableLink;
    }

    public AttemptDTO() {
    }



    public AttemptDTO(Attempt attempt) {
        this.shareableLink = attempt.getShareableLink();
        this.answers = attempt.getAnswers().stream()
                .map(AnswerDTO::new)
                .collect(Collectors.toList());
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<AnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDTO> answers) {
        this.answers = answers;
    }
}

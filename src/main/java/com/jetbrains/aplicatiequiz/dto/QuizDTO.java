package com.jetbrains.aplicatiequiz.dto;

import com.jetbrains.aplicatiequiz.models.Quiz;

import java.util.List;
import java.util.stream.Collectors;

public class QuizDTO {

    private Long id;
    private String title;
    private String shareableLink;
    private List<QuestionDTO> questions;

    public QuizDTO() {
    }

    public QuizDTO(Quiz quiz) {
        this.id = quiz.getId();
        this.title = quiz.getTitle();
        this.shareableLink = quiz.getShareableLink();
        if (quiz.getQuestions() != null) {
            this.questions = quiz.getQuestions().stream()
                    .map(QuestionDTO::new)
                    .collect(Collectors.toList());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShareableLink() {
        return shareableLink;
    }

    public void setShareableLink(String shareableLink) {
        this.shareableLink = shareableLink;
    }

    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
    }
}

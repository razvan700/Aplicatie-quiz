package com.jetbrains.aplicatiequiz.dto;

import com.jetbrains.aplicatiequiz.models.Quiz;

public class QuizDTO {
    private Long id;
    private String title;
    private String shareableLink;

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

    public QuizDTO(Quiz quiz) {
        this.id = quiz.getId();
        this.title = quiz.getTitle();
        this.shareableLink = quiz.getShareableLink();
    }
}
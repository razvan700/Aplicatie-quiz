package com.jetbrains.aplicatiequiz.dto;

import com.jetbrains.aplicatiequiz.models.Question;
import com.jetbrains.aplicatiequiz.models.Quiz;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

public class QuizDTO {

    public QuizDTO(Quiz quiz){
        this.id = quiz.getId();
        this.title = quiz.getTitle();
        this.shareableLink = quiz.getShareableLink();
        this.questions = quiz.getQuestions();
    }

    private List<Question> questions;

    private Long id;

    private String title;

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

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


}

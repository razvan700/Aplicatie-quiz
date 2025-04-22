package com.jetbrains.aplicatiequiz.models;

import com.jetbrains.aplicatiequiz.dto.QuizDTO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<>();

    public Quiz() {

    }

    public Quiz(QuizDTO dto) {
        this.title = dto.getTitle();
    }

    public QuizDTO toQuizDto() {
        QuizDTO dto = new QuizDTO();
        dto.setTitle(this.getTitle());
        dto.setId(this.getId());
        return dto;
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

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}


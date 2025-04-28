package com.jetbrains.aplicatiequiz.models;

import com.jetbrains.aplicatiequiz.dto.QuizDTO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions = new ArrayList<>();

    @Column(unique = true, nullable = false)
    private String shareableLink;

    public Quiz() {
        this.shareableLink = UUID.randomUUID().toString();
    }

    public Quiz(QuizDTO dto) {
        this.title = dto.getTitle();
        this.shareableLink = UUID.randomUUID().toString();
        this.id = dto.getId();
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

    public String getShareableLink() {
        return shareableLink;
    }

    public void setShareableLink(String shareableLink) {
        this.shareableLink = shareableLink;
    }
}

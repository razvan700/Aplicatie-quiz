package com.jetbrains.aplicatiequiz.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Choice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @OneToMany(mappedBy = "choice", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AnswerChoice> answerChoices;
}

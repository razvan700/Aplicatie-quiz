package com.jetbrains.aplicatiequiz.models;

import jakarta.persistence.*;

import java.util.List;

public class AnswerChoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_id", nullable = false)
    public Long answerId;
    
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "choice_id", nullable = false)
    public Long choiceId;
}

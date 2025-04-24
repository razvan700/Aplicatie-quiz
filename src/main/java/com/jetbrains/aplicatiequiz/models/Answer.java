package com.jetbrains.aplicatiequiz.models;

import jakarta.persistence.*;

public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "question_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;

    @JoinColumn(name = "attempt_id", nullable = false)
    @ManyToOne
    private Attempt attempt;



}

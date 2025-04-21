package com.jetbrains.aplicatiequiz.dto;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

public class QuestionDTO {

    private Long id;

    private String text;

    private String type;

    private List<String> options;

    private List<String> correctAnswer;
}

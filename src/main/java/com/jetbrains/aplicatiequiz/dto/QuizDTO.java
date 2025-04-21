package com.jetbrains.aplicatiequiz.dto;

import com.jetbrains.aplicatiequiz.models.Question;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

public class QuizDTO {

    private Long id;

    private String title;

    private List<Question> questions = new ArrayList<>();
}

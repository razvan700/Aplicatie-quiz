package com.jetbrains.aplicatiequiz.dto;

import java.util.List;

public class AnswerDTO {
    private Long questionId;
    private String textResponse;
    private List<Long> selectedChoiceIds;
}

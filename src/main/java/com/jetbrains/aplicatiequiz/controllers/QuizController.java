package com.jetbrains.aplicatiequiz.controllers;

import com.jetbrains.aplicatiequiz.dto.QuizDTO;
import com.jetbrains.aplicatiequiz.mappers.QuizMapper;
import com.jetbrains.aplicatiequiz.models.Quiz;
import com.jetbrains.aplicatiequiz.services.QuizServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class QuizController {

    @Autowired
    private QuizServiceImplementation quizService;

    @Autowired
    private QuizMapper quizMapper;

    @PostMapping("/quiz/new")
    public ResponseEntity<QuizDTO> createQuiz(@RequestBody QuizDTO quizDTO){
        Quiz quiz = quizMapper.toEntity(quizDTO);
        Quiz createdQuiz = quizService.create(quiz);
        return ResponseEntity.ok(quizMapper.toDTO(createdQuiz));
    }



}

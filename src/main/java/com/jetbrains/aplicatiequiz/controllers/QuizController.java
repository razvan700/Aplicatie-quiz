package com.jetbrains.aplicatiequiz.controllers;

import com.jetbrains.aplicatiequiz.dto.QuizDTO;
import com.jetbrains.aplicatiequiz.mappers.QuizMapper;
import com.jetbrains.aplicatiequiz.models.Quiz;
import com.jetbrains.aplicatiequiz.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuizMapper quizMapper;

    @PostMapping("/quiz/new")
    public ResponseEntity<Quiz> createQuiz(@RequestBody QuizDTO quizDTO){
        Quiz quiz = quizMapper.toEntity(quizDTO);
        Quiz createdQuiz = quizService.save(quiz);
        return ResponseEntity.ok(quizMapper.toDTO(createdQuiz));
    }


}

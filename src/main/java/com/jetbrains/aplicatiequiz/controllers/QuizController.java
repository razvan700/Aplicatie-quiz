package com.jetbrains.aplicatiequiz.controllers;

import com.jetbrains.aplicatiequiz.models.Quiz;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class QuizController {
    @PostMapping("/quiz/new")
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz){
        return new ResponseEntity.ok(quizService.saveQuiz(quiz));
    }
}

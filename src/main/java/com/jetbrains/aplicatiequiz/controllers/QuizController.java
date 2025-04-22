package com.jetbrains.aplicatiequiz.controllers;

import com.jetbrains.aplicatiequiz.dto.QuizDTO;
import com.jetbrains.aplicatiequiz.services.QuizServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/")
public class QuizController {

    private QuizServiceImplementation quizService;

    @Autowired
    public QuizController(QuizServiceImplementation quizService) {
        this.quizService = quizService;
    }

    @PostMapping("/quiz/new")
    public ResponseEntity<QuizDTO> createQuiz(@RequestBody QuizDTO quizDTO){
        return ResponseEntity.ok(quizService.create(quizDTO));
    }

    @GetMapping("/quiz/list")
    public ResponseEntity<List<QuizDTO>> createQuiz(){
        return ResponseEntity.ok(quizService.list());
    }
}

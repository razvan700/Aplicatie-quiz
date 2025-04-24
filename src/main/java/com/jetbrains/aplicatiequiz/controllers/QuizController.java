package com.jetbrains.aplicatiequiz.controllers;

import com.jetbrains.aplicatiequiz.dto.QuizDTO;
import com.jetbrains.aplicatiequiz.services.QuizServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/quiz/{id}")
    public ResponseEntity<QuizDTO> getQuizById(@PathVariable Long id) {
        return ResponseEntity.ok(quizService.get(id));
    }

    @PostMapping("/quiz/update/{id}")
    public ResponseEntity<QuizDTO> updateQuiz(@PathVariable Long id, @RequestBody QuizDTO quizDTO) {
        quizDTO.setId(id);
        return ResponseEntity.ok(quizService.update(quizDTO));
    }

    @DeleteMapping("/quiz/delete/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long id) {
        quizService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/quiz/{id}/submit")
    public ResponseEntity<String> submitQuizAnswers(@PathVariable Long id, @RequestBody List<String> answers) {
        return ResponseEntity.ok("Answers submitted for quiz ID: " + id);
    }
    // work in progress
    @GetMapping("/quiz/{id}/responses")
    public ResponseEntity<List<String>> getQuizResponses(@PathVariable Long id) {
        // Placeholder for now
        return ResponseEntity.ok(List.of("Sample response 1", "Sample response 2"));
    }
}

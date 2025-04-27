package com.jetbrains.aplicatiequiz.controllers;

import com.jetbrains.aplicatiequiz.dto.QuizDTO;
import com.jetbrains.aplicatiequiz.models.Quiz;
import com.jetbrains.aplicatiequiz.services.QuizServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController("/")
public class QuizController {

    private QuizServiceImpl quizService;

    @Autowired
    public QuizController(QuizServiceImpl quizService) {
        this.quizService = quizService;
    }

    @SecurityRequirement(name = "JavaInUseSecurityScheme")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/quiz/new")
    public ResponseEntity<Quiz> createQuiz(@RequestBody QuizDTO quizDTO){
        Quiz quiz = new Quiz(quizDTO);
        return ResponseEntity.ok(quizService.create(quiz));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/quiz/list")
    public ResponseEntity<List<QuizDTO>> createQuiz(){
        List<Quiz> intermediary = quizService.list();
        List<QuizDTO> result = new ArrayList<>();
        for(Quiz q : intermediary){
            QuizDTO dto = new QuizDTO(q);
            result.add(dto);
        }
        return ResponseEntity.ok(result);
    }

    @SecurityRequirement(name = "JavaInUseSecurityScheme")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/quiz/{id}")
    public ResponseEntity<QuizDTO> getQuizById(@PathVariable Long id) {
        QuizDTO result = new QuizDTO(quizService.get(id));
        return ResponseEntity.ok(result);
    }


    @SecurityRequirement(name = "JavaInUseSecurityScheme")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/quiz/update/{id}")
    public ResponseEntity<QuizDTO> updateQuiz(@PathVariable Long id, @RequestBody QuizDTO quizDTO) {
        quizDTO.setId(id);
        Quiz quiz = new Quiz(quizDTO);
        quizService.update(quiz);
        return ResponseEntity.ok(quizDTO);
    }

    @SecurityRequirement(name = "JavaInUseSecurityScheme")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/quiz/delete/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long id) {
        quizService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

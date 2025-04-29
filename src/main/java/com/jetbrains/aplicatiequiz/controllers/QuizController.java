package com.jetbrains.aplicatiequiz.controllers;

import com.jetbrains.aplicatiequiz.dto.QuizDTO;
import com.jetbrains.aplicatiequiz.models.Quiz;
import com.jetbrains.aplicatiequiz.services.QuizServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class QuizController {

    private final QuizServiceImpl quizService;

    @Autowired
    public QuizController(QuizServiceImpl quizService) {
        this.quizService = quizService;
    }

    @SecurityRequirement(name = "JavaInUseSecurityScheme")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/quiz/new")
    public ResponseEntity<QuizDTO> createQuiz(@RequestBody QuizDTO quizDTO) {
        Quiz quiz = new Quiz(quizDTO);
        Quiz savedQuiz = quizService.create(quiz);
        return ResponseEntity.ok(new QuizDTO(savedQuiz));
    }

    @SecurityRequirement(name = "JavaInUseSecurityScheme")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/quiz/list")
    public ResponseEntity<List<QuizDTO>> listQuizzes() {
        List<QuizDTO> quizDTOs = quizService.list().stream()
                .map(QuizDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(quizDTOs);
    }

    @SecurityRequirement(name = "JavaInUseSecurityScheme")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/quiz/{id}")
    public ResponseEntity<QuizDTO> getQuizById(@PathVariable Long id) {
        Quiz quiz = quizService.get(id);
        return ResponseEntity.ok(new QuizDTO(quiz));
    }

    @SecurityRequirement(name = "JavaInUseSecurityScheme")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/quiz/{id}")
    public ResponseEntity<QuizDTO> updateQuiz(@PathVariable Long id, @RequestBody QuizDTO quizDTO) {
        quizDTO.setId(id);
        Quiz updatedQuiz = quizService.updateWithNested(id, quizDTO);
        return ResponseEntity.ok(new QuizDTO(updatedQuiz));
    }

    @SecurityRequirement(name = "JavaInUseSecurityScheme")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/quiz/delete/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long id) {
        quizService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

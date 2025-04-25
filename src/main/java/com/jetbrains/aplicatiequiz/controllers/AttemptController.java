package com.jetbrains.aplicatiequiz.controllers;

import com.jetbrains.aplicatiequiz.dto.AttemptDTO;
import com.jetbrains.aplicatiequiz.services.AttemptService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attempt")
public class AttemptController {

    private final AttemptService attemptService;

    public AttemptController(AttemptService attemptService) {
        this.attemptService = attemptService;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/submit")
    public ResponseEntity<AttemptDTO> submitAttempt(@RequestBody AttemptDTO attemptDTO) {
        AttemptDTO savedAttempt = new AttemptDTO(attemptService.submitAttempt(attemptDTO));
        return ResponseEntity.ok(savedAttempt);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AttemptDTO>> getAttemptsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(attemptService.getAttemptsByUserId(userId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<List<AttemptDTO>> getAttemptsByQuiz(@PathVariable Long quizId) {
        return ResponseEntity.ok(attemptService.getAttemptsByQuizId(quizId));
    }
}

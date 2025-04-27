package com.jetbrains.aplicatiequiz.controllers;

import com.jetbrains.aplicatiequiz.dto.AttemptDTO;
import com.jetbrains.aplicatiequiz.services.AttemptService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attempt")
public class AttemptController {

    private final AttemptService attemptService;

    public AttemptController(AttemptService attemptService) {
        this.attemptService = attemptService;
    }

    @SecurityRequirement(name = "JavaInUseSecurityScheme")
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/submit/{quizId}")
    public ResponseEntity<AttemptDTO> submitAttempt(@AuthenticationPrincipal UserDetails userDetails,
                                                    @PathVariable Long quizId,
                                                    @RequestBody AttemptDTO attemptDTO) {
        String username = userDetails.getUsername();
        AttemptDTO savedAttempt = new AttemptDTO(attemptService.submitAttempt(attemptDTO, username, quizId));
        return ResponseEntity.ok(savedAttempt);
    }

    @SecurityRequirement(name = "JavaInUseSecurityScheme")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AttemptDTO>> getAttemptsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(attemptService.getAttemptsByUserId(userId));
    }

    @SecurityRequirement(name = "JavaInUseSecurityScheme")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<List<AttemptDTO>> getAttemptsByQuiz(@PathVariable Long quizId) {
        return ResponseEntity.ok(attemptService.getAttemptsByQuizId(quizId));
    }
}

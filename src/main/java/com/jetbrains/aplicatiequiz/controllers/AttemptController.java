package com.jetbrains.aplicatiequiz.controllers;

import com.jetbrains.aplicatiequiz.dto.AttemptDTO;
import com.jetbrains.aplicatiequiz.models.Attempt;
import com.jetbrains.aplicatiequiz.services.AttemptService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
        Attempt attempt = new Attempt();
        attempt.setAttemptDate(attemptDTO.getAttemptDate());
        attempt.setTimestamp(attemptDTO.getTimestamp());
        attempt.setShareableLink(attemptDTO.getShareableLink());

        Attempt savedAttempt = attemptService.submitAttempt(attempt, username, quizId);

        return ResponseEntity.ok(new AttemptDTO(savedAttempt));
    }

    @SecurityRequirement(name = "JavaInUseSecurityScheme")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AttemptDTO>> getAttemptsByUser(@PathVariable Long userId) {
        List<AttemptDTO> attempts = attemptService.getAttemptsByUserId(userId).stream()
                .map(AttemptDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(attempts);
    }

    @SecurityRequirement(name = "JavaInUseSecurityScheme")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<List<AttemptDTO>> getAttemptsByQuiz(@PathVariable Long quizId) {
        List<AttemptDTO> attempts = attemptService.getAttemptsByQuizId(quizId).stream()
                .map(AttemptDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(attempts);
    }
}

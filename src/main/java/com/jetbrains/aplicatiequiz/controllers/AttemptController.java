package com.jetbrains.aplicatiequiz.controllers;

import com.jetbrains.aplicatiequiz.dto.AnswerDTO;
import com.jetbrains.aplicatiequiz.dto.AttemptDTO;
import com.jetbrains.aplicatiequiz.models.*;
import com.jetbrains.aplicatiequiz.repositories.QuestionRepository;
import com.jetbrains.aplicatiequiz.services.AttemptService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/attempt")
public class AttemptController {

    private final AttemptService attemptService;

    private final QuestionRepository questionRepository;

    public AttemptController(AttemptService attemptService, QuestionRepository questionRepository) {

        this.attemptService = attemptService;
        this.questionRepository = questionRepository;
    }

    @SecurityRequirement(name = "JavaInUseSecurityScheme")
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/submit/{quizId}")
    public ResponseEntity<AttemptDTO> submitAttempt(@AuthenticationPrincipal UserDetails userDetails,
                                                    @PathVariable Long quizId,
                                                    @RequestBody AttemptDTO attemptDTO) {
        String username = userDetails.getUsername();

        Attempt attempt = new Attempt();
        attempt.setAttemptDate(LocalDateTime.now());
        attempt.setTimestamp(LocalDateTime.now());
        attempt.setShareableLink(attemptDTO.getShareableLink());

        List<Answer> answers = new ArrayList<>();

        for (AnswerDTO answerDTO : attemptDTO.getAnswers()) {
            Answer answer = new Answer();
            answer.setTextResponse(answerDTO.getTextResponse());
            answer.setSelectedChoiceIds(answerDTO.getSelectedChoiceIds());

            Question question = questionRepository.findById(answerDTO.getQuestionId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Question not found"));
            answer.setQuestion(question);

            answer.setAttempt(attempt);
            answers.add(answer);
        }

        attempt.setAnswers(answers);

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

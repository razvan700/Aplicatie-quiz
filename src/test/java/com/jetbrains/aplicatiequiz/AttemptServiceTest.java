package com.jetbrains.aplicatiequiz;

import com.jetbrains.aplicatiequiz.models.*;
import com.jetbrains.aplicatiequiz.repositories.*;
import com.jetbrains.aplicatiequiz.services.AttemptServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AttemptServiceTest {

    private final AttemptRepository attemptRepo = mock(AttemptRepository.class);
    private final UserRepository userRepo = mock(UserRepository.class);
    private final QuizRepository quizRepo = mock(QuizRepository.class);
    private final QuestionRepository questionRepo = mock(QuestionRepository.class);
    private final ChoiceRepository choiceRepo = mock(ChoiceRepository.class);
    private final AnswerRepository answerRepo = mock(AnswerRepository.class);

    private final AttemptServiceImpl service = new AttemptServiceImpl(
            attemptRepo, userRepo, quizRepo, questionRepo, choiceRepo, answerRepo
    );


    @Test
    void submitAttempt_validData_savesSuccessfully() {
        User user = new User(); user.setUsername("john");
        Quiz quiz = new Quiz(); quiz.setId(1L); quiz.setShareableLink("link");

        Attempt attempt = new Attempt();
        attempt.setShareableLink("link");

        when(userRepo.findByUsername("john")).thenReturn(Optional.of(user));
        when(quizRepo.findById(1L)).thenReturn(Optional.of(quiz));
        when(attemptRepo.save(any())).thenReturn(attempt);

        Attempt result = service.submitAttempt(attempt, "john", 1L);
        assertNotNull(result);
    }

    @Test
    void submitAttempt_wrongLink_throwsException() {
        User user = new User(); user.setUsername("john");
        Quiz quiz = new Quiz(); quiz.setId(1L); quiz.setShareableLink("correct");

        Attempt attempt = new Attempt();
        attempt.setShareableLink("wrong");

        when(userRepo.findByUsername("john")).thenReturn(Optional.of(user));
        when(quizRepo.findById(1L)).thenReturn(Optional.of(quiz));

        assertThrows(ResponseStatusException.class, () -> {
            service.submitAttempt(attempt, "john", 1L);
        });
    }
}

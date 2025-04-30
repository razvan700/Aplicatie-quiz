package com.jetbrains.aplicatiequiz;

import com.jetbrains.aplicatiequiz.dto.*;
import com.jetbrains.aplicatiequiz.models.*;
import com.jetbrains.aplicatiequiz.repositories.*;
import com.jetbrains.aplicatiequiz.services.QuizServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class QuizServiceTest {

    private final QuizRepository quizRepo = mock(QuizRepository.class);
    private final AnswerRepository answerRepo = mock(AnswerRepository.class);
    private final AnswerChoiceRepository answerChoiceRepo = mock(AnswerChoiceRepository.class);
    private final ChoiceRepository choiceRepo = mock(ChoiceRepository.class);
    private final QuestionRepository questionRepo = mock(QuestionRepository.class);

    private final QuizServiceImpl service = new QuizServiceImpl(
            quizRepo, answerChoiceRepo, answerRepo, choiceRepo, questionRepo
    );

    @Test
    void updateWithNested_updatesQuizAndQuestions() {
        Quiz existing = new Quiz();
        existing.setId(1L);
        existing.setTitle("Old");

        when(quizRepo.findById(1L)).thenReturn(Optional.of(existing));
        when(quizRepo.save(any())).thenAnswer(i -> i.getArgument(0));

        QuizDTO dto = new QuizDTO();
        dto.setId(1L);
        dto.setTitle("New");

        QuestionDTO q = new QuestionDTO();
        q.setText("Updated?");
        q.setChoices(List.of(new ChoiceDTO("Yes")));

        dto.setQuestions(List.of(q));

        Quiz result = service.updateWithNested(1L, dto);
        assertEquals("New", result.getTitle());
        assertEquals(1, result.getQuestions().size());
    }
}

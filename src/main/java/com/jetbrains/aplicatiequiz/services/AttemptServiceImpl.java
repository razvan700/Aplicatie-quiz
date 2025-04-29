package com.jetbrains.aplicatiequiz.services;

import com.jetbrains.aplicatiequiz.models.*;
import com.jetbrains.aplicatiequiz.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AttemptServiceImpl implements AttemptService {

    private final AttemptRepository attemptRepository;
    private final UserRepository userRepository;
    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final ChoiceRepository choiceRepository;
    private final AnswerRepository answerRepository;

    public AttemptServiceImpl(
            AttemptRepository attemptRepository,
            UserRepository userRepository,
            QuizRepository quizRepository,
            QuestionRepository questionRepository,
            ChoiceRepository choiceRepository,
            AnswerRepository answerRepository) {
        this.attemptRepository = attemptRepository;
        this.userRepository = userRepository;
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
        this.choiceRepository = choiceRepository;
        this.answerRepository = answerRepository;
    }

    @Override
    public Attempt submitAttempt(Attempt attempt, String username, Long quizId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new EntityNotFoundException("Quiz not found"));

        attempt.setUser(user);
        attempt.setQuiz(quiz);
        attempt.setTimestamp(LocalDateTime.now());
        attempt.setAttemptDate(LocalDateTime.now());
        attempt.setShareableLink(quiz.getShareableLink());

        if (attempt.getAnswers() != null) {
            for (Answer answer : attempt.getAnswers()) {
                Question question = questionRepository.findById(answer.getQuestionId())
                        .orElseThrow(() -> new EntityNotFoundException("Question not found"));

                answer.setAttempt(attempt);
                answer.setQuestion(question);

                if (answer.getSelectedChoiceIds() != null) {
                    List<AnswerChoice> answerChoices = new ArrayList<>();
                    for (Long choiceId : answer.getSelectedChoiceIds()) {
                        Choice choice = choiceRepository.findById(choiceId)
                                .orElseThrow(() -> new EntityNotFoundException("Choice not found"));

                        AnswerChoice answerChoice = new AnswerChoice();
                        answerChoice.setAnswer(answer);
                        answerChoice.setChoice(choice);
                        answerChoices.add(answerChoice);
                    }
                    answer.setAnswerChoices(answerChoices);
                }
            }
        }

        return attemptRepository.save(attempt);
    }

    @Override
    public List<Attempt> getAttemptsByUserId(Long userId) {
        return attemptRepository.findByUserId(userId);
    }

    @Override
    public List<Attempt> getAttemptsByQuizId(Long quizId) {
        return attemptRepository.findByQuizId(quizId);
    }

    @Override
    public Attempt getAttemptById(Long attemptId) {
        return attemptRepository.findById(attemptId)
                .orElseThrow(() -> new EntityNotFoundException("Attempt not found"));
    }
}

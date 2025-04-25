package com.jetbrains.aplicatiequiz.services;

import com.jetbrains.aplicatiequiz.dto.AnswerDTO;
import com.jetbrains.aplicatiequiz.dto.AttemptDTO;
import com.jetbrains.aplicatiequiz.models.*;
import com.jetbrains.aplicatiequiz.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public Attempt submitAttempt(AttemptDTO attemptDTO, String username, Long quizId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new EntityNotFoundException("Quiz not found"));

        Attempt attempt = new Attempt();
        attempt.setUser(user);
        attempt.setQuiz(quiz);
        attempt.setTimestamp(LocalDateTime.now());


        attempt.setShareableLink(quiz.getShareableLink());

        List<Answer> answers = new ArrayList<>();

        for (AnswerDTO answerDTO : attemptDTO.getAnswers()) {
            Question question = questionRepository.findById(answerDTO.getQuestionId())
                    .orElseThrow(() -> new EntityNotFoundException("Question not found"));

            Answer answer = new Answer();
            answer.setAttempt(attempt);
            answer.setQuestion(question);
            answer.setTextResponse(answerDTO.getTextResponse());

            if (answerDTO.getSelectedChoiceIds() != null) {
                List<AnswerChoice> answerChoices = new ArrayList<>();
                for (Long choiceId : answerDTO.getSelectedChoiceIds()) {
                    Choice choice = choiceRepository.findById(choiceId)
                            .orElseThrow(() -> new RuntimeException("Choice not found"));

                    AnswerChoice answerChoice = new AnswerChoice();
                    answerChoice.setAnswer(answer);
                    answerChoice.setChoice(choice);
                    answerChoices.add(answerChoice);
                }
                answer.setAnswerChoices(answerChoices);
            }

            answers.add(answer);
        }

        attempt.setAnswers(answers);

        return attemptRepository.save(attempt);
    }



    @Override
        public List<AttemptDTO> getAttemptsByUserId(Long userId) {
            List<Attempt> attempts = attemptRepository.findByUserId(userId);
            return attempts.stream()
                    .map(AttemptDTO::new)
                    .toList();
        }

        @Override
        public List<AttemptDTO> getAttemptsByQuizId(Long quizId) {
            return attemptRepository.findByQuizId(quizId)
                    .stream()
                    .map(AttemptDTO::new)
                    .collect(Collectors.toList());
        }

        @Override
        public Attempt getAttemptById(Long attemptId) {
            return attemptRepository.findById(attemptId)
                    .orElseThrow(() -> new EntityNotFoundException("Attempt not found"));
        }
    }



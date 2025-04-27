package com.jetbrains.aplicatiequiz.services;

import com.jetbrains.aplicatiequiz.dto.QuizDTO;
import com.jetbrains.aplicatiequiz.models.Question;
import com.jetbrains.aplicatiequiz.models.Quiz;
import com.jetbrains.aplicatiequiz.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Transactional
@Service
public class QuizServiceImpl implements QuizService{

    private final Logger logger = Logger.getLogger(QuizServiceImpl.class.getName());

    private final QuizRepository quizRepository;

    private final AnswerChoiceRepository answerChoiceRepository;

    private final AnswerRepository answerRepository;

    private final ChoiceRepository choiceRepository;

    private final QuestionRepository questionRepository;

    @Value("${app.base-url:http://localhost:8080}")
    private String baseUrl;

    public QuizServiceImpl(QuizRepository quizRepository,
                           AnswerChoiceRepository answerChoiceRepository,
                           AnswerRepository answerRepository,
                           ChoiceRepository choiceRepository,
                           QuestionRepository questionRepository
                            ) {
        this.quizRepository = quizRepository;
        this.answerChoiceRepository = answerChoiceRepository;
        this.answerRepository = answerRepository;
        this.choiceRepository = choiceRepository;
        this.questionRepository = questionRepository;
    }

    public QuizDTO create(QuizDTO dto) {
        logger.info("Creating new quiz: " + dto.getTitle());

        Quiz quiz = quizRepository.save(new Quiz(dto));

        QuizDTO resultDto = quiz.toQuizDto();

        String shareableLink = baseUrl + "/quiz/" + quiz.getId();
        resultDto.setShareableLink(shareableLink);

        return resultDto;
    }

    @Override
    public List<QuizDTO> list() {
        logger.info("Listing all quizzes");
        return quizRepository.findAll().stream()
                .map(Quiz::toQuizDto).collect(Collectors.toList());
    }

    @Override
    public QuizDTO get(Long id) {
        logger.info("Fetching quiz by ID: " + id);
        return quizRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz not found with ID: " + id))
                .toQuizDto();
    }

    @Override
    public QuizDTO update(QuizDTO dto) {
        logger.info("Updating quiz with ID: " + dto.getId());
        return quizRepository.save(new Quiz(dto)).toQuizDto();
    }

    @Override
    public Boolean delete(Long id) {
        logger.info("Deleting quiz with ID: " + id);
        Optional<Quiz> existingQuiz = quizRepository.findById(id);
        if (existingQuiz.isPresent()) {


            quizRepository.deleteById(id);
            return true;
        }
        return false;
    }
}



package com.jetbrains.aplicatiequiz.services;

import com.jetbrains.aplicatiequiz.models.Quiz;
import com.jetbrains.aplicatiequiz.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

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


    @Override
    public Quiz create(Quiz quiz) {
        logger.info("Creating new quiz: " + quiz.getTitle());
        quiz = quizRepository.save(quiz);
        String shareableLink = baseUrl + "/quiz/" + quiz.getId();
        quiz.setShareableLink(shareableLink);
        return quizRepository.save(quiz);
    }

    @Override
    public List<Quiz> list() {
        logger.info("Listing all quizzes");
        return quizRepository.findAll();
    }

    @Override
    public Quiz get(Long id) {
        logger.info("Fetching quiz by ID: " + id);
        return quizRepository.findQuizById(id);
    }

    @Override
    public Quiz update(Quiz quiz) {
        logger.info("Updating quiz with ID: " + quiz.getId());
        return quizRepository.save(quiz);
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



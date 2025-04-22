package com.jetbrains.aplicatiequiz.services;

import com.jetbrains.aplicatiequiz.dto.QuestionDTO;
import com.jetbrains.aplicatiequiz.models.Question;
import com.jetbrains.aplicatiequiz.repositories.QuestionRepository;
import com.jetbrains.aplicatiequiz.repositories.QuizRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

    private final Logger logger = Logger.getLogger(QuestionServiceImpl.class.getName());

    private final QuizRepository quizRepository;

    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuizRepository quizRepository,
                               QuestionRepository questionRepository) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public QuestionDTO createQuestion(Long quizId, QuestionDTO questionDTO) {
        logger.info("Saving new question..");

        Question newQuestion = new Question(questionDTO);
        questionRepository.save(newQuestion);
        return questionDTO;
    }

    @Override
    public List<QuestionDTO> getQuestionsByQuiz(Long quizId) {
        logger.info("Fetching quiz by ID: " + quizId);
//        return questionRepository.findAllByQuizId(quizId)
//                .orElseThrow(() -> new RuntimeException("Quiz not found with ID: " + quizId));
        return new ArrayList<>();
    }

    @Override
    public QuestionDTO getQuestion(Long id) {
        return null;
    }

    @Override
    public QuestionDTO updateQuestion(Long id, QuestionDTO question) {
        return null;
    }

    @Override
    public boolean deleteQuestion(Long id) {
        return false;
    }
}

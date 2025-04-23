package com.jetbrains.aplicatiequiz.services;

import com.jetbrains.aplicatiequiz.dto.QuestionDTO;
import com.jetbrains.aplicatiequiz.exceptions.ResourceNotFoundException;
import com.jetbrains.aplicatiequiz.models.Question;
import com.jetbrains.aplicatiequiz.models.Quiz;
import com.jetbrains.aplicatiequiz.repositories.QuestionRepository;
import com.jetbrains.aplicatiequiz.repositories.QuizRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
        logger.info("Creating new question for quiz ID: " + quizId);

        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found with ID: " + quizId));

        Question newQuestion = new Question();
        newQuestion.setText(questionDTO.getText());
        newQuestion.setType(questionDTO.getType());
        newQuestion.setOptions(questionDTO.getOptions());
        newQuestion.setCorrectAnswers(questionDTO.getCorrectAnswers());
        newQuestion.setQuiz(quiz);

        Question savedQuestion = questionRepository.save(newQuestion);
        return new QuestionDTO(savedQuestion);
    }

    @Override
    public List<QuestionDTO> getQuestionsByQuizId(Long quizId) {
        logger.info("Fetching questions for quiz ID: " + quizId);

        if (!quizRepository.existsById(quizId)) {
            throw new ResourceNotFoundException("Quiz not found with ID: " + quizId);
        }

        return questionRepository.findQuestionsByQuizId(quizId);
    }

    @Override
    public QuestionDTO getQuestion(Long id) {
        return questionRepository.findQuestionDTOById(id);
    }


    @Override
    public QuestionDTO updateQuestion(Long id, QuestionDTO questionDTO) {
        Question existingQuestion = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with ID: " + id));

        existingQuestion.setText(questionDTO.getText());
        existingQuestion.setType(questionDTO.getType());
        existingQuestion.setOptions(questionDTO.getOptions());
        existingQuestion.setCorrectAnswers(questionDTO.getCorrectAnswers());

        if (questionDTO.getQuizId() != null &&
                !questionDTO.getQuizId().equals(existingQuestion.getQuiz().getId())) {
            Quiz newQuiz = quizRepository.findById(questionDTO.getQuizId())
                    .orElseThrow(() -> new ResourceNotFoundException("Quiz not found with ID: " + questionDTO.getQuizId()));
            existingQuestion.setQuiz(newQuiz);
        }

        Question updatedQuestion = questionRepository.save(existingQuestion);
        return new QuestionDTO(updatedQuestion);
    }

    @Override
    public void deleteQuestion(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with ID: " + id));
        questionRepository.delete(question);
    }
}
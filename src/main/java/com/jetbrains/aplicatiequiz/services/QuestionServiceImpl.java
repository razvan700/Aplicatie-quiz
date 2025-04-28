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
    public Question createQuestion(Long quizId, Question question) {
        logger.info("Creating new question for quiz ID: " + quizId);

        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found with ID: " + quizId));

        Question newQuestion = new Question();
        newQuestion.setText(question.getText());
        newQuestion.setType(question.getType());
        newQuestion.setQuiz(quiz);


        return newQuestion;
    }


    @Override
    public List<Question> getQuestionsByQuizId(Long quizId) {
        logger.info("Fetching questions for quiz ID: " + quizId);

        if (!quizRepository.existsById(quizId)) {
            throw new ResourceNotFoundException("Quiz not found with ID: " + quizId);
        }

        return questionRepository.findQuestionsByQuizId(quizId);
    }

    @Override
    public Question getQuestion(Long id) {
        return questionRepository.findQuestionById(id);
    }


    @Override
    public Question updateQuestion(Long id, Question question) {
        Question existingQuestion = questionRepository.findQuestionById(id);
        existingQuestion.setText(question.getText());
        existingQuestion.setType(question.getType());
        questionRepository.save(existingQuestion);
        return existingQuestion;
    }

    @Override
    public void deleteQuestion(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with ID: " + id));
        questionRepository.delete(question);
    }
}
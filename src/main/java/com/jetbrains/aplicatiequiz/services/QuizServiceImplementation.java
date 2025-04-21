package com.jetbrains.aplicatiequiz.services;

import com.jetbrains.aplicatiequiz.models.Quiz;
import com.jetbrains.aplicatiequiz.repositories.QuizRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.logging.Logger;

@Transactional
@Service
public class QuizServiceImplementation implements QuizService{

    private final Logger logger = Logger.getLogger(QuizServiceImplementation.class.getName());

    private final QuizRepository quizRepository;

    public QuizServiceImplementation(QuizRepository quizRepository){
        this.quizRepository = quizRepository;
    }

    public Quiz create(Quiz quiz){
        logger.info("Creating new quiz:" + quiz.getTitle());
        return quizRepository.save(quiz);
    }

    @Override
    public Collection<Quiz> list(){
        logger.info("Listing all quizzes");
        return quizRepository.findAll();
    }

    @Override
    public Quiz get(Long id) {
        logger.info("Fetching quiz by ID: " + id);
        return quizRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz not found with ID: " + id));
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



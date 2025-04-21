package com.jetbrains.aplicatiequiz.services;

import com.jetbrains.aplicatiequiz.repositories.QuestionRepository;
import com.jetbrains.aplicatiequiz.repositories.QuizRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
@Transactional
public class QuestionServiceImplementation implements QuestionService{

    private final Logger logger = Logger.getLogger(QuestionServiceImplementation.class.getName());

    private final QuizRepository quizRepository;

    private final QuestionRepository questionRepository;



}

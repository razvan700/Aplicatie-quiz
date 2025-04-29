package com.jetbrains.aplicatiequiz.services;

import com.jetbrains.aplicatiequiz.dto.QuizDTO;
import com.jetbrains.aplicatiequiz.dto.QuestionDTO;
import com.jetbrains.aplicatiequiz.dto.ChoiceDTO;
import com.jetbrains.aplicatiequiz.models.Choice;
import com.jetbrains.aplicatiequiz.models.Question;
import com.jetbrains.aplicatiequiz.models.Quiz;
import com.jetbrains.aplicatiequiz.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Transactional
@Service
public class QuizServiceImpl implements QuizService {

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
                           QuestionRepository questionRepository) {
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

    @Override
    public Quiz updateWithNested(Long id, QuizDTO quizDTO) {
        Quiz existingQuiz = quizRepository.findById(quizDTO.getId())
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        existingQuiz.setTitle(quizDTO.getTitle());

        Map<Long, Question> existingQuestionsMap = new HashMap<>();
        if (existingQuiz.getQuestions() != null) {
            for (Question q : existingQuiz.getQuestions()) {
                if (q.getId() != null) {
                    existingQuestionsMap.put(q.getId(), q);
                }
            }
        }

        List<Question> updatedQuestions = new ArrayList<>();

        for (QuestionDTO questionDTO : quizDTO.getQuestions()) {
            Question question;
            if (questionDTO.getId() != null && existingQuestionsMap.containsKey(questionDTO.getId())) {
                question = existingQuestionsMap.get(questionDTO.getId());
            } else {
                question = new Question();
            }

            question.setText(questionDTO.getText());
            question.setType(questionDTO.getType());
            question.setQuiz(existingQuiz);

            Map<Long, Choice> existingChoicesMap = new HashMap<>();
            if (question.getChoices() != null) {
                for (Choice choice : question.getChoices()) {
                    if (choice.getId() != null) {
                        existingChoicesMap.put(choice.getId(), choice);
                    }
                }
            }

            List<Choice> updatedChoices = new ArrayList<>();
            for (ChoiceDTO choiceDTO : questionDTO.getChoices()) {
                Choice choice;
                if (choiceDTO.getId() != null && existingChoicesMap.containsKey(choiceDTO.getId())) {
                    choice = existingChoicesMap.get(choiceDTO.getId());
                } else {
                    choice = new Choice();
                }

                choice.setText(choiceDTO.getText());
                choice.setQuestion(question);
                updatedChoices.add(choice);
            }

            question.getChoices().clear();
            question.getChoices().addAll(updatedChoices);
            updatedQuestions.add(question);
        }

        existingQuiz.getQuestions().clear();
        existingQuiz.getQuestions().addAll(updatedQuestions);

        return quizRepository.save(existingQuiz);
    }



}

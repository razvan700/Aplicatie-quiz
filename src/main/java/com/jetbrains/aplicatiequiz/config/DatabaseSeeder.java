package com.jetbrains.aplicatiequiz.config;

import com.jetbrains.aplicatiequiz.enums.Role;
import com.jetbrains.aplicatiequiz.models.*;
import com.jetbrains.aplicatiequiz.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class DatabaseSeeder {

//    @Bean
//    CommandLineRunner initDatabase(UserRepository userRepository,
//                                   QuizRepository quizRepository,
//                                   QuestionRepository questionRepository,
//                                   ChoiceRepository choiceRepository,
//                                   AttemptRepository attemptRepository,
//                                   AnswerRepository answerRepository,
//                                   PasswordEncoder passwordEncoder) {
//        return args -> {
//            if (userRepository.findByUsername("admin").isEmpty()) {
//                User admin = new User();
//                admin.setUsername("admin");
//                admin.setPassword(passwordEncoder.encode("admin123"));
//                admin.setRole(Role.ADMIN);
//                userRepository.save(admin);
//            }
//
//            if (userRepository.findByUsername("user").isEmpty()) {
//                User user = new User();
//                user.setUsername("user");
//                user.setPassword(passwordEncoder.encode("user123"));
//                user.setRole(Role.USER);
//                userRepository.save(user);
//            }
//
//            if (quizRepository.count() == 0) {
//                Quiz javaQuiz = new Quiz();
//                javaQuiz.setTitle("Java Basics Quiz");
//                javaQuiz.setShareableLink("java-basics");
//                quizRepository.save(javaQuiz);
//
//                Quiz springQuiz = new Quiz();
//                springQuiz.setTitle("Spring Boot Quiz");
//                springQuiz.setShareableLink("spring-boot");
//                quizRepository.save(springQuiz);
//
//                Question q1 = createQuestion("What is the size of int in Java?", "MULTIPLE_CHOICE", javaQuiz, questionRepository);
//                Question q2 = createQuestion("Which company created Java?", "MULTIPLE_CHOICE", javaQuiz, questionRepository);
//
//                Question q3 = createQuestion("What annotation starts Spring Boot?", "MULTIPLE_CHOICE", springQuiz, questionRepository);
//                Question q4 = createQuestion("What does @Autowired do?", "MULTIPLE_CHOICE", springQuiz, questionRepository);
//
//                Choice c1 = createChoice("4 bytes", q1, choiceRepository);
//                Choice c2 = createChoice("8 bytes", q1, choiceRepository);
//
//                Choice c3 = createChoice("Sun Microsystems", q2, choiceRepository);
//                Choice c4 = createChoice("Microsoft", q2, choiceRepository);
//
//                Choice c5 = createChoice("@SpringBootApplication", q3, choiceRepository);
//                Choice c6 = createChoice("@ComponentScan", q3, choiceRepository);
//
//                Choice c7 = createChoice("Injects dependencies", q4, choiceRepository);
//                Choice c8 = createChoice("Starts a new thread", q4, choiceRepository);
//
//                Attempt attempt = new Attempt();
//                attempt.setUser(userRepository.findByUsername("user").orElseThrow());
//                attempt.setQuiz(javaQuiz);
//                attempt.setAttemptDate(LocalDateTime.now());
//                attempt.setTimestamp(LocalDateTime.now());
//                attempt.setShareableLink(javaQuiz.getShareableLink());
//                attempt = attemptRepository.save(attempt);
//
//                Answer answer1 = new Answer();
//                answer1.setAttempt(attempt);
//                answer1.setQuestion(q1);
//                answer1.setTextResponse(null);
//                answer1 = answerRepository.save(answer1);
//
//                Answer answer2 = new Answer();
//                answer2.setAttempt(attempt);
//                answer2.setQuestion(q2);
//                answer2.setTextResponse(null);
//                answer2 = answerRepository.save(answer2);
//
//                AnswerChoice ac1 = new AnswerChoice();
//                ac1.setAnswer(answer1);
//                ac1.setChoice(c1);
//
//                AnswerChoice ac2 = new AnswerChoice();
//                ac2.setAnswer(answer2);
//                ac2.setChoice(c3);
//
//                answer1.setAnswerChoices(List.of(ac1));
//                answer2.setAnswerChoices(List.of(ac2));
//
//                answerRepository.save(answer1);
//                answerRepository.save(answer2);
//            }
//
//            System.out.println("Database seeding completed or skipped if already existing.");
//        };
//    }

    private Question createQuestion(String text, String type, Quiz quiz, QuestionRepository questionRepository) {
        Question question = new Question();
        question.setText(text);
        question.setType(type);
        question.setQuiz(quiz);
        return questionRepository.save(question);
    }

    private Choice createChoice(String text, Question question, ChoiceRepository choiceRepository) {
        Choice choice = new Choice();
        choice.setText(text);
        choice.setQuestion(question);
        return choiceRepository.save(choice);
    }
}

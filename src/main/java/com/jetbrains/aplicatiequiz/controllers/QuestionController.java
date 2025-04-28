package com.jetbrains.aplicatiequiz.controllers;

import com.jetbrains.aplicatiequiz.dto.QuestionDTO;
import com.jetbrains.aplicatiequiz.models.Question;
import com.jetbrains.aplicatiequiz.services.QuestionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @SecurityRequirement(name = "JavaInUseSecurityScheme")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/quiz/{quizId}/questions")
    public ResponseEntity<List<QuestionDTO>> getQuestionsByQuizId(@PathVariable Long quizId) {
        List<QuestionDTO> result= new ArrayList<>();
        List<Question> obtained = questionService.getQuestionsByQuizId(quizId);
        for(Question q : obtained){
            QuestionDTO dto = new QuestionDTO(q);
            result.add(dto);
        }
        return ResponseEntity.ok(result);
    }

    @SecurityRequirement(name = "JavaInUseSecurityScheme")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public ResponseEntity<QuestionDTO> getQuestionById(@PathVariable Long id) {
        QuestionDTO result = new QuestionDTO(questionService.getQuestion(id));
        return ResponseEntity.ok(result);
    }

    @SecurityRequirement(name = "JavaInUseSecurityScheme")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/new/{quizId}")
    public ResponseEntity<QuestionDTO> createQuestion(@PathVariable Long quizId,
                                                      @RequestBody QuestionDTO questionDTO) {
        Question currentQuestion = new Question(questionDTO);
        questionService.createQuestion(quizId, currentQuestion);
        return ResponseEntity.ok(questionDTO);
    }
}
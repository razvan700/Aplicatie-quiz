package com.jetbrains.aplicatiequiz.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "question_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attempt_id", nullable = false)
    private Attempt attempt;

    @OneToMany(mappedBy = "answer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AnswerChoice> answerChoices = new ArrayList<>();

    private String textResponse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Attempt getAttempt() {
        return attempt;
    }

    public void setAttempt(Attempt attempt) {
        this.attempt = attempt;
    }

    public List<AnswerChoice> getAnswerChoices() {
        return answerChoices;
    }

    public void setAnswerChoices(List<AnswerChoice> answerChoices) {
        this.answerChoices = answerChoices;
    }

    public String getTextResponse() {
        return textResponse;
    }

    public void setTextResponse(String textResponse) {
        this.textResponse = textResponse;
    }

    public Iterable<Long> getSelectedChoiceIds() {
        List<Long> ids = new ArrayList<>();
        for (AnswerChoice ac : answerChoices) {
            if (ac.getChoice() != null && ac.getChoice().getId() != null) {
                ids.add(ac.getChoice().getId());
            }
        }
        return ids;
    }

    public Long getQuestionId() {
        return question != null ? question.getId() : null;
    }

    public void setSelectedChoiceIds(List<Long> choiceIds) {
        List<AnswerChoice> choices = new ArrayList<>();
        for (Long id : choiceIds) {
            Choice choice = new Choice();
            choice.setId(id);
            AnswerChoice ac = new AnswerChoice();
            ac.setAnswer(this);
            ac.setChoice(choice);
            choices.add(ac);
        }
        this.answerChoices = choices;
    }

    public void setQuestionId(Long questionId) {
        Question question = new Question();
        question.setId(questionId);
        this.question = question;
    }
}

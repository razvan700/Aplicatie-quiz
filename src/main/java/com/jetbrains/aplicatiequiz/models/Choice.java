package com.jetbrains.aplicatiequiz.models;

import com.jetbrains.aplicatiequiz.dto.ChoiceDTO;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Choice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    public Choice() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<AnswerChoice> getAnswerChoices() {
        return answerChoices;
    }

    public void setAnswerChoices(List<AnswerChoice> answerChoices) {
        this.answerChoices = answerChoices;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @OneToMany(mappedBy = "choice", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AnswerChoice> answerChoices;

    public Choice(ChoiceDTO dto){
         this.id = dto.getId();
         this.text = dto.getText();
    }
}

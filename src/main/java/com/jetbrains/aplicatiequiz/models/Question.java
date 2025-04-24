package com.jetbrains.aplicatiequiz.models;

import com.jetbrains.aplicatiequiz.dto.QuestionDTO;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private String type;

//    @CollectionTable(name = "question_options", joinColumns = @JoinColumn(name = "question_id"))
//    @Column(name = "option")
//    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
//    private List<String> options;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "choice_id", nullable = false)
    private List<Choice> choices;

    public Question() {
    }

    public Question(QuestionDTO dto) {
        this.type = dto.getType();
        this.text = dto.getText();
//        this.options = dto.getOptions();

    }

    // Getters and Setters
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

//    public List<String> getOptions() {
//        return options;
//    }

//    public void setOptions(List<String> options) {
//        this.options = options;
//    }





    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
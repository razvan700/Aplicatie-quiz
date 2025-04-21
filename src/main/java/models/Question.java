package models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Question {
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

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public List<String> getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(List<String> correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private String type;

    @ElementCollection
    private List<String> options;

    @ElementCollection
    private List<String> correctAnswer;


}

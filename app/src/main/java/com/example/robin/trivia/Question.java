package com.example.robin.trivia;

import java.io.Serializable;

/**
 * Created by Robin on 8-3-2018.
 */

public class Question implements Serializable {

    // define variables
    private String question;
    private String answer;
    private String category;
    private Boolean correct;

    // define constructor of the class
    public Question(String question, String answer, String category, boolean correct) {
        this.question = question;
        this.answer = answer;
        this.category = category;
        this.correct = correct;
    }


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }
}

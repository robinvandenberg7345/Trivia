package com.example.robin.trivia;

/**
 * Created by Robin on 14-3-2018.
 */

public class HighScoreItem {

    // variables of class
    int score;
    String name;

    // constructors of class
    public HighScoreItem(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public HighScoreItem() {
    }

    // getters and setters of class
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

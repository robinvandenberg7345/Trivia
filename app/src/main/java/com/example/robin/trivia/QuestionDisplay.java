package com.example.robin.trivia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class QuestionDisplay extends AppCompatActivity implements QuestionRequest.Callback {

    // define variables of class
    int counter = 1;
    Question currentQuestion;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_display);

        Intent intent = getIntent();

        // get question
        if (counter == 1) {
            QuestionRequest questionRequest = new QuestionRequest(this);
            questionRequest.getQuestion(this);
        }

        // set listeners for the multiple choice options
        TextView optionOne = findViewById(R.id.optionOne);
        TextView optionTwo = findViewById(R.id.optionTwo);
        TextView optionThree = findViewById(R.id.optionThree);
        TextView optionFour = findViewById(R.id.optionFour);

        optionOne.setOnClickListener(new QuestionDisplay.AnswerClickListener());
        optionTwo.setOnClickListener(new QuestionDisplay.AnswerClickListener());
        optionThree.setOnClickListener(new QuestionDisplay.AnswerClickListener());
        optionFour.setOnClickListener(new QuestionDisplay.AnswerClickListener());
    }

    // handle the download of the menu
    @Override
    public void gotQuestion(ArrayList<Question> question) {
        ArrayList<Question> questionsList = question;

        // make a shuffled list of the numbers 0 to 3
        ArrayList<Integer> number = new ArrayList<Integer>();
        for (int i = 0; i <= 3; ++i) number.add(i);
        Collections.shuffle(number);

        currentQuestion = questionsList.get(0);

        // display title of category
        TextView categoryTitle = findViewById(R.id.title);
        categoryTitle.setText(currentQuestion.getCategory());

        // display question
        TextView asked = findViewById(R.id.questionText);
        asked.setText(currentQuestion.getQuestion());

        // display the different multiple choice options
        TextView optionOne = findViewById(R.id.optionOne);
        TextView optionTwo = findViewById(R.id.optionTwo);
        TextView optionThree = findViewById(R.id.optionThree);
        TextView optionFour = findViewById(R.id.optionFour);
        optionOne.setText(questionsList.get(number.get(0)).getAnswer());
        optionTwo.setText(questionsList.get(number.get(1)).getAnswer());
        optionThree.setText(questionsList.get(number.get(2)).getAnswer());
        optionFour.setText(questionsList.get(number.get(3)).getAnswer());
    }

    // display error message when loading question goes wrong
    @Override
    public void gotQuestionError(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    // update UI initiate verifying of answer
    public void updateUI(TextView answer) {
        if (counter != 1) {
            verifyAnswer(answer);
        }
        QuestionRequest questionRequest = new QuestionRequest(this);
        questionRequest.getQuestion(this);
    }

    // verify clicked answer
    public void verifyAnswer(TextView view) {
        String givenAnswer = view.getText().toString();
        String correctAnswer = currentQuestion.getAnswer();

        if (givenAnswer.equals(correctAnswer)) {
            currentQuestion.setCorrect(true);
            score = score + 1;
        }
    }

    // define the listener for multiple choice options
    private class AnswerClickListener implements TextView.OnClickListener {

        @Override
        public void onClick(View v) {
            TextView clickAnswer = (TextView) v;

            if (counter < 10) {
                counter = counter + 1;
                updateUI(clickAnswer);
            } else {

                // pass on category to menu activity to display items
                verifyAnswer(clickAnswer);
                Intent intent = new Intent(QuestionDisplay.this, UsernameInput.class);
                intent.putExtra("scoredPoints", score);
                startActivity(intent);
            }
        }
    }
}

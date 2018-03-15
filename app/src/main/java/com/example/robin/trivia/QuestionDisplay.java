package com.example.robin.trivia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class QuestionDisplay extends AppCompatActivity implements QuestionRequest.Callback {

    int counter = 1;
    int countersecond = 1;
    Question currentQuestion;
    int score = 0;
    ArrayList<Question> answeredQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_display);


        Intent intent = getIntent();

        TextView nextQuestionButton = findViewById(R.id.nextQuestion);
        nextQuestionButton.setOnClickListener(new QuestionDisplay.NextQuestionClickListener());

        updateUI();

        countersecond = countersecond + 1;
    }

    // handle the download of the menu
    @Override
    public void gotQuestion(ArrayList<Question> question) {
        ArrayList<Question> questionsList = question;
        currentQuestion = questionsList.get(0);

        TextView categoryTitle = findViewById(R.id.title);
        categoryTitle.setText(currentQuestion.getCategory());

        TextView asked = findViewById(R.id.questionText);
        asked.setText(currentQuestion.getQuestion());
    }

    @Override
    public void gotQuestionError(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    // Methods of the class
    public void updateUI() {
        if (counter != 1) {
            verifyAnswer();
        }

        QuestionRequest questionRequest = new QuestionRequest(this);
        questionRequest.getQuestion(this);
    }

    public void verifyAnswer() {
        EditText givenAnswerView = findViewById(R.id.answer);
        String givenAnswer = givenAnswerView.getText().toString();
        String correctAnswer = currentQuestion.getAnswer();

        if (givenAnswer.equals(correctAnswer)) {
            currentQuestion.setCorrect(true);
            score = score + 1;
        }

        //answeredQuestions.add(currentQuestion);
    }

    // define the listener
    private class NextQuestionClickListener implements TextView.OnClickListener {

        @Override
        public void onClick(View v) {
            if (counter < 10) {
                counter = counter + 1;
                updateUI();
            } else {
                // pass on category to menu activity to display items
                verifyAnswer();
                Intent intent = new Intent(QuestionDisplay.this, UsernameInput.class);
                intent.putExtra("scoredPoints", score);
                startActivity(intent);
            }
        }
    }

}

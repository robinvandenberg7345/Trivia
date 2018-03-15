package com.example.robin.trivia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UsernameInput extends AppCompatActivity {

    int score;
    TextView highScoreButton;
    String scoreText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username_input);

        Intent intent = getIntent();
        score = intent.getIntExtra("scoredPoints", 0);
        scoreText = String.valueOf(score);

        highScoreButton = findViewById(R.id.seeHighScoreButton);
        highScoreButton.setOnClickListener(new UsernameInput.HighScoreButtonItemClickListener());

        updateUI();
    }

    public void updateUI() {
        TextView scoreView = findViewById(R.id.score);
        scoreView.setText(scoreText);
    }

    // define the listener
    private class HighScoreButtonItemClickListener implements TextView.OnClickListener {

        @Override
        public void onClick(View v) {
            EditText givenUsername = findViewById(R.id.username);
            String username = givenUsername.getText().toString();

            DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();

            myRef.child("AllScores").child(username).child("score").setValue(score);

            // pass on category to menu activity to display items
            Intent intent = new Intent(UsernameInput.this, HighScoreView.class);
            intent.putExtra("scoredScore", score);
            intent.putExtra("username", username);
            startActivity(intent);
        }
    }
}

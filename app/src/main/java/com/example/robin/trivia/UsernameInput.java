package com.example.robin.trivia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class UsernameInput extends AppCompatActivity {

    // define variables
    int score;
    TextView highScoreButton;
    String scoreText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username_input);

        // retrieve passed on score
        Intent intent = getIntent();
        score = intent.getIntExtra("scoredPoints", 0);
        scoreText = String.valueOf(score);

        // set listener on highscore button
        highScoreButton = findViewById(R.id.seeHighScoreButton);
        highScoreButton.setOnClickListener(new UsernameInput.HighScoreButtonItemClickListener());

        // update the UI
        updateUI();
    }

    public void updateUI() {
        TextView scoreView = findViewById(R.id.score);
        scoreView.setText(scoreText);
    }

    // define the listener for highscore button
    private class HighScoreButtonItemClickListener implements TextView.OnClickListener {

        @Override
        public void onClick(View v) {

            // get entered username and create user item
            EditText givenUsername = findViewById(R.id.username);
            String username = givenUsername.getText().toString();
            HighScoreItem user = new HighScoreItem(username, score);

            // save the user in data base under random key
            DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
            myRef.child("AllScores").child(UUID.randomUUID().toString()).setValue(user);

            // pass on users information to highscore view
            Intent intent = new Intent(UsernameInput.this, HighScoreView.class);
            intent.putExtra("scoredScore", score);
            intent.putExtra("username", username);
            startActivity(intent);
        }
    }
}

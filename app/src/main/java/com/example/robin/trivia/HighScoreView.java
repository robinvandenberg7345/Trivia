package com.example.robin.trivia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class HighScoreView extends AppCompatActivity {

    int score;
    String username;
    TextView replayButton;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore_view);

        Intent intent = getIntent();
        score = intent.getIntExtra("scoredScore", 0);
        username = intent.getStringExtra("username");

        String scoreString = String.valueOf(score);
        TextView scoreView = findViewById(R.id.scoredScore);
        scoreView.setText(scoreString);

        replayButton = findViewById(R.id.tryAgain);
        replayButton.setOnClickListener(new ReplayClickListener());


        myRef = FirebaseDatabase.getInstance().getReference().child("AllScores");
        Query orderedList = myRef.orderByChild("score").limitToLast(5);

        // Read from the database
        orderedList.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object value = dataSnapshot.getValue();
                Log.d("Succes", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Failed", "Failed to read value.", error.toException());
            }
        });
    }

    // define the listener
    private class ReplayClickListener implements TextView.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(HighScoreView.this, MainActivity.class);
            startActivity(intent);
        }
    }
}

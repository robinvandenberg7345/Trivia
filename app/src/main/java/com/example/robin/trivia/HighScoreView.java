package com.example.robin.trivia;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HighScoreView extends AppCompatActivity {

    // number of items to show in highscores
    int NUMBEROFITEMS = 10;

    // define variables of class
    ArrayList<HighScoreItem> highScoresList = new ArrayList<HighScoreItem>();
    int score;
    String username;
    TextView replayButton;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore_view);

        // get past on score and name
        Intent intent = getIntent();
        score = intent.getIntExtra("scoredScore", 0);
        username = intent.getStringExtra("username");

        // set scored points
        String scoreString = String.valueOf(score);
        TextView scoreView = findViewById(R.id.scoredScore);
        scoreView.setText(scoreString);

        // set listener on replay button
        replayButton = findViewById(R.id.tryAgain);
        replayButton.setOnClickListener(new ReplayClickListener());

        final Context context = this;

        // retrieve highest scores form database
        myRef = FirebaseDatabase.getInstance().getReference().child("AllScores");
        Query orderedList = myRef.orderByChild("score").limitToLast(NUMBEROFITEMS);

        // Read from the database
        orderedList.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // process data returned from database
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    String itemHighScoreList = child.getValue().toString();

                    // register users with highest scores by splitting up returned string
                    String score = itemHighScoreList.substring(itemHighScoreList.indexOf('=') + 1,
                            itemHighScoreList.indexOf(","));
                    String name = itemHighScoreList.substring(itemHighScoreList.lastIndexOf('=') + 1,
                            itemHighScoreList.indexOf("}"));
                    HighScoreItem user = new HighScoreItem(name, Integer.valueOf(score));
                    highScoresList.add(user);
                }

                // fill highscore list with users having the highest scores
                ListView highScoresListView = findViewById(R.id.scoreListView);
                HighScoreAdapter adapter = new HighScoreAdapter(context, R.layout.highscore_item, highScoresList, NUMBEROFITEMS);
                highScoresListView.setAdapter(adapter);
            }

            // return error message if failed
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Failed", "Failed to read value.", error.toException());
            }
        });
    }

    // define the listener for replay button
    private class ReplayClickListener implements TextView.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(HighScoreView.this, MainActivity.class);
            startActivity(intent);
        }
    }
}

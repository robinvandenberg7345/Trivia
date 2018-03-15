package com.example.robin.trivia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        TextView playButton;
        TextView highScoreButton;


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener(new BeginGameButtonItemClickListener());

        highScoreButton = findViewById(R.id.seeHighScoreButton);
        highScoreButton.setOnClickListener(new HighScoreButtonItemClickListener());
    }

    // define the listener
    private class HighScoreButtonItemClickListener implements TextView.OnClickListener {

        @Override
        public void onClick(View v) {
            // pass on category to menu activity to display items
            Intent intent = new Intent(MainActivity.this, HighScoreView.class);
            startActivity(intent);
        }
    }

    // define the listener
    private class BeginGameButtonItemClickListener implements TextView.OnClickListener {

        @Override
        public void onClick(View v) {
            // pass on category to menu activity to display items
            Intent intent = new Intent(MainActivity.this, QuestionDisplay.class);
            startActivity(intent);
        }
    }
}

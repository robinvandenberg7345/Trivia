package com.example.robin.trivia;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Robin on 8-3-2018.
 */

public class HighScoreAdapter extends ArrayAdapter {

    // define variables
    Context context;
    int resource;
    ArrayList<HighScoreItem> highScores;
    int numberOfItems;

    // define constructor of the class
    public HighScoreAdapter(@NonNull Context context, int resource,
                            @NonNull ArrayList<HighScoreItem> highScores, int numberOfItems) {
        super(context, resource, highScores);

        this.highScores = highScores;
        this.context = context;
        this.resource = resource;
        this.numberOfItems = numberOfItems;
    }

    // fill ListView with highscores
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resource, null);

            // get highscore from list matching with the location in the list
            // the position is enhanced since the list from firebase is ordered from low to high
            HighScoreItem item = highScores.get(Math.abs(position - numberOfItems + 1));

            // retrieve views from different pieces of info
            TextView name = convertView.findViewById(R.id.userName);
            TextView score = convertView.findViewById(R.id.highScore);

            // push the pieces of info to the view
            name.setText(item.getName());
            score.setText(String.valueOf(item.getScore()));
        }
        return convertView;
    }
}

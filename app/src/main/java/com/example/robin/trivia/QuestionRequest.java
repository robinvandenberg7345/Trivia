package com.example.robin.trivia;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Robin on 8-3-2018.
 */

public class QuestionRequest implements Response.Listener<JSONArray>, Response.ErrorListener {

    // define variables of the class
    Context contextQuestion;
    String link = "http://jservice.io/api/random";
    Callback activityCallback;


    // define constructor of the class
    public QuestionRequest(Context context) {
        this.contextQuestion = context;
    }

    // guide the download of the questions
    @Override
    public void onErrorResponse(VolleyError error) {
        activityCallback.gotQuestionError("Question couldn't be successfully retrieved");
        Log.e("Questions:", error.getMessage());
    }

    @Override
    public void onResponse(JSONArray response) {

        // try to download the menu
        try {
            JSONArray questions = response;
            System.out.println(questions);
            ArrayList<Question> questionsList = new ArrayList<>();
            JSONObject question = questions.getJSONObject(0);

            //String title = question.getString("title");
            //Log.e("try", title);
            String asked = question.getString("question");
            String answer = question.getString("answer");
            JSONObject category = question.getJSONObject("category");
            String categoryTitle = category.getString("title");

            questionsList.add(new Question(asked, answer, categoryTitle, false));

            activityCallback.gotQuestion(questionsList);
            Log.e("test", question.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // to initiate download using volley
    public void getQuestion(Callback activity) {
        activityCallback = activity;
        RequestQueue queue = Volley.newRequestQueue(contextQuestion);
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(link, this, this);
        queue.add(jsonObjectRequest);
    }


    // define callback interface
    public interface Callback {
        void gotQuestion(ArrayList<Question> question);

        void gotQuestionError(String message);
    }
}

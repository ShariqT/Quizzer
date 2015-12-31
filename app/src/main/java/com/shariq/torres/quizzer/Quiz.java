package com.shariq.torres.quizzer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import android.content.Context;

/**
 * Created by Torres on 12/31/2015.
 */
public class Quiz {


    public int score;

    public String type;

    public String error;

    ArrayList<String> questions;

    ArrayList<JSONArray> answers;

    public Quiz(String type){
        this.type = type;
        this.loadQuiz();
    }


    private void loadQuiz(){
        try {
            JSONArray jsonText = new JSONArray(R.string.politics);
            for(int i = 0; i < jsonText.length(); i++){
                JSONObject currentQuestion = jsonText.getJSONObject(i);
                this.questions.add(i,currentQuestion.getString("question"));
                this.answers.add(i, currentQuestion.getJSONArray("answers"));
            }
        }catch(JSONException e){
            this.error = "Could not load the quiz. Please try again.";
        }
    }
}

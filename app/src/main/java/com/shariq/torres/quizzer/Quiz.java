package com.shariq.torres.quizzer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import android.content.Context;
import android.util.Log;

/**
 * Created by Torres on 12/31/2015.
 */
public class Quiz {


    public int score = 0;

    public String type;

    public String error;

    public ArrayList<String> questions;

    public ArrayList<JSONArray> answers;

    private Context ctx;

    public Quiz(String type, Context ctx){
        this.type = type;
        this.ctx = ctx;
        this.loadQuiz();
    }


    public boolean checkAnswer(int indexToCheck, CharSequence answerToCheck){
        try {
            JSONArray answersToCheck = this.answers.get(indexToCheck);
            for (int i = 0; i < answersToCheck.length(); i++) {
                if (answersToCheck.getJSONObject(i).getString("answer").contentEquals(answerToCheck) && answersToCheck.getJSONObject(i).optBoolean("correct")) {
                    score = score + 10;
                    return true;
                }
            }
            return false;
        }catch(JSONException e){
            //stub
            return false;
        }
    }


    public int getTotalQuestions(){
        return this.questions.size();
    }

    public int getCorrectAnswers(){
        return this.score / 10;
    }

    private void loadQuiz(){
        try {
            JSONObject jsonText = new JSONObject((String)ctx.getResources().getText(R.string.politics));
            //Log.d("Quizzer", jsonText.toString());
            JSONArray currentQuestion = jsonText.getJSONArray("quiz");
            this.questions = new ArrayList<String>();
            this.answers = new ArrayList<JSONArray>();
            for(int i = 0; i < currentQuestion.length(); i++){
                //Log.d("Quizzer Debug", currentQuestion.getJSONObject(1).toString());
                this.questions.add(i,currentQuestion.getJSONObject(i).getString("question"));
                this.answers.add(i, currentQuestion.getJSONObject(i).getJSONArray("choices"));
            }
        }catch(JSONException e){
            this.error = e.getMessage();
        }
    }
}

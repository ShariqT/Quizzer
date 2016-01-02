package com.shariq.torres.quizzer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {
    TextView titleView;
    String quizType;
    int currentIndex = 0;
    Quiz currentQuiz;
    TextView currentQuestion;
    RadioGroup possibleAnswers;
    Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        titleView = (TextView) findViewById(R.id.quizTitle);
        possibleAnswers = (RadioGroup) findViewById(R.id.radioGroup);
        currentQuestion = (TextView) findViewById(R.id.questionText);
        submitBtn = (Button) findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(this);

        quizType = getIntent().getStringExtra("type");
        if(quizType != null){
            currentQuiz = new Quiz(quizType, this);
            this.setUpQuiz();
        }
    }




    @Override
    public void onClick(View v){
        RadioButton selectedAnswer = (RadioButton) findViewById(possibleAnswers.getCheckedRadioButtonId());
        if(currentQuiz.checkAnswer(this.currentIndex, selectedAnswer.getText())){
            Toast.makeText(this,"Correct", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Wrong", Toast.LENGTH_SHORT).show();
        }

        Handler delayHandler = new Handler();

        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //noop, just want to have a little space before we setup the new question UI
                currentIndex++;
                if(currentIndex < currentQuiz.questions.size()){
                    setUpQuiz();
                }else{
                    //we have reached the end of the quiz
                    Intent i = new Intent(QuizActivity.this, MainActivity.class);
                    startActivity(i);
                }
            }
        }, 3000);


    }


    private void setUpQuiz(){
        titleView.setText(quizType.toUpperCase());


        if(currentQuiz.error != null){
            Log.d("Quizzer", currentQuiz.error);
        }
        Log.d("Quizzer", "The current index is " + String.valueOf(this.currentIndex));
        currentQuestion.setText(currentQuiz.questions.get(this.currentIndex));
        JSONArray answerArray = currentQuiz.answers.get(this.currentIndex);


        try {
            possibleAnswers.removeAllViews();
            for (int i = 0; i < answerArray.length(); i++) {
                RadioButton btn = new RadioButton(this);
                btn.setText(answerArray.getJSONObject(i).getString("answer"));
                possibleAnswers.addView(btn,i);
            }
        }catch(Exception e){
            //something went wrong parsing out the JSON from the strings.xml
            Snackbar.make(titleView, "Something went wrong", Snackbar.LENGTH_SHORT);
        }
    }

}

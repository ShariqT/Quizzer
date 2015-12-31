package com.shariq.torres.quizzer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import org.json.JSONArray;

public class QuizActivity extends AppCompatActivity {
    TextView titleView;
    String quizType;
    int currentIndex = 0;
    Quiz currentQuiz;
    TextView currentQuestion;
    Button nextBtn;
    Button prevBtn;
    RadioButton answer1;
    RadioButton answer2;
    RadioButton answer3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        titleView = (TextView) findViewById(R.id.quizTitle);
        nextBtn = (Button) findViewById(R.id.nextBtn);
        prevBtn = (Button) findViewById(R.id.prevBtn);
        currentQuestion = (TextView) findViewById(R.id.questionText);

       quizType = getIntent().getStringExtra("type");
        if(quizType != null){
            this.setUpQuiz();
        }
    }



    private void setUpQuiz(){
        titleView.setText(quizType.toUpperCase());
        currentQuiz = new Quiz(quizType);
        if( currentIndex == 0){
            prevBtn.setVisibility(View.INVISIBLE);
        }

        currentQuestion.setText(currentQuiz.questions.get(this.currentIndex));
        JSONArray answerArray = currentQuiz.answers.get(currentIndex);
        for(int i = 0; i < answerArray.length(); i++){
            
        }
    }

}

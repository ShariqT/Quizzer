package com.shariq.torres.quizzer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    ImageView spiritAnimal;
    TextView correctAnswer;
    TextView totalQuestions;
    TextView message;
    Button restBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        spiritAnimal = (ImageView) findViewById(R.id.spiritAnimal);
        spiritAnimal.setImageResource(R.drawable.zach);
        int total_questions = getIntent().getIntExtra("total questions", 0);
        int correct_answers = getIntent().getIntExtra("correct answers", 0);

        correctAnswer = (TextView) findViewById(R.id.correct_answers);
        totalQuestions = (TextView) findViewById(R.id.total_questions);
        message = (TextView) findViewById(R.id.statusMessage);
        restBtn = (Button) findViewById(R.id.resetBtn);
        totalQuestions.setText(String.valueOf(total_questions));
        correctAnswer.setText(String.valueOf(correct_answers));
        float percentage = (correct_answers / total_questions) * 100;

        if(percentage <= 35){
            message.setText(R.string.sucks);
        }
        if(percentage > 35 && percentage < 80){
            message.setText(R.string.middling);
        }

        if(percentage >= 80){
            message.setText(R.string.good);
        }

        restBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(ResultsActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

    }

}

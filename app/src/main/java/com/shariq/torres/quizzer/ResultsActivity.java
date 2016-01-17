package com.shariq.torres.quizzer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
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

        int total_questions = getIntent().getIntExtra("total questions", 0);
        int correct_answers = getIntent().getIntExtra("correct answers", 0);

        correctAnswer = (TextView) findViewById(R.id.correct_answers);
        totalQuestions = (TextView) findViewById(R.id.total_questions);
        float percentage = ((float)correct_answers / (float)total_questions) * 100;

        message = (TextView) findViewById(R.id.statusMessage);
        restBtn = (Button) findViewById(R.id.resetBtn);
        totalQuestions.setText(String.valueOf(total_questions));
        correctAnswer.setText(String.valueOf(correct_answers));

        Log.d("Quizzer", "final percentage is " + String.valueOf(percentage));
        Log.d("Quizzer", "total_questions is " + String.valueOf(total_questions));
        Log.d("Quizzer", "correct_answers is " + String.valueOf(correct_answers));
        if(percentage == 0){
            message.setText("You are bad and you should be ashamed!");
            spiritAnimal.setImageResource(R.drawable.all_wrong);
        }

        if(percentage <= 35 && percentage > 0){
            message.setText(R.string.sucks);
            spiritAnimal.setImageResource(R.drawable.ghostface);
        }
        if(percentage > 35 && percentage < 80){
            message.setText(R.string.middling);
            spiritAnimal.setImageResource(R.drawable.fresh_prince_try);
        }

        if(percentage >= 80 && percentage < 100){
            message.setText(R.string.good);
            spiritAnimal.setImageResource(R.drawable.destinyschild);
        }

        if(percentage == 100){
            message.setText("Flawless");
            spiritAnimal.setImageResource(R.drawable.lauryn);
        }

        restBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(ResultsActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onKeyDown(int KeyCode, KeyEvent event){
        if(KeyCode == KeyEvent.KEYCODE_BACK){
            Intent i = new Intent(ResultsActivity.this, MainActivity.class);
            startActivity(i);
        }
        return true;
    }

}

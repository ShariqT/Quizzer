package com.shariq.torres.quizzer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity {
    TextView titleView;
    String quizType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        titleView = (TextView) findViewById(R.id.quizTitle);

       quizType = getIntent().getStringExtra("type");
        if(quizType != null){
            titleView.setText(quizType);
        }
    }

}

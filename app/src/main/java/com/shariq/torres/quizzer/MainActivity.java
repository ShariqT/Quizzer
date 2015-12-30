package com.shariq.torres.quizzer;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    Button sportsBtn;
    Button politicsBtn;
    Button musicBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sportsBtn = (Button) findViewById(R.id.sportsBtn);
        politicsBtn = (Button) findViewById(R.id.politicsBtn);
        musicBtn = (Button) findViewById(R.id.musicBtn);
        sportsBtn.setOnClickListener(this);
        politicsBtn.setOnClickListener(this);
        musicBtn.setOnClickListener(this);
    }



    @Override
    public void onClick(View v){
        Intent i =  new Intent(MainActivity.this, QuizActivity.class);
        switch(v.getId()){
            case R.id.sportsBtn:
                Log.d("Quizzer", "Selected Sports Quiz");
                i.putExtra("type", "sports");
                startActivity(i);
                break;
            case R.id.politicsBtn:
                Log.d("Quizzer", "Selected Politics Quiz");
                i.putExtra("type", "politics");
                startActivity(i);
                break;
            case R.id.musicBtn:
                Log.d("Quizzer", "Selected Music Quiz");
                i.putExtra("type", "music");
                startActivity(i);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

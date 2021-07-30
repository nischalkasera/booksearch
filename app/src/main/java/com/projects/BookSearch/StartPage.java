package com.projects.BookSearch;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class StartPage extends AppCompatActivity {
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_page);
        timer =  new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent bIntent = new Intent(StartPage.this, MainActivity.class);
                startActivity(bIntent);
                finish();
            }
        },2000);
    }
}

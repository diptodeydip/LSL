package com.example.dip.lsl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Quiz extends AppCompatActivity {

    Button al,emo,days;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        al = findViewById(R.id.Alphabets1);
        emo = findViewById(R.id.Emot1);
        days = findViewById(R.id.Days1);
        al.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.category = "Alphabets";
                ////finish();
                startActivity(new Intent(Quiz.this,QuizContents.class));
            }
        });
        emo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.category = "Emotions";
                // finish();
                startActivity(new Intent(Quiz.this,QuizContents.class));
            }
        });
        days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.category = "Days";
                //// finish();
                startActivity(new Intent(Quiz.this,QuizContents.class));
            }
        });

    }
}

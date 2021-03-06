package com.example.dip.lsl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.TextView;

public class Categories extends AppCompatActivity {

    Button al,emo,days;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.2F);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        al = findViewById(R.id.Alphabets);
        emo = findViewById(R.id.Emot);
        days = findViewById(R.id.Days);
        al.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClick);
                MainActivity.category = "Alphabets";
                ////finish();
                startActivity(new Intent(Categories.this,Contents.class));
            }
        });
        emo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClick);
                MainActivity.category = "Emotions";
               // finish();
                startActivity(new Intent(Categories.this,Contents.class));
            }
        });
        days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClick);
                MainActivity.category = "Days";
               //// finish();
                startActivity(new Intent(Categories.this,Contents.class));
            }
        });

    }
}

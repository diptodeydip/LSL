package com.example.dip.lsl;

import android.content.Intent;
import android.media.Image;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class QuizContents extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button btnDisplay;
    private ImageView iv;
    TextView lvl;
    Button submit;
    DatabaseReference db;
    int count =0,temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_contents);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        iv = findViewById(R.id.quesiv);
        lvl = findViewById(R.id.lvl);
        lvl.setText("Level - "+MainActivity.rating);
        submit = findViewById(R.id.submit);

        btnDisplay=(Button)findViewById(R.id.submit);
        iv.setImageResource(R.drawable.a);

        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId=radioGroup.getCheckedRadioButtonId();
                radioButton=(RadioButton)findViewById(selectedId);
                if(count == 0){
                    if(radioButton.getText().toString().equalsIgnoreCase("a")){
                        count++;
                        Toast.makeText(QuizContents.this,"Correct "+count,Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(QuizContents.this,"Wrong Ansewr , Try again after learing",Toast.LENGTH_LONG).show();
                        finish();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));

                    }
                }
                else if(count==1){
                    if(radioButton.getText().toString().equalsIgnoreCase("d")){
                        count++;
                        Toast.makeText(QuizContents.this,"Correct "+count,Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(QuizContents.this,"Wrong Ansewr , Try again after learing",Toast.LENGTH_LONG).show();
                        finish();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        Toast.makeText(QuizContents.this,"Wrong Ansewr , Try again after learing",Toast.LENGTH_SHORT).show();
                    }
                }
                else if(count==2){
                    if(radioButton.getText().toString().equalsIgnoreCase("b")){
                        count++;
                        Toast.makeText(QuizContents.this,"Correct "+count,Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(QuizContents.this,"Wrong Ansewr , Try again after learing",Toast.LENGTH_LONG).show();
                        finish();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        Toast.makeText(QuizContents.this,"Wrong Ansewr , Try again after learing",Toast.LENGTH_LONG).show();
                    }
                }
                else if(count==3){
                    if(radioButton.getText().toString().equalsIgnoreCase("e")){
                        count++;
                        Toast.makeText(QuizContents.this,"Correct "+count,Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(QuizContents.this,"Wrong Ansewr , Try again after learing",Toast.LENGTH_LONG).show();
                        finish();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        Toast.makeText(QuizContents.this,"Wrong Ansewr , Try again after learing",Toast.LENGTH_LONG).show();
                    }
                }




                if(count==1){
                    iv.setImageResource(R.drawable.d);
                }
                else if(count ==2){
                    iv.setImageResource(R.drawable.b);
                }
                else if(count == 3){
                    iv.setImageResource(R.drawable.e);
                }
                else if(count == 4){
                    db = FirebaseDatabase.getInstance().getReference("Users").child(
                            FirebaseAuth.getInstance().getUid().toString()
                    ).child("level");

                    temp = Integer.parseInt(MainActivity.rating)+1;
                    MainActivity.rating =Integer.toString(temp);
                    db.setValue(MainActivity.rating);
                    Toast.makeText(QuizContents.this,"You have been promoted to "+MainActivity.rating,Toast.LENGTH_LONG).show();
                    finish();
                    startActivity(new Intent(getApplicationContext(),Profile.class));
                }
            }
        });
    }
}

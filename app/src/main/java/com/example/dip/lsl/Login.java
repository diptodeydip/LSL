package com.example.dip.lsl;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private EditText loginName;
    private EditText loginPassword;
    private Button loginButton;
    private Button signInButton;
    private TextView signUpHint;
    private FirebaseAuth mAuth;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.2F);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginName = findViewById(R.id.login_name);
        loginPassword =findViewById(R.id.login_password);
        loginButton= findViewById(R.id.login_button);
        loginButton.setOnClickListener(this);
        signInButton = findViewById(R.id.sign_up_button);
        signInButton.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        if(v==loginButton){
            v.startAnimation(buttonClick);
            String email,password;
            email = loginName.getText().toString().trim();
            password = loginPassword.getText().toString().trim();
            Toast.makeText(Login.this, email,
                    Toast.LENGTH_SHORT).show();
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                Toast.makeText(Login.this, "Logged In",
                                        Toast.LENGTH_SHORT).show();
                               finish();
                                startActivity(new Intent(Login.this,MainActivity.class));
                            } else {

                                Toast.makeText(Login.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }

                        }
                    });



        }
        else if(v==signInButton){
            v.startAnimation(buttonClick);
            startActivity(new Intent(getApplicationContext(), Register.class));


        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }
    }

}
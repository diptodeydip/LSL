package com.example.dip.lsl;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity implements View.OnClickListener {
    private EditText registerNickName;
    private EditText emailEditText;
    private EditText passwordText;
    private Button registerButton;
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerNickName = findViewById(R.id.register_nick_name);
        emailEditText = findViewById(R.id.register_email);
        passwordText = findViewById(R.id.register_email_password);

        registerButton = findViewById(R.id.sign_up_button_register);
        registerButton.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();






    }

    @Override
    public void onClick(View v) {
        if(v==registerButton){
            String email,password;
            email= emailEditText.getText().toString().trim();
            password = passwordText.getText().toString().trim();
            Toast.makeText(Register.this, email,
                    Toast.LENGTH_SHORT).show();

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();

                                String uid = user.getUid();
                                myRef = FirebaseDatabase.getInstance().getReference("Users").child(uid);

                                UserInformation ui = new UserInformation(
                                        registerNickName.getText().toString().trim(),uid,"1"
                                );
                                myRef.setValue(ui);



                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(registerNickName.getText().toString()).build();

                                user.updateProfile(profileUpdates);
                                // Sign in success, update UI with the signed-in user's information
//                                Log.d(TAG, "createUserWithEmail:success");
//                                FirebaseUser user = mAuth.getCurrentUser();
//                                updateUI(user);
                                Toast.makeText(Register.this, "Authentication Successful",
                                        Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(Register.this,Login.class));
                            } else {
                                // If sign in fails, display a message to the user.
//                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(Register.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
//                                updateUI(null);
                            }

                            // ...
                        }
                    });
        }
    }
}

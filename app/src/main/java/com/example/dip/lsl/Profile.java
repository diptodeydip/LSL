package com.example.dip.lsl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.security.PublicKey;
import java.util.List;

public class Profile extends AppCompatActivity {
    public TextView pname,level;
    RecyclerView rView;
    DatabaseReference db;
    List<upload> uploads;
    ProgressBar pbar;
    FirebaseAuth mAuth;
    String userId;
    FirebaseStorage fs;
    ValueEventListener dBListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        pname = findViewById(R.id.profileusername);
        level = findViewById(R.id.Level);
        pname.setText("Welcome "+ FirebaseAuth.getInstance().getCurrentUser().getDisplayName().toString());
        level.setText("Level - "+MainActivity.rating);




    }
}

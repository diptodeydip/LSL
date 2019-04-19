package com.example.dip.lsl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class VideoContents extends AppCompatActivity {

    RecyclerView rView;
    DatabaseReference db;
    List<upload> uploads;
    ProgressBar pbar;
    VideoAdapter iAdapter;
    FirebaseAuth mAuth;
    String userId;
    FirebaseStorage fs;
    ValueEventListener dBListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_contents);
        rView = findViewById(R.id.recyclerView1);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(new LinearLayoutManager(this));
        uploads = new ArrayList<>();
        fs = FirebaseStorage.getInstance();
        db = FirebaseDatabase.getInstance().getReference("Video");
        iAdapter = new VideoAdapter(VideoContents.this,uploads);
        rView.setAdapter(iAdapter);
        dBListener = db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                uploads.clear();
             //   Toast.makeText(VideoContents.this,"Paise", Toast.LENGTH_SHORT).show();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    upload up = postSnapshot.getValue(upload.class);
                 //   Toast.makeText(VideoContents.this,"Paise22", Toast.LENGTH_SHORT).show();
                    uploads.add(up);
                }
                iAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(VideoContents.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}


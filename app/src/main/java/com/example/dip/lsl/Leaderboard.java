package com.example.dip.lsl;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import static java.security.AccessController.getContext;

public class Leaderboard extends AppCompatActivity implements View.OnClickListener {
    RecyclerView mUsers_list;
    private DatabaseReference myRef;
    private Button searchButton;
    private EditText searchFied;
    public Button addfriend;
    public static String uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        mUsers_list= (RecyclerView) findViewById(R.id.user_list);
        mUsers_list.setHasFixedSize(true);
        mUsers_list.setLayoutManager(new LinearLayoutManager(this));

        searchFied = findViewById(R.id.search_here_text);

        myRef = FirebaseDatabase.getInstance().getReference().child("Users");

        searchButton= findViewById(R.id.search_here_button);
        searchButton.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        if(v==searchButton){
            String searchText = searchFied.getText().toString();

            userSearch(searchText);
        }
    }

    public static class All_UsersViewHolder extends RecyclerView.ViewHolder{

        View v;
        public All_UsersViewHolder(@NonNull View itemView) {
            super(itemView);
            v=itemView;
        }
        public void setDetails(String userName, String level,String uidd){
            TextView userNametxt = v.findViewById(R.id.all_name_text);
            TextView leveltxt = v.findViewById(R.id.all_level_text);
            userNametxt.setText(userName);
            leveltxt.setText("level~"+level);
            uid = uidd;
        }

    }
    public void userSearch(String searchText){


        Query firebaseSearchQuery = myRef.orderByChild("name").startAt(searchText).endAt(searchText + "\uf8ff");

        FirebaseRecyclerAdapter<UserInformation, All_UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<UserInformation, All_UsersViewHolder>(
                UserInformation.class,
                R.layout.all_users_single_layout,
                All_UsersViewHolder.class,
                firebaseSearchQuery) {
            @Override
            protected void populateViewHolder(All_UsersViewHolder viewHolder, UserInformation model, int position) {
                Toast.makeText(Leaderboard.this, "Started Search", Toast.LENGTH_LONG).show();
                viewHolder.setDetails(model.getName(),model.getLevel(),model.getUid());

                ///
                ///
                /// /
                viewHolder.v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addfriend = findViewById(R.id.addfriend);
                        if(!uid.equalsIgnoreCase(FirebaseAuth.getInstance().getCurrentUser().getUid())){

                        }
                        else addfriend.setVisibility(View.GONE);

                    }
                });
            }
        };
        mUsers_list.setAdapter(firebaseRecyclerAdapter);

    }
}

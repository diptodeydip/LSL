package com.example.dip.lsl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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

import java.util.List;

public class MainActivity extends AppCompatActivity
       implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView rView;
    DatabaseReference db;
    List<upload> uploads;
    ProgressBar pbar;
    FirebaseAuth mAuth;
    String userId;
    FirebaseStorage fs;
    ValueEventListener dBListener;
       public  static String category,rating;
       TextView username;
       String un;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //


        db = FirebaseDatabase.getInstance().getReference("Users");
        dBListener = db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
              //  Toast.makeText(MainActivity.this,"Paise", Toast.LENGTH_SHORT).show();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    UserInformation up = postSnapshot.getValue(UserInformation.class);
               //     Toast.makeText(MainActivity.this, "Paise22", Toast.LENGTH_SHORT).show();
                    if(up.getUid().equalsIgnoreCase(FirebaseAuth.getInstance().getCurrentUser()
                            .getUid())){
                        MainActivity.rating = up.getLevel();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });




        //

       // Toast.makeText(MainActivity.this,"Welcome "+FirebaseAuth.getInstance().getCurrentUser().getDisplayName().toString(),Toast.LENGTH_LONG).show();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
      //  getMenuInflater().inflate(R.menu.main, menu);
        username = findViewById(R.id.usernamee);
        un = FirebaseAuth.getInstance().getCurrentUser().getDisplayName().toString();
        username.setText(un);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
     //   if (id == R.id.action_settings) {
       //     return true;
       // }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_prof) {
            startActivity(new Intent(MainActivity.this,Profile.class));
        } else if (id == R.id.nav_pracphoto) {
            startActivity(new Intent(MainActivity.this,Categories.class));

        }
        else if (id == R.id.nav_pracvid) {
            startActivity(new Intent(MainActivity.this,VideoContents.class));
        }
        else if (id == R.id.nav_leader) {
            startActivity(new Intent(getApplicationContext(),Leaderboard.class));

        } else if (id == R.id.nav_quiz) {
            startActivity(new Intent(MainActivity.this,Quiz.class));
        }
        else if(id== R.id.nav_logout){
            FirebaseAuth.getInstance().signOut();
            finish();
            startActivity(new Intent(getApplicationContext(),Login.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

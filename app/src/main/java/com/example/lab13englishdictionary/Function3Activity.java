package com.example.lab13englishdictionary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Function3Activity extends AppCompatActivity {

    public static ArrayList<Modeclass> list1; // Change ArrayList to List
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function3);




        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.quiz);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home) {
                    startActivity(new Intent(getApplicationContext(), HomePage.class));
                    overridePendingTransition(0, 0);

                    return true;
                } else if (item.getItemId() == R.id.card) {
                    startActivity(new Intent(getApplicationContext(), RandomCardActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if (item.getItemId() == R.id.quiz) {

                    return true;
                }else if (item.getItemId() == R.id.story) {
                    startActivity(new Intent(getApplicationContext(), Function4Activity.class));
                    overridePendingTransition(0, 0);
                    return true;
                }
                return false;
            }
        });

        list1 = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("Quiz");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Modeclass modeclass = dataSnapshot.getValue(Modeclass.class);
                    list1.add(modeclass);
                }
                Intent i = new Intent(Function3Activity.this, DashboardActivity.class);
                startActivity(i);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//        list1.add(new Modeclass("1 cung cấp / đem lại", "offer", "fill out/in", "offer", "level", "new"));
//        list1.add(new Modeclass("2 có", "availabel", "availabel", "among", "adjust", "regarding"));
//        list1.add(new Modeclass("3 availabel", "có", "có mặt", "có thể", "cấm", "có"));
//        list1.add(new Modeclass("4", "a", "a", "b", "c", "d"));
//        list1.add(new Modeclass("5", "a", "a", "b", "c", "d"));
//        list1.add(new Modeclass("6", "a", "a", "b", "c", "d"));
//        list1.add(new Modeclass("7", "a", "a", "b", "c", "d"));
//        list1.add(new Modeclass("8", "a", "a", "b", "c", "d"));
//        list1.add(new Modeclass("9", "a", "a", "b", "c", "d"));
//        list1.add(new Modeclass("10", "a", "a", "b", "c", "d"));
//        list1.add(new Modeclass("11", "a", "a", "b", "c", "d"));
//        list1.add(new Modeclass("12", "a", "a", "b", "c", "d"));
//        list1.add(new Modeclass("13", "a", "a", "b", "c", "d"));
//        list1.add(new Modeclass("14", "a", "a", "b", "c", "d"));
//        list1.add(new Modeclass("15", "a", "a", "b", "c", "d"));
//        list1.add(new Modeclass("16", "a", "a", "b", "c", "d"));
//        list1.add(new Modeclass("17", "a", "a", "b", "c", "d"));
//
//
//        list1.add(new Modeclass("18The word \"dauntless\" appears in your favorite novel.  Based on the context, which word best captures the meaning of \"dauntless\"?", "b) Full of courage and bravery", "a) Hesitant and easily frightened", "b) Full of courage and bravery", "c) Devious and cunning", "d) Prone to complaining"));
//        list1.add(new Modeclass("19The word \"dauntless\" appears in your favorite novel.  Based on the context, what is the closest synonym for \"dauntless\"?", "(c) Fearless", "(a) Confused", "(b) Unprepared", "(c) Fearless", "(d) Hesitant"));
//        list1.add(new Modeclass("20The word \"dauntless\" means:", "(c) Brave and fearless", "(a) Full of fear", "(b) Prudent and cautious", "(c) Brave and fearless", "(d) Devious and cunning"));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                Intent i = new Intent(Function3Activity.this, DashboardActivity.class);
//                startActivity(i);
            }
        }, 0);


    }
}
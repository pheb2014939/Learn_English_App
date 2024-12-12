package com.example.lab13englishdictionary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) LinearLayout instruction_info = findViewById(R.id.type1);
        instruction_info.setOnClickListener((v -> {
            Intent i = new Intent(HomePage.this, MainActivity.class);
            startActivity(i);
        }));

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) LinearLayout instruction_info_ielts = findViewById(R.id.type2);
        instruction_info_ielts.setOnClickListener((v -> {
            Intent i = new Intent(HomePage.this, TypeIeltsActivity.class);
            startActivity(i);
        }));
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) LinearLayout instruction_info_toiec = findViewById(R.id.type3);
        instruction_info_toiec.setOnClickListener((v -> {
            Intent i = new Intent(HomePage.this, ToiecActivity.class);
            startActivity(i);
        }));
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) LinearLayout instruction_info_advance = findViewById(R.id.type4);
        instruction_info_advance.setOnClickListener((v -> {
            Intent i = new Intent(HomePage.this, AdvanceEnglishCommunicationActivity.class);
            startActivity(i);
        }));
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) LinearLayout instruction_info_basic = findViewById(R.id.type5);
        instruction_info_basic.setOnClickListener((v -> {
            Intent i = new Intent(HomePage.this, BasicCommunicationActivity.class);
            startActivity(i);
        }));
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) LinearLayout instruction_info_ielts_listening = findViewById(R.id.type6);
        instruction_info_ielts_listening.setOnClickListener((v -> {
            Intent i = new Intent(HomePage.this, CommonIeltsListeningActivity.class);
            startActivity(i);
        }));


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home) {
                    return true;
                } else if (item.getItemId() == R.id.card) {
                    startActivity(new Intent(getApplicationContext(), RandomCardActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if (item.getItemId() == R.id.quiz) {
                    startActivity(new Intent(getApplicationContext(), Function3Activity.class));
                    overridePendingTransition(0, 0);
                    return true;
                }else if (item.getItemId() == R.id.story) {
                    startActivity(new Intent(getApplicationContext(), Function4Activity.class));
                    overridePendingTransition(0, 0);
                    return true;
                }

                return false;
            }
        });
    }
}
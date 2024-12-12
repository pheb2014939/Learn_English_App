package com.example.lab13englishdictionary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Function4Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<DataClass> dataList;
    MyAdapter adapter;
    DataClass androidData;
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function4);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.story);

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
                    startActivity(new Intent(getApplicationContext(), Function3Activity.class));
                    overridePendingTransition(0, 0);

                    return true;
                }else if (item.getItemId() == R.id.story) {
                    return true;
                }

                return false;
            }
        });
        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.search);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(Function4Activity.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        dataList = new ArrayList<>();
        androidData = new DataClass("Never Tell A Lie", R.string.late_blight, R.drawable.liepic);
        dataList.add(androidData);
        androidData = new DataClass("The Cow And The Pitcher", R.string.thecowandthepitcher, R.drawable.crow);
        dataList.add(androidData);
        androidData = new DataClass("Friendship Is A Weapon", R.string.friend, R.drawable.friend);
        dataList.add(androidData);
        androidData = new DataClass("The Wind And The Sun", R.string.thesun,  R.drawable.thesun);
        dataList.add(androidData);
//        androidData = new DataClass("Tomato Spotted Spider Mites", R.string.spider_mites, R.drawable.spider_mites);

//        dataList.add(androidData);
        adapter = new MyAdapter(Function4Activity.this, dataList);
        recyclerView.setAdapter(adapter);
    }

    private void searchList(String text) {
        List<DataClass> dataSearchList = new ArrayList<>();
        for (DataClass data : dataList) {
            if (data.getDataTitle().toLowerCase().contains(text.toLowerCase())) {
                dataSearchList.add(data);
            }
        }
        if (dataSearchList.isEmpty()) {
            Toast.makeText(this, "Not Found", Toast.LENGTH_SHORT).show();
        } else {
            adapter.setSearchList(dataSearchList);
        }




    }
}
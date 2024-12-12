package com.example.lab13englishdictionary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ToiecActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private DatabaseReference myRef;
    private Context mimg;
    ProgressDialog progressDialog;

    private ArrayList<Vocabulary> vocabularyArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toiec);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching data ...");
        progressDialog.show();


        recyclerView = findViewById(R.id.RecyclerView1);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        myRef = FirebaseDatabase.getInstance().getReference();

        vocabularyArrayList = new ArrayList<>();

        ClearAll();

        GetDataFromFirebase();


    }

    private void GetDataFromFirebase() {

        Query query = myRef.child("toiecword");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ClearAll();

                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    Vocabulary vocabulary = new Vocabulary();
                    vocabulary.setImageUrl(snapshot1.child("img").getValue(String.class));
                    vocabulary.setEx(snapshot1.child("ex").getValue(String.class));
                    vocabulary.setDef(snapshot1.child("def").getValue(String.class));
                    vocabulary.setVocab(snapshot1.child("vocab").getValue(String.class));

                    vocabularyArrayList.add(vocabulary);
                }

                recyclerAdapter = new RecyclerAdapter(ToiecActivity.this, vocabularyArrayList);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database error
            }
        });


    }

    private void ClearAll() {
        if(vocabularyArrayList !=null){
            vocabularyArrayList.clear();

            if(recyclerAdapter != null){
                recyclerAdapter.notifyDataSetChanged();
            }
        }

        vocabularyArrayList = new ArrayList<>();




    }

}
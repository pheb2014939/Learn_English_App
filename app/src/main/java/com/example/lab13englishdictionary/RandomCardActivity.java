package com.example.lab13englishdictionary;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.MenuItem;
import android.view.ContextMenu;


import com.example.lab13englishdictionary.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomCardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private VocabularyAdapter adapter;
    ProgressDialog progressDialog;

    Button btnIdiom;

    private List<Vocabulary> vocabularyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_card);

        Button btnIdiom = findViewById(R.id.buttonIdiom);
        btnIdiom.setOnClickListener(v -> {
            Intent i = new Intent(RandomCardActivity.this, RandomIdiomCardActivity.class);
            startActivity(i);
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.card);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home) {
                    startActivity(new Intent(getApplicationContext(), HomePage.class));
                    overridePendingTransition(0, 0);
                    return true;

                } else if (item.getItemId() == R.id.card) {

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




        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching data ...");
        progressDialog.show();
        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        vocabularyList = new ArrayList<>();
        adapter = new VocabularyAdapter(vocabularyList);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("random");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                vocabularyList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Vocabulary vocabulary = snapshot.getValue(Vocabulary.class);
                    vocabularyList.add(vocabulary);
                }
                adapter.notifyDataSetChanged();
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });

        recyclerView.setAdapter(adapter);
    }

    public static class Vocabulary {
        private String word;
        private String mean;

        public Vocabulary() {
            // Default constructor required for Firebase
        }

        public Vocabulary(String word, String mean) {
            this.word = word;
            this.mean = mean;
        }

        public String getWord() {
            return word;
        }

        public String getMean() {
            return mean;
        }
    }

    public class VocabularyAdapter extends RecyclerView.Adapter<VocabularyAdapter.ViewHolder> {
        private List<Vocabulary> vocabularyList;
        private Random random;

        public VocabularyAdapter(List<Vocabulary> vocabularyList) {
            this.vocabularyList = vocabularyList;
            this.random = new Random();
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_row1, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Vocabulary vocabulary = vocabularyList.get(position);
            holder.wordTextView.setText(vocabulary.getWord());
            holder.meaningTextView.setText(vocabulary.getMean());
        }

        @Override
        public int getItemCount() {
            return vocabularyList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private TextView wordTextView;
            private TextView meaningTextView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                wordTextView = itemView.findViewById(R.id.word);
                meaningTextView = itemView.findViewById(R.id.mean);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                // Random index
                int randomIndex = random.nextInt(vocabularyList.size());
                // Get random vocabulary
                Vocabulary randomVocabulary = vocabularyList.get(randomIndex);
                // Update views with random vocabulary
                wordTextView.setText(randomVocabulary.getWord());
                meaningTextView.setText(randomVocabulary.getMean());
            }
        }
    }

}
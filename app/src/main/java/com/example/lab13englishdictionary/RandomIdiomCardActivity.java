package com.example.lab13englishdictionary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class RandomIdiomCardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Context context;


    private VocabularyAdapter adapter;
    ProgressDialog progressDialog;

    private List<Vocabulary> vocabularyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_idiom_card);

        Button button_word_card = findViewById(R.id.button_word_card);
        button_word_card.setOnClickListener(v -> {
            Intent i = new Intent(RandomIdiomCardActivity.this, RandomCardActivity.class);
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
        DatabaseReference databaseReference = firebaseDatabase.getReference("idioms");

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
        private String idiomword;
        private String wordmean;
        private String worddef;


        String img;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public Vocabulary() {
            // Default constructor required for Firebase
        }

        public Vocabulary(String idiomword, String wordmean, String worddef, String img) {
            this.idiomword = idiomword;
            this.wordmean = wordmean;
            this.worddef = worddef;
            this.img = img;

        }

        public String getIdiomword() {
            return idiomword;
        }

        public String getWordmean() {
            return wordmean;
        }
        public String getWorddef() {

            return worddef;
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
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_row3, parent, false);
            return new ViewHolder(view);
        }

        //        @Override
//        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//            Vocabulary vocabulary = vocabularyList.get(position);
//            holder.idiomwordTextView.setText(vocabulary.getIdiomword());
//            holder.wordmeaningTextView.setText(vocabulary.getWordmean());
//            holder.worddefTextView.setText(vocabulary.getWordmean());
//
//        }
        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Vocabulary vocabulary = vocabularyList.get(position);
            holder.idiomwordTextView.setText(vocabulary.getIdiomword());
            holder.wordmeaningTextView.setText(vocabulary.getWordmean());
            holder.worddefTextView.setText(vocabulary.getWorddef());

            // Load image using Glide
            Glide.with(holder.itemView.getContext())
                    .load(vocabulary.getImg()) // Assuming getImageUrl() returns the URL of the image
                    .into(holder.imageView);
        }


        @Override
        public int getItemCount() {
            return vocabularyList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            //            private TextView idiomwordTextView;
//            private TextView wordmeaningTextView;
//            private TextView worddefTextView;
//
//            ImageView imageView;
//
//
//
//            public ViewHolder(@NonNull View itemView) {
//                super(itemView);
//                idiomwordTextView = itemView.findViewById(R.id.idiom_word);
//                wordmeaningTextView = itemView.findViewById(R.id.word_mean);
//                worddefTextView = itemView.findViewById(R.id.word_def);
//                imageView = itemView.findViewById(R.id.imageView2);
//                itemView.setOnClickListener(this);
//            }
            private TextView idiomwordTextView;
            private TextView wordmeaningTextView;
            private TextView worddefTextView;
            private ImageView imageView; // Change the variable name to imageView

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                idiomwordTextView = itemView.findViewById(R.id.idiom_word);
                wordmeaningTextView = itemView.findViewById(R.id.word_mean);
                worddefTextView = itemView.findViewById(R.id.word_def);
                imageView = itemView.findViewById(R.id.imageView2); // Correct variable assignment
                itemView.setOnClickListener(this);
            }

            //            @Override
//            public void onClick(View v) {
//                // Random index
//                int randomIndex = random.nextInt(vocabularyList.size());
//                // Get random vocabulary
//                Vocabulary randomVocabulary = vocabularyList.get(randomIndex);
//                // Update views with random vocabulary
//                idiomwordTextView.setText(randomVocabulary.getIdiomword());
//                wordmeaningTextView.setText(randomVocabulary.getWordmean());
//                worddefTextView.setText(randomVocabulary.getWorddef());
//                Glide.with(context)
//                        .load(vocabularyList.getImg()) // Assuming getImageUrl() returns the URL of the image
//                        .into(holder.imageView2);
//
//
//            }
            @Override
            public void onClick(View v) {
                // Random index
                int randomIndex = random.nextInt(vocabularyList.size());
                // Get random vocabulary
                Vocabulary randomVocabulary = vocabularyList.get(randomIndex);
                // Update views with random vocabulary
                idiomwordTextView.setText(randomVocabulary.getIdiomword());
                wordmeaningTextView.setText(randomVocabulary.getWordmean());
                worddefTextView.setText(randomVocabulary.getWorddef());

                // Load the image using Glide
                Glide.with(itemView.getContext())
                        .load(randomVocabulary.getImg()) // Load the image URL of the random vocabulary
                        .into(imageView); // Use the imageView reference directly
            }

        }
    }
}
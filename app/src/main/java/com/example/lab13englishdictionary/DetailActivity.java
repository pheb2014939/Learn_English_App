package com.example.lab13englishdictionary;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DetailActivity extends AppCompatActivity {

    TextView detailDesc, detailTitle;
    ImageView detailImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
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

        detailDesc = findViewById(R.id.detailDesc);
        detailTitle = findViewById(R.id.detailTitle);
        detailImage = findViewById(R.id.detailImage);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            detailTitle.setText(bundle.getString("Title"));

            // Assuming bundle.getInt("Image") contains an integer resource ID
            detailImage.setImageResource(bundle.getInt("Image"));

            // Use HtmlCompat.fromHtml for HTML formatting
            String htmlDesc = getString(bundle.getInt("Desc"));
            CharSequence styledDesc = HtmlCompat.fromHtml(htmlDesc, HtmlCompat.FROM_HTML_MODE_LEGACY);
            detailDesc.setText(styledDesc);
        }

    }
}

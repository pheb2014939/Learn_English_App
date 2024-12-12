package com.example.lab13englishdictionary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;

import java.util.Collections;
import java.util.List;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.os.Handler;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashboardActivity extends AppCompatActivity {

    CountDownTimer countDownTimer;
    List<Modeclass> allQuestionList;
    int timerValue =20;
    int index=0;
    int correctCount = 0;
    int wrongCount = 0;
    TextView card_question, optiona, optionb, optionc, optiond, ic_exit;
    CardView cardOA, cardOB, cardOD, cardOC;

    ProgressBar progressbar;
    Modeclass modeclass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        TextView ic_exit = findViewById(R.id.ic_exit);
        ic_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomePage.class));

            }
        });


        Hooks();

        allQuestionList = Function3Activity.list1;
        Collections.shuffle(allQuestionList);
        modeclass = Function3Activity.list1.get(index);


        setAllData();

        countDownTimer = new CountDownTimer(20000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerValue = timerValue-1;
                progressbar.setProgress(timerValue);

            }

            @Override
            public void onFinish() {
                Dialog dialog = new Dialog(DashboardActivity.this);
                dialog.setContentView(R.layout.time_out_dialog);
                dialog.show();
                dialog.findViewById(R.id.btn_tryagain).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(DashboardActivity.this, Function3Activity.class);
                        startActivity(i);
                    }
                });
            }

        }.start();

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
    }

    private void setAllData() {
        card_question.setText(modeclass.getQuestion());
        optiona.setText(modeclass.getoA());
        optionb.setText(modeclass.getoB());
        optionc.setText(modeclass.getoC());
        optiond.setText(modeclass.getoD());



    }

    private void Hooks() {

        progressbar = findViewById(R.id.quiz_timer);
        card_question = findViewById(R.id.card_question);
        optiona = findViewById(R.id.card_optiona);
        optionb = findViewById(R.id.card_optionb);
        optionc = findViewById(R.id.card_optionc);
        optiond = findViewById(R.id.card_optiond);

        cardOA = findViewById(R.id.cardA);
        cardOB = findViewById(R.id.cardB);
        cardOC = findViewById(R.id.cardC);
        cardOD = findViewById(R.id.cardD);

    }



    private void GameWon() {
        Intent i = new Intent(DashboardActivity.this, WonActivity.class);
        i.putExtra("correct", correctCount);
        i.putExtra("wrong", correctCount);

        startActivity(i);
    }


    public void enableButton(){
        cardOA.setClickable(true);
        cardOB.setClickable(true);
        cardOC.setClickable(true);
        cardOD.setClickable(true);

    }
    public void disableButton(){
        cardOA.setClickable(false);
        cardOB.setClickable(false);
        cardOC.setClickable(false);
        cardOD.setClickable(false);

    }

    public void resetColor(){
        cardOA.setCardBackgroundColor(getResources().getColor(R.color.white));
        cardOB.setCardBackgroundColor(getResources().getColor(R.color.white));
        cardOC.setCardBackgroundColor(getResources().getColor(R.color.white));
        cardOD.setCardBackgroundColor(getResources().getColor(R.color.white));


    }
    public void OptionAClick(View view) {
        processAnswer(modeclass.getoA(), cardOA);
    }

    public void OptionBClick(View view) {
        processAnswer(modeclass.getoB(), cardOB);
    }

    public void OptionCClick(View view) {
        processAnswer(modeclass.getoC(), cardOC);
    }

    public void OptionDClick(View view) {
        processAnswer(modeclass.getoD(), cardOD);
    }

    private void processAnswer(String selectedOption, CardView selectedCard) {
        disableButton();

        if (selectedOption.equals(modeclass.getAns())) {
            selectedCard.setCardBackgroundColor(getResources().getColor(R.color.correct));
            correctCount++;
        } else {
            selectedCard.setCardBackgroundColor(getResources().getColor(R.color.red));
            wrongCount++;
        }

        // Automatically proceed to the next question after a delay
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (index < Function3Activity.list1.size() - 1) {
                    index++;
                    modeclass = Function3Activity.list1.get(index);
                    setAllData();
                    resetColor();
                    enableButton();
                } else {
                    GameWon();
                }
            }
        }, 300); // Delay in milliseconds before proceeding to the next question
    }

}





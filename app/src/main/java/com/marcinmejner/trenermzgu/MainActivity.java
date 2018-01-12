package com.marcinmejner.trenermzgu;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    CountDownTimer timer;
    Button goButton;
    TextView sumTextView;
    TextView timerTextView;
    ArrayList<Integer> answers = new ArrayList<>();
    int locationOfCorrectAnswer;
    TextView resoutlTextView;
    GridLayout grid;
    boolean graZaczęta = false;

    //POINTS
    TextView pointsTextView;
    int prawidłowaOdpowiedz = 0;
    int nieprawidłowaOdpowiedz = 0;

    //PRZYCISKI
    Button przycisk1;
    Button przycisk2;
    Button przycisk3;
    Button przycisk4;

    int incorrectAnswer;
    int a;
    int b;
    Random rand = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goButton = findViewById(R.id.goButton);
        sumTextView = findViewById(R.id.sumTextView);
        timerTextView = findViewById(R.id.timerTextView);
        przycisk1 = findViewById(R.id.przycisk1);
        przycisk2 = findViewById(R.id.przycisk2);
        przycisk3 = findViewById(R.id.przycisk3);
        przycisk4 = findViewById(R.id.przycisk4);
        pointsTextView = findViewById(R.id.pointsTextView);
        resoutlTextView = findViewById(R.id.resoultTextView);
        grid = findViewById(R.id.grid);





      losoweLiczby();



        locationOfCorrectAnswer = rand.nextInt(4);
        losowanie();


    }

    public void start(View view) {
        goButton.setVisibility(View.INVISIBLE);
        startujemy();
        odliczanie();
        graZaczęta = true;

    }

    public void choseAnswer(View view) {

        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {
            Log.d("correct", "choseAnswer: prawda ");
            prawidłowaOdpowiedz++;
            pointsTextView.setText(prawidłowaOdpowiedz + "/" + nieprawidłowaOdpowiedz);
            resoutlTextView.setTextColor(getResources().getColor(R.color.mójKolor));
            resoutlTextView.setText("Brawo!");
            resoutlTextView.setVisibility(View.VISIBLE);
            losoweLiczby();
            answers.clear();
            losowanie();

        } else {
            nieprawidłowaOdpowiedz++;
            pointsTextView.setText(prawidłowaOdpowiedz + "/" + nieprawidłowaOdpowiedz);
            resoutlTextView.setTextColor(Color.RED);
            resoutlTextView.setText("Błąd!");
            resoutlTextView.setVisibility(View.VISIBLE);
            losoweLiczby();
            answers.clear();
            losowanie();
        }


    }

    //
    public void odliczanie() {
        timer = new CountDownTimer(30000 + 100, 1000) {
            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l / 1000) + "s");

            }

            @Override
            public void onFinish() {


            }
        }.start();
    }

    public void losowanie() {
        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(a + b);
            } else {
                incorrectAnswer = rand.nextInt(41);
                while (incorrectAnswer == a + b) {
                    incorrectAnswer = rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }
        przycisk1.setText(Integer.toString(answers.get(0)));
        przycisk2.setText(Integer.toString(answers.get(1)));
        przycisk3.setText(Integer.toString(answers.get(2)));
        przycisk4.setText(Integer.toString(answers.get(3)));
    }

    public void losoweLiczby(){
        a = rand.nextInt(21);
        b = rand.nextInt(21);
        sumTextView.setText(a + " + " + b);

    }

    public void startujemy(){
        grid.setVisibility(View.VISIBLE);
        timerTextView.setVisibility(View.VISIBLE);
        pointsTextView.setVisibility(View.VISIBLE);
        sumTextView.setVisibility(View.VISIBLE);


    }

}

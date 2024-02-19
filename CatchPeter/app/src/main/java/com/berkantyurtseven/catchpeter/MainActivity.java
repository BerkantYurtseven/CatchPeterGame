package com.berkantyurtseven.catchpeter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Handler handler ;
    Runnable runnable;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView [] imageArray ;

    TextView TimerText ;
    TextView ScoreText ;
    int Score ;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TimerText = findViewById(R.id.textView);
        ScoreText  = findViewById(R.id.textView2);

        imageView =findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView1);
        imageView3 = findViewById(R.id.imageView2);
        imageView4 = findViewById(R.id.imageView3);
        imageView5 = findViewById(R.id.imageView4);
        imageView6 = findViewById(R.id.imageView5);

        imageArray = new ImageView[]{imageView, imageView2 ,imageView3 , imageView4,imageView5,imageView6};

        hideImages();



        Score = 0 ;
        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                TimerText.setText("Time : " + millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                TimerText.setText("Time is over.");
                handler.removeCallbacks(runnable);
                for(ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart");
                alert.setMessage("Do you wanna try again..");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);

                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Game Over", Toast.LENGTH_LONG).show();
                    }
                });
                alert.show();

            }
        }.start();
    }
    public void increaseScore (View view){

        Score ++;
        ScoreText.setText("Score : " +Score);


    }
    public void hideImages(){
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for(ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                Random random = new Random();
                int i = random.nextInt(6);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(runnable,500);

            }
        };
        handler.post(runnable);


    }
}
package com.example.sparksupportinfotech;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.sparksupportinfotech.DashBoard.DashBoardActivity;
import com.example.sparksupportinfotech.Login.LoginPage;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    TextView progressText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView logoImageView = findViewById(R.id.logoImageView);
        progressBar = findViewById(R.id.progressBar1);
        progressText = findViewById(R.id.progressText);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.splash_animation);
        logoImageView.startAnimation(animation);


        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // Animation started, you can start the progress update here
                updateProgressBar();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // Animation ended, start the next activity
                startActivity(new Intent(MainActivity.this, DashBoardActivity.class));
                finish();

//                SharedPreferences sharedPreferences=getSharedPreferences("myPreef", Context.MODE_PRIVATE);
//                int logincheck=sharedPreferences.getInt("check",0);
//                if (sharedPreferences.contains("check")) {
//                    // Data is present in SharedPreferences
//                    // You can also check the value if needed
//                    if (logincheck == 1) {
             //          startActivity(new Intent(MainActivity.this, DashBoardActivity.class));
//                        finish(); //
//                        // The value is 1
//                    } else {
//                        // The value is not 1
                  //      startActivity(new Intent(MainActivity.this, LoginPage.class));
//                        finish(); //
//                    }
//                } else {
 //                  startActivity(new Intent(MainActivity.this, LoginPage.class));
//                    finish();
//                    // Data is not present in SharedPreferences
//                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    private void updateProgressBar() {
        final int totalProgress = 100;
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            int progress = 0;

            @Override
            public void run() {
                if (progress <= totalProgress) {
                    progressBar.setProgress(progress);
                    progressText.setText(progress + "%");
                    progress++;
                    handler.postDelayed(this, 10); // Adjust the delay for smoother animation
                }
            }
        };
        handler.postDelayed(runnable, 10);
    }
}
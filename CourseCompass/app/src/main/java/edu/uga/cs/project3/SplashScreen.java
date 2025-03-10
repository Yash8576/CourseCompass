package edu.uga.cs.project3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash_screen);

        ImageView imageView = findViewById(R.id.splashImage);
        Button button = findViewById(R.id.exploreButton);

        imageView.setImageResource(R.drawable.grad_splash);

        button.setOnClickListener(
                v->{
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
        );


    }
}

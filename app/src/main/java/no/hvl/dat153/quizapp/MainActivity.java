package no.hvl.dat153.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button galleryButton = findViewById(R.id.galleryButton);

        galleryButton.setOnClickListener(v -> {
            Intent galleryIntent = new Intent(MainActivity.this, GalleryActivity.class);
            startActivity(galleryIntent);
                });

        Button quizButton = findViewById(R.id.quizButton);

        quizButton.setOnClickListener(v -> {
            Intent quizIntent = new Intent(MainActivity.this, QuizActivity.class);
            startActivity(quizIntent);
                });

        }
}
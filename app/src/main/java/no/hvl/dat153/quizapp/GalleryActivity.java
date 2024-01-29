package no.hvl.dat153.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class GalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        // Find ImageView-elements in the layout
        ImageView imageView1 = findViewById(R.id.imageView1);
        ImageView imageView2 = findViewById(R.id.imageView2);
        ImageView imageView3 = findViewById(R.id.imageView3);

        // Set the predefined images to ImageView-elements
        imageView1.setImageResource(R.drawable.sheep_image);
        imageView2.setImageResource(R.drawable.beer_image);
        imageView3.setImageResource(R.drawable.tree_image);

        Button addPhotoButton = findViewById(R.id.addPhotoButton);

        addPhotoButton.setOnClickListener(v -> {
            Intent AddPhotoIntent = new Intent(GalleryActivity.this, AddPhotoActivity.class);
            startActivity(AddPhotoIntent);
        });
    }
}
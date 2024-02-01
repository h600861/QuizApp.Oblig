package no.hvl.dat153.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        ImageView imageView = findViewById(R.id.galleryImageView);
        TextView textView = findViewById(R.id.galleryTextView); // Add this line

        Intent intent = getIntent();
        String imageUriString = intent.getStringExtra("imageUri");
        String enteredText = intent.getStringExtra("enteredText"); // Retrieve the entered text

        if (imageUriString != null) {
            Uri imageUri = Uri.parse(imageUriString);
            imageView.setImageURI(imageUri);
        } else {
            // Handle the case where imageUriString is null or not provided
            // For example, display a default image or show an error message
        }

        // Display the entered text in the TextView
        if (enteredText != null) {
            textView.setText(enteredText);
        }

        // Add Photo button (assuming the button has ID addPhotoButton)
        Button addPhotoButton = findViewById(R.id.addPhotoButton);
        addPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start addPhotoActivity when the "Add Photo" button is clicked
                Intent addPhotoIntent = new Intent(GalleryActivity.this, AddPhotoActivity.class);
                startActivity(addPhotoIntent);
            }
        });
    }
}
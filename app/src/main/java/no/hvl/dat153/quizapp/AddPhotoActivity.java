package no.hvl.dat153.quizapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddPhotoActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private ImageView imageView;
    private List<GalleryItem> galleryItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo);

        imageView = findViewById(R.id.imageView);
        Button chooseImageButton = findViewById(androidx.constraintlayout.widget.R.id.add);
        Button saveImageButton = findViewById(R.id.saveButton);

        chooseImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImageChooser();
            }
        });

        saveImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveImageToGallery();
            }
        });

        galleryItemList = new ArrayList<>();
    }

    private void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Velg bilde"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveImageToGallery() {
        // Få Bitmap fra ImageView
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();

        // Legg til bildet i galleriet
        galleryItemList.add(new GalleryItem(bitmap, "Beskrivelse")); // Endre "Beskrivelse" etter behov

        // Oppdater adapteren i GalleryActivity med den nye listen

        // Etter at bildet er lagret, kan du gå tilbake til GalleryActivity
        Intent galleryIntent = new Intent(AddPhotoActivity.this, GalleryActivity.class);
        startActivity(galleryIntent);
        finish();
    }

}

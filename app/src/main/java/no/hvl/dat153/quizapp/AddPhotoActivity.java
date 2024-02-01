package no.hvl.dat153.quizapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class AddPhotoActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo);

        Button add = findViewById(R.id.add);
        Button saveButton = findViewById(R.id.saveButton);
        ImageView imageView = findViewById(R.id.imageView);
        EditText editText = findViewById(R.id.editText);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setAction(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("image/*");
                startActivityForResult(i, PICK_IMAGE_REQUEST);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the entered text
                String enteredText = editText.getText().toString();

                // Get the selected image URI
                Drawable drawable = imageView.getDrawable();

                if (drawable instanceof BitmapDrawable) {
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                    Bitmap bitmap = bitmapDrawable.getBitmap();

                    // Save the bitmap to the device and get its URI
                    Uri imageUri = saveBitmapAndGetUri(bitmap);

                    // Create an Intent to pass data to the gallery activity
                    Intent intent = new Intent(AddPhotoActivity.this, GalleryActivity.class);
                    intent.putExtra("imageUri", imageUri.toString());
                    intent.putExtra("enteredText", enteredText); // Pass the entered text

                    // Start the gallery activity
                    startActivity(intent);
                }
            }
        });

    }

    // Override onActivityResult method to handle the result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            // Get the selected image URI
            Uri selectedImageUri = data.getData();

            // Now you can display the selected image on the screen
            ImageView imageView = findViewById(R.id.imageView);
            imageView.setImageURI(selectedImageUri);
        }
    }

    // Helper method to save a Bitmap to the device and get its URI
    private Uri saveBitmapAndGetUri(Bitmap bitmap) {
        try {
            File file = new File(getExternalFilesDir(null), "image.jpg");
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            return Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
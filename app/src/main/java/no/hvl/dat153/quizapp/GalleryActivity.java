package no.hvl.dat153.quizapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {
    private static final int REQUEST_ADD_PHOTO = 1;
    private GalleryAdapter galleryAdapter;
    private List<GalleryItem> galleryItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        // Initialize RecyclerView and Adapter
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        galleryItems = new ArrayList<>();

        // Add predefined photos
        galleryItems.add(new GalleryItem(R.drawable.sheep_image, getString(R.string.sheep)));
        galleryItems.add(new GalleryItem(R.drawable.beer_image, getString(R.string.beer)));
        galleryItems.add(new GalleryItem(R.drawable.tree_image, getString(R.string.tree)));

        galleryAdapter = new GalleryAdapter(galleryItems);
        recyclerView.setAdapter(galleryAdapter);

        galleryAdapter.setOnItemClickListener(this::showConfirmationDialog);
        // Button to add a photo
        Button addPhotoButton = findViewById(R.id.addPhotoButton);
        addPhotoButton.setOnClickListener(v -> {
            Intent intent = new Intent(GalleryActivity.this, AddPhotoActivity.class);
            startActivityForResult(intent, REQUEST_ADD_PHOTO);
        });

        // Sort buttons
        Button sortButtonAToZ = findViewById(R.id.sortButtonAToZ);
        sortButtonAToZ.setOnClickListener(view -> galleryAdapter.sortByAlphabet());

        Button sortButtonZToA = findViewById(R.id.sortButtonZToA);
        sortButtonZToA.setOnClickListener(view -> galleryAdapter.sortByReverseAlphabet());
    }
    private void showConfirmationDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Er du sikker på at du vil slette dette bildet?")
                .setPositiveButton("Ja", (dialog, which) -> {
                    deleteImage(position);
                    Toast.makeText(this, "Bildet er slettet", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Nei", (dialog, which) -> {
                    dialog.dismiss();
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void deleteImage(int position) {
        galleryItems.remove(position);
        galleryAdapter.notifyItemRemoved(position);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_ADD_PHOTO && resultCode == RESULT_OK && data != null) {
            byte[] newImageBitmapBytes = data.getByteArrayExtra("imageBitmapBytes");
            String newText = data.getStringExtra("text");

            if (newImageBitmapBytes != null) {
                // Convert byte array to bitmap
                Bitmap newImageBitmap = ImageUtils.convertByteArrayToBitmap(newImageBitmapBytes);

                // Add the new item with bitmap to galleryItems
                galleryItems.add(new GalleryItem(newImageBitmap, newText));

                // Refresh the adapter
                galleryAdapter.notifyDataSetChanged();
            }
        }
    }
}
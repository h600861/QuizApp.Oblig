package no.hvl.dat153.quizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {

    private GalleryAdapter galleryAdapter;
    private List<GalleryItem> galleryItemList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        galleryItemList = new ArrayList<>();
        galleryItemList.add(new GalleryItem(R.drawable.sheep_image, getString(R.string.sheep)));
        galleryItemList.add(new GalleryItem(R.drawable.beer_image, getString(R.string.beer)));
        galleryItemList.add(new GalleryItem(R.drawable.tree_image, getString(R.string.tree)));

        galleryAdapter = new GalleryAdapter(galleryItemList);
        recyclerView.setAdapter(galleryAdapter);

        galleryAdapter.setOnItemClickListener(this::showConfirmationDialog);

        Button addPhotoButton = findViewById(R.id.addPhotoButton);
        addPhotoButton.setOnClickListener(view -> {
            Intent AddPhotoIntent = new Intent(GalleryActivity.this, AddPhotoActivity.class);
            startActivity(AddPhotoIntent);
        });

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
        galleryItemList.remove(position);
        galleryAdapter.notifyItemRemoved(position);
    }
}

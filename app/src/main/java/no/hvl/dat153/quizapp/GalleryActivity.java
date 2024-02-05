package no.hvl.dat153.quizapp;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

        List<GalleryItem> galleryItemList = new ArrayList<>();
        galleryItemList.add(new GalleryItem(R.drawable.sheep_image, getString(R.string.sheep)));
        galleryItemList.add(new GalleryItem(R.drawable.beer_image, getString(R.string.beer)));
        galleryItemList.add(new GalleryItem(R.drawable.tree_image, getString(R.string.tree)));

        galleryAdapter = new GalleryAdapter(galleryItemList);
        recyclerView.setAdapter(galleryAdapter);

        // Add Photo button
        Button addPhotoButton = findViewById(R.id.addPhotoButton);
        addPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent AddPhotoIntent = new Intent(GalleryActivity.this, AddPhotoActivity.class);
                startActivity(AddPhotoIntent);
            }
        });

        Button sortButtonAToZ = findViewById(R.id.sortButtonAToZ);
        sortButtonAToZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                galleryAdapter.sortByAlphabet();
            }
        });

        Button sortButtonZToA = findViewById(R.id.sortButtonZToA);
        sortButtonZToA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                galleryAdapter.sortByReverseAlphabet();
            }
        });
    }

}

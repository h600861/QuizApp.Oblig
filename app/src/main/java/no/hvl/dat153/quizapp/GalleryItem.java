package no.hvl.dat153.quizapp;

import android.graphics.Bitmap;

public class GalleryItem {

    private int imageResource; // Ressurs-ID for gamle bilder (for bakoverkompatibilitet)
    private Bitmap imageBitmap; // Bitmap for nye bilder
    private String description;

    // Constructor for gamle bilder
    public GalleryItem(int imageResource, String description) {
        this.imageResource = imageResource;
        this.description = description;
    }

    // Constructor for nye bilder
    public GalleryItem(Bitmap imageBitmap, String description) {
        this.imageBitmap = imageBitmap;
        this.description = description;
    }

    // Metode for å få Ressurs-ID
    public int getImageResource() {
        return imageResource;
    }

    // Metode for å få Bitmap
    public Bitmap getImageBitmap() {
        return imageBitmap;
    }

    // Metode for å få beskrivelse
    public String getDescription() {
        return description;
    }
}


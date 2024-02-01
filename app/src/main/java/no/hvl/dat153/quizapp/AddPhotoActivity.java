package no.hvl.dat153.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class AddPhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo);

        /*@Override
        public void onActivityResult(int x, int y, Intent i) {
            super.onActivityResult(x,y,i);
            Log.d("vs", i.getData().toString());
            Bitmap img = null;
            try {
                img = getBitmapFromUri(i.getData());
            } catch (Exception e) {
                Log.d("vs", "Failed");
            }
            ImageView iv = findViewById(R.id.image);
            iv.setImageBitmap(img);
        }*/
    }
}
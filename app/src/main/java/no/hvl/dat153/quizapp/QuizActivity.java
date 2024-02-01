package no.hvl.dat153.quizapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private ImageView questionImage;
    private Button option1Button, option2Button, option3Button;

    private List<Integer> imageList;
    private int currentImageIndex;
    private int remainingAttempts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_activty);

        questionImage = findViewById(R.id.questionImage);
        option1Button = findViewById(R.id.option1Button);
        option2Button = findViewById(R.id.option2Button);
        option3Button = findViewById(R.id.option3Button);

        imageList = new ArrayList<>();
        imageList.add(R.drawable.sheep_image);
        imageList.add(R.drawable.beer_image);
        imageList.add(R.drawable.tree_image);

        currentImageIndex = 0;
        remainingAttempts = 3;
        loadCurrentImage();
    }

    private void loadCurrentImage() {
        int currentImageResource = imageList.get(currentImageIndex);
        questionImage.setImageResource(currentImageResource);

        List<Button> optionButtons = Arrays.asList(option1Button, option2Button, option3Button);
        Collections.shuffle(optionButtons);

        String correctAnswer = getResourceName(currentImageResource);
        List<Answers> randomizedAnswers = Dictionary.getRandomizedAnswers(optionButtons.size() - 1);

        // Sett tekstbeskrivelser for bildet
        for (int i = 0; i < optionButtons.size(); i++) {
            if (i == 0) {
                optionButtons.get(i).setText(correctAnswer);
                optionButtons.get(i).setTag(correctAnswer);
            } else {
                optionButtons.get(i).setText(randomizedAnswers.get(i - 1).getName());
                optionButtons.get(i).setTag(randomizedAnswers.get(i - 1).getName());
            }
        }
    }


    private String getResourceName(int imageResource) {
        return getResources().getResourceEntryName(imageResource);
    }

    public void checkAnswer(View view) {
        Button clickedButton = (Button) view;

        String correctAnswer = getResourceName(imageList.get(currentImageIndex));

        if (clickedButton.getTag().equals(correctAnswer)) {
            Toast.makeText(this, "Riktig svar!", Toast.LENGTH_SHORT).show();

            if (currentImageIndex < imageList.size() - 1) {
                currentImageIndex++;
                remainingAttempts = 3;
                loadCurrentImage();
            } else {
                Toast.makeText(this, "Gratulerer, du har fullført quizen!", Toast.LENGTH_SHORT).show();
                finish();
            }
        } else {
            remainingAttempts--;

            if (remainingAttempts > 0) {
                Toast.makeText(this, "Feil svar. Du har " + remainingAttempts + " forsøk igjen.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Feil svar. Du har brukt opp alle forsøk. Prøv neste bilde.", Toast.LENGTH_SHORT).show();

                if (currentImageIndex < imageList.size() - 1) {
                    currentImageIndex++;
                    remainingAttempts = 3;
                    loadCurrentImage();
                } else {
                    Toast.makeText(this, "Gratulerer, du har fullført quizen!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }
}

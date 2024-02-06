package no.hvl.dat153.quizapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private ImageView questionImage;
    private Button option1Button, option2Button, option3Button;
    private Button exitQuizButton;
    private List<Integer> imageList;
    private int currentImageIndex;
    private int score;

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
        score = 0;
        updateScore();
        loadCurrentImage();

        exitQuizButton = findViewById(R.id.exitQuizButton);
        exitQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishQuiz();
            }
        });
    }

    private void loadCurrentImage() {
        int currentImageResource = imageList.get(currentImageIndex);
        questionImage.setImageResource(currentImageResource);

        List<Button> optionButtons = Arrays.asList(option1Button, option2Button, option3Button);
        Collections.shuffle(optionButtons);

        String correctAnswer = getCustomAnswer();
        List<Answers> incorrectAnswers = Dictionary.getRandomizedAnswers(optionButtons.size() - 1);

        for (int i = 0; i < optionButtons.size(); i++) {
            if (i == 0) {
                optionButtons.get(i).setText(correctAnswer);
                optionButtons.get(i).setTag(correctAnswer);
            } else {
                optionButtons.get(i).setText(incorrectAnswers.get(i - 1).getName());
                optionButtons.get(i).setTag(incorrectAnswers.get(i - 1).getName());
            }
        }
    }


    private String getCustomAnswer() {
        switch (currentImageIndex) {
            case 0:
                return "Sau";
            case 1:
                return "Øl";
            case 2:
                return "Tre";
            default:
                return "Standard svar";
        }
    }


    private List<String> getRandomizedAnswers(String correctAnswer) {
        List<String> allAnswers = new ArrayList<>();
        allAnswers.add(correctAnswer);
        allAnswers.add("Feil svar 1");
        allAnswers.add("Feil svar 2");

        Collections.shuffle(allAnswers);

        return allAnswers;
    }

    public void checkAnswer(View view) {
        Button clickedButton = (Button) view;
        String selectedAnswer = clickedButton.getTag().toString();

        String correctAnswer = getCustomAnswer();

        if (selectedAnswer.equals(correctAnswer)) {
            Toast.makeText(this, "Riktig svar!", Toast.LENGTH_SHORT).show();
            score++;
        } else {
            Toast.makeText(this, "Feil svar. Riktig svar er: " + correctAnswer, Toast.LENGTH_SHORT).show();
        }

        updateScore();
        if (currentImageIndex < imageList.size() - 1) {
            currentImageIndex++;
            loadCurrentImage();
        } else {
            //showQuizResult();
            currentImageIndex = 0;
            loadCurrentImage();
        }
    }

    private void updateScore() {
        TextView scoreTextView = findViewById(R.id.scoreTextView);
        if (scoreTextView != null) {
            scoreTextView.setText("Poengsum: " + score + "/" + imageList.size());
        }
    }

    private void showQuizResult() {
        Toast.makeText(this, "Gratulerer, du har fullført quizen!\nPoengsum: " + score + "/" + imageList.size(), Toast.LENGTH_LONG).show();
        finish();
    }

    private void finishQuiz() {
        Toast.makeText(this, "Quiz avsluttet!\nPoengsum: " + score + "/" + imageList.size(), Toast.LENGTH_LONG).show();
        finish();
    }
}

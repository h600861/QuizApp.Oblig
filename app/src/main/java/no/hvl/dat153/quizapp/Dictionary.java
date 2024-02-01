package no.hvl.dat153.quizapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class Dictionary {

    private static List<Answers> answerList = Arrays.asList(
            new Answers("Bil"),
            new Answers("Hund"),
            new Answers("Sjiraff"),
            new Answers("Blomst"),
            new Answers("Brus")
            // Legg til flere svar etter behov
    );

    public static List<Answers> getRandomizedAnswers(int count) {
        List<Answers> randomizedAnswers = new ArrayList<>(answerList);
        Collections.shuffle(randomizedAnswers);
        return randomizedAnswers.subList(0, count);
    }
}




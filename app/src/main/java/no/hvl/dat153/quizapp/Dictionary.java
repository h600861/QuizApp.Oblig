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
            new Answers("Brus"),
            new Answers("Troll"),
            new Answers("Menneske"),
            new Answers("Telefon"),
            new Answers("Solsikke"),
            new Answers("Fjell"),
            new Answers("Kaffe"),
            new Answers("Regnbue"),
            new Answers("Musikk"),
            new Answers("Sjokolade"),
            new Answers("Sommer"),
            new Answers("Vindu"),
            new Answers("Laptop"),
            new Answers("Drage")
    );

    public static List<Answers> getRandomizedAnswers(int count) {
        List<Answers> randomizedAnswers = new ArrayList<>(answerList);
        Collections.shuffle(randomizedAnswers);
        return randomizedAnswers.subList(0, count);
    }
}




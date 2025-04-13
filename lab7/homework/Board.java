package org.example;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<String> words = new ArrayList<>();

    public synchronized void submitWord(String word) {
        words.add(word);
        //System.out.println("Word submitted: " + word);
    }

    public synchronized List<String> getWords() {
        return new ArrayList<>(words);
    }
}
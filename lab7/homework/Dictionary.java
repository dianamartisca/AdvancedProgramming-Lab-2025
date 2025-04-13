package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Dictionary {
    private final Set<String> words;

    public Dictionary(String fileName) {
        words = new HashSet<>();
        loadWords(fileName);
    }

    private void loadWords(String fileName) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(fileName))))) {
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line.trim().toLowerCase());
            }
        } catch (Exception e) {
            System.err.println("Error reading dictionary file: " + e.getMessage());
        }
    }

    public boolean isWord(String word) {
        return words.contains(word.toLowerCase());
    }
}
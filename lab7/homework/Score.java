package org.example;

import java.util.List;

public class Score {
    private int points;

    public Score() {
        this.points = 0;
    }

    public synchronized void addPoints(int points) {
        this.points += points;
    }

    public synchronized int getPoints() {
        return points;
    }

    public int calculateWordScore(String word, List<Tile> hand) {
        int totalScore = 0;
        for (char c : word.toCharArray()) {
            for (Tile tile : hand) {
                if (tile.getLetter() == c) {
                    totalScore += tile.getPoints();
                    break;
                }
            }
        }
        return totalScore;
    }
}
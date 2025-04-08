package org.example;

import javax.swing.*;
import java.awt.Color;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Score implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private double playerOneScore = 0;
    private double playerTwoScore = 0;

    public void updateScore(Line line) {
        double length = line.getLength();
        if (line.color == Color.BLUE) {
            playerOneScore += length;
        } else {
            playerTwoScore += length;
        }
    }

    public void reset() {
        playerOneScore = 0;
        playerTwoScore = 0;
    }

    public void showScores(List<Dot> dots) {
        List<Line> mstLines = getMSTLines(dots);
        mstLines.sort(Comparator.comparingDouble(Line::getLength));

        double bestPossibleScorePlayerOne = 0;
        double bestPossibleScorePlayerTwo = 0;

        for (int i = 0; i < mstLines.size(); i++) {
            if (i % 2 == 0) {
                bestPossibleScorePlayerOne += mstLines.get(i).getLength();
            } else {
                bestPossibleScorePlayerTwo += mstLines.get(i).getLength();
            }
        }

        String message = String.format("Player One Score: %.2f\nPlayer Two Score: %.2f\nBest Possible Score (Player One): %.2f\nBest Possible Score (Player Two): %.2f",
                playerOneScore, playerTwoScore, bestPossibleScorePlayerOne, bestPossibleScorePlayerTwo);
        JOptionPane.showMessageDialog(null, message);
    }

    private List<Line> getMSTLines(List<Dot> dots) {
        List<Line> allPossibleLines = new ArrayList<>();
        for (int i = 0; i < dots.size(); i++) {
            for (int j = i + 1; j < dots.size(); j++) {
                allPossibleLines.add(new Line(dots.get(i), dots.get(j), Color.BLACK));
            }
        }

        allPossibleLines.sort(Comparator.comparingDouble(Line::getLength));

        UnionFind uf = new UnionFind(dots.size());
        List<Line> mstLines = new ArrayList<>();
        for (Line line : allPossibleLines) {
            int startIdx = dots.indexOf(line.start);
            int endIdx = dots.indexOf(line.end);
            if (uf.union(startIdx, endIdx)) {
                mstLines.add(line);
            }
        }
        return mstLines;
    }
}
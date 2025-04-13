package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Player extends Thread {
    private final String name;
    private final Bag bag;
    private final Board board;
    private final Dictionary dictionary;
    private final Lock lock;
    private final Condition condition;
    private final int playerId;
    private final Score score;
    private static int currentPlayer = 1;
    private final List<Tile> hand;

    public Player(String name, Bag bag, Board board, Dictionary dictionary, Lock lock, Condition condition, int playerId, Score score) {
        this.name = name;
        this.bag = bag;
        this.board = board;
        this.dictionary = dictionary;
        this.lock = lock;
        this.condition = condition;
        this.playerId = playerId;
        this.score = score;
        this.hand = new ArrayList<>();
    }

    @Override
    public void run() {
        initializeHand();
        while (true) {
            lock.lock();
            try {
                while (currentPlayer != playerId) {
                    condition.await();
                }
                if (bag.isEmpty() && hand.isEmpty()) {
                    currentPlayer = (currentPlayer % 2) + 1;
                    condition.signalAll();
                    break;
                }
                StringBuilder letters = new StringBuilder();
                for (Tile tile : hand) {
                    letters.append(tile.getLetter());
                }
                String word = findValidWord(letters.toString());
                if (word != null) {
                    board.submitWord(word);
                    int wordScore = score.calculateWordScore(word, hand);
                    score.addPoints(wordScore);
                    System.out.println(name + " submitted word: " + word + " (Score: " + wordScore + ")");
                    replaceUsedTiles(word);
                } else {
                    replaceUsedTiles(letters.toString());
                    System.out.println(name + " could not form a valid word and passed.");
                }
                currentPlayer = (currentPlayer % 2) + 1;
                condition.signalAll();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            } finally {
                lock.unlock();
            }
        }
    }

    private void initializeHand() {
        hand.addAll(bag.extractTiles(7));
    }

    private void replaceUsedTiles(String word) {
        List<Tile> usedTiles = new ArrayList<>();
        for (char c : word.toCharArray()) {
            for (Tile tile : hand) {
                if (tile.getLetter() == c) {
                    usedTiles.add(tile);
                    break;
                }
            }
        }
        hand.removeAll(usedTiles);
        hand.addAll(bag.extractTiles(usedTiles.size()));
    }

    private String findValidWord(String letters) {
        List<String> subsets = new ArrayList<>();
        generateSubsets("", letters, subsets);

        for (String subset : subsets) {
            String validWord = findValidPermutation("", subset);
            if (validWord != null) {
                return validWord;
            }
        }
        return null;
    }

    private String findValidPermutation(String prefix, String remaining) {
        if (remaining.isEmpty()) {
            if (dictionary.isWord(prefix.toLowerCase())) {
                return prefix;
            }
        } else {
            for (int i = 0; i < remaining.length(); i++) {
                String result = findValidPermutation(
                        prefix + remaining.charAt(i),
                        remaining.substring(0, i) + remaining.substring(i + 1)
                );
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    private void generateSubsets(String prefix, String remaining, List<String> subsets) {
        for (int i = 0; i < remaining.length(); i++) {
            generateSubsets(prefix + remaining.charAt(i), remaining.substring(i + 1), subsets);
        }
        if (!prefix.isEmpty()) {
            subsets.add(prefix);
        }
    }

    public int getScore() {
        return score.getPoints();
    }

    public String getPlayerName() {
        return name;
    }
}
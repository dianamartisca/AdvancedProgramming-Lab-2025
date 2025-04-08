package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Bag {
    private final List<Tile> tiles = new ArrayList<>();

    public Bag() {
        for (char letter = 'A'; letter <= 'Z'; letter++) {
            Random random = new Random();
            int points = random.nextInt(10) + 1;
            for (int i = 0; i < 10; i++) {
                tiles.add(new Tile(letter, points));
            }
        }
        Collections.shuffle(tiles);
    }

    public synchronized List<Tile> extractTiles(int count) {
        List<Tile> extracted = new ArrayList<>();
        for (int i = 0; i < count && !tiles.isEmpty(); i++) {
            extracted.add(tiles.removeLast());
        }
        return extracted;
    }

    public synchronized boolean isEmpty() {
        return tiles.isEmpty();
    }
}
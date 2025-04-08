package org.example;

import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Player extends Thread {
    private final String name;
    private final Bag bag;
    private final Board board;
    private final Lock lock;
    private final Condition condition;
    private final int playerId;
    private static int currentPlayer = 1;

    public Player(String name, Bag bag, Board board, Lock lock, Condition condition, int playerId) {
        this.name = name;
        this.bag = bag;
        this.board = board;
        this.lock = lock;
        this.condition = condition;
        this.playerId = playerId;
    }

    @Override
    public void run() {
        while (!bag.isEmpty()) {
            lock.lock();
            try {
                while (currentPlayer != playerId) {
                    condition.await();
                }

                List<Tile> tiles = bag.extractTiles(7);
                if (tiles.isEmpty()) break;

                System.out.println(name + " extracted: " + tiles);

                StringBuilder word = new StringBuilder();
                for (Tile tile : tiles) {
                    word.append(tile.getLetter());
                }
                board.submitWord(word.toString());

                currentPlayer = (currentPlayer % 2) + 1;
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
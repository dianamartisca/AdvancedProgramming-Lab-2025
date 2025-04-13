package org.example;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        Bag bag = new Bag();
        Board board = new Board();
        Dictionary dictionary = new Dictionary("dictionary.txt");
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        Score score1 = new Score();
        Score score2 = new Score();

        Player player1 = new Player("Player 1", bag, board, dictionary, lock, condition, 1, score1);
        Player player2 = new Player("Player 2", bag, board, dictionary, lock, condition, 2, score2);

        Timekeeper timekeeper = new Timekeeper(5000, player1, player2);
        timekeeper.start();

        player1.start();
        player2.start();

        try {
            player1.join();
            player2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Game over! Words on the board: " + board.getWords());
        System.out.println("Score Player 1: " + player1.getScore());
        System.out.println("Score Player 2: " + player2.getScore());
        System.out.println("Winner: " + (player1.getScore() > player2.getScore() ? player1.getPlayerName() : player2.getPlayerName()));
    }
}
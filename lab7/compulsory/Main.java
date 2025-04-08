package org.example;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        Bag bag = new Bag();
        Board board = new Board();
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        Player player1 = new Player("Player 1", bag, board, lock, condition, 1);
        Player player2 = new Player("Player 2", bag, board, lock, condition, 2);

        player1.start();
        player2.start();

        try {
            player1.join();
            player2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Game over! Words on the board: " + board.getWords());
    }
}
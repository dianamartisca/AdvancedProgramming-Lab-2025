package org.example;

public class Timekeeper extends Thread {
    private final long timeLimitMillis;
    private final Thread[] players;

    public Timekeeper(long timeLimitMillis, Thread... players) {
        this.timeLimitMillis = timeLimitMillis;
        this.players = players;
        setDaemon(true);
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < timeLimitMillis) {
            try {
                Thread.sleep(1000);
                long elapsed = (System.currentTimeMillis() - startTime) / 1000;
                System.out.println("Time elapsed: " + elapsed + " seconds");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Time limit reached! Stopping the game...");
        for (Thread player : players) {
            player.interrupt();
        }
    }
}
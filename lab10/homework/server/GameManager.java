package org.example;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class GameManager {
    private final ConcurrentHashMap<Integer, HexGame> games = new ConcurrentHashMap<>();
    private final AtomicInteger gameId = new AtomicInteger(1);

    public int createGame(int size) {
        int id = gameId.getAndIncrement();
        games.put(id, new HexGame(size));
        return id;
    }

    public HexGame getGame(int id) {
        return games.get(id);
    }
}
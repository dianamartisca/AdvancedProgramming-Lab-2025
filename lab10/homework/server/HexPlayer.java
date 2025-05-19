package org.example;

public class HexPlayer {
    private final String name;
    private final String color;
    private long timeLeftMillis;

    public HexPlayer(String name, String color, long initialTimeMillis) {
        this.name = name;
        this.color = color;
        this.timeLeftMillis = initialTimeMillis;
    }

    public String getName() { return name; }
    public String getColor() { return color; }
    public long getTimeLeftMillis() { return timeLeftMillis; }
    public void deductTime(long millis) { timeLeftMillis -= millis; }
}
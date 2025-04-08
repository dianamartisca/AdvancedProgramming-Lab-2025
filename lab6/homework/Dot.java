package org.example;

import java.io.Serial;
import java.io.Serializable;

public class Dot implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    int x, y;

    public Dot(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
package org.example;

import java.awt.Color;
import java.io.Serial;
import java.io.Serializable;

public class Line implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    Dot start, end;
    Color color;

    public Line(Dot start, Dot end, Color color) {
        this.start = start;
        this.end = end;
        this.color = color;
    }

    public double getLength() {
        return Math.sqrt(Math.pow(start.x - end.x, 2) + Math.pow(start.y - end.y, 2));
    }
}
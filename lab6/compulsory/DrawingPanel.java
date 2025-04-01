package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class DrawingPanel extends JPanel {
    private List<Dot> dots = new ArrayList<>();
    private List<Line> lines = new ArrayList<>();
    private Random random = new Random();

    public void generateDots(int numDots) {
        dots.clear();
        for (int i = 0; i < numDots; i++) {
            dots.add(new Dot(random.nextInt(700) + 50, random.nextInt(400) + 50));
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        for (Dot dot : dots) {
            g.fillOval(dot.x - 5, dot.y - 5, 10, 10);
        }
    }
}

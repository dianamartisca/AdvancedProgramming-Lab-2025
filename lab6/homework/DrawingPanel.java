package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class DrawingPanel extends JPanel {
    private List<Dot> dots = new ArrayList<>();
    private List<Line> lines = new ArrayList<>();
    private Random random = new Random();
    private Dot selectedDot = null;
    private boolean isPlayerOneTurn = true;
    private Score score = new Score();

    public DrawingPanel() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Dot clickedDot = getDotAt(e.getX(), e.getY());
                if (clickedDot != null) {
                    if (selectedDot == null) {
                        selectedDot = clickedDot;
                    } else {
                        Color lineColor = isPlayerOneTurn ? Color.BLUE : Color.RED;
                        Line newLine = new Line(selectedDot, clickedDot, lineColor);
                        addLine(newLine);
                        score.updateScore(newLine);
                        selectedDot = null;
                        isPlayerOneTurn = !isPlayerOneTurn;
                        if (areAllDotsConnected()) {
                            score.showScores(dots);
                        }
                    }
                }
            }
        });
    }

    public void generateDots(int numDots) {
        dots.clear();
        lines.clear();
        score.reset();
        for (int i = 0; i < numDots; i++) {
            dots.add(new Dot(random.nextInt(700) + 50, random.nextInt(400) + 50));
        }
        repaint();
    }

    public void addLine(Line line) {
        lines.add(line);
        repaint();
    }

    private Dot getDotAt(int x, int y) {
        for (Dot dot : dots) {
            if (Math.abs(dot.x - x) < 10 && Math.abs(dot.y - y) < 10) {
                return dot;
            }
        }
        return null;
    }

    private boolean areAllDotsConnected() {
        if (dots.isEmpty()) return true;
        boolean[] visited = new boolean[dots.size()];
        dfs(dots.getFirst(), visited);
        for (boolean v : visited) {
            if (!v) return false;
        }
        return true;
    }

    private void dfs(Dot dot, boolean[] visited) {
        int index = dots.indexOf(dot);
        if (index == -1 || visited[index]) return;
        visited[index] = true;
        for (Line line : lines) {
            if (line.start.equals(dot)) {
                dfs(line.end, visited);
            } else if (line.end.equals(dot)) {
                dfs(line.start, visited);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        for (Dot dot : dots) {
            g.fillOval(dot.x - 5, dot.y - 5, 10, 10);
        }
        for (Line line : lines) {
            g.setColor(line.color);
            g.drawLine(line.start.x, line.start.y, line.end.x, line.end.y);
        }
    }

    public void exportToPNG(String filePath) {
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        paint(g2d);
        g2d.dispose();
        try {
            ImageIO.write(image, "png", new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveGame(String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(dots);
            oos.writeObject(lines);
            oos.writeObject(score);
            oos.writeBoolean(isPlayerOneTurn);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadGame(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            dots = (List<Dot>) ois.readObject();
            lines = (List<Line>) ois.readObject();
            score = (Score) ois.readObject();
            isPlayerOneTurn = ois.readBoolean();
            repaint();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

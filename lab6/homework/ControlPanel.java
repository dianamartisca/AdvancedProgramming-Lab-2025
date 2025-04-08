package org.example;

import javax.swing.*;
import java.awt.*;

class ControlPanel extends JPanel {
    public ControlPanel(MainFrame frame) {
        setLayout(new FlowLayout());
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        JButton exportButton = new JButton("Export to PNG");
        JButton exitButton = new JButton("Exit");

        saveButton.addActionListener(e -> frame.getDrawingPanel().saveGame("game_state.ser"));
        loadButton.addActionListener(e -> frame.getDrawingPanel().loadGame("game_state.ser"));
        exportButton.addActionListener(e -> frame.getDrawingPanel().exportToPNG("game_board.png"));
        exitButton.addActionListener(e -> System.exit(0));

        add(saveButton);
        add(loadButton);
        add(exportButton);
        add(exitButton);
    }
}

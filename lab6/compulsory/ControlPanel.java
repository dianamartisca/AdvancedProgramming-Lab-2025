package org.example;

import javax.swing.*;
import java.awt.*;

class ControlPanel extends JPanel {
    public ControlPanel(MainFrame frame) {
        setLayout(new FlowLayout());
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        JButton exitButton = new JButton("Exit");

        exitButton.addActionListener(e -> System.exit(0));

        add(saveButton);
        add(loadButton);
        add(exitButton);
    }
}

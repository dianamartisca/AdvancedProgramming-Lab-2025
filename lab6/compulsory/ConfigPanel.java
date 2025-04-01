package org.example;

import javax.swing.*;
import java.awt.*;

class ConfigPanel extends JPanel {
    private JTextField dotsField;
    private JButton startButton;

    public ConfigPanel(MainFrame frame) {
        setLayout(new FlowLayout());
        add(new JLabel("Number of Dots:"));
        dotsField = new JTextField(5);
        add(dotsField);
        startButton = new JButton("Start Game");
        add(startButton);

        startButton.addActionListener(e -> {
            int numDots = Integer.parseInt(dotsField.getText());
            frame.startNewGame(numDots);
        });
    }
}
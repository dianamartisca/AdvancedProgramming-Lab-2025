package org.example;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    private final List<Image> images = new ArrayList<>();

    public void addImage(Image image) {
        images.add(image);
    }

    public void displayImage(String name) {
        for (Image image : images) {
            if (image.name().equalsIgnoreCase(name)) {
                File file = new File(image.filePath());
                if (file.exists()) {
                    try {
                        Desktop.getDesktop().open(file);
                    } catch (IOException e) {
                        System.out.println("Error opening file: " + e.getMessage());
                    }
                } else {
                    System.out.println("File not found: " + image.filePath());
                }
                return;
            }
        }
        System.out.println("Image not found: " + name);
    }
}

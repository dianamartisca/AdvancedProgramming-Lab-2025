package org.example;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class UpdateCommand implements Command {
    @Override
    public void execute(Repository repository, String[] args) throws ImageNotFound, InvalidCommand {
        if (args.length < 5) {
            throw new InvalidCommand("Usage: update <oldName> <newName> <newDate> <newTags> <newFilePath>");
        }

        String oldName = args[0];
        String newName = args[1];
        LocalDate newDate = LocalDate.parse(args[2]);
        List<String> newTags = Arrays.asList(args[3].split(","));
        String newFilePath = args[4];

        for (Image img : repository.getImages()) {
            if (img.name().equalsIgnoreCase(oldName)) {
                repository.getImages().remove(img);
                repository.addImage(new Image(newName, newDate, newTags, newFilePath));
                System.out.println("Image updated: " + oldName + " -> " + newName);
                return;
            }
        }

        throw new ImageNotFound("Image not found: " + oldName);
    }
}

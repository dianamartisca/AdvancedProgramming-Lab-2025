package org.example;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class AddCommand implements Command {
    @Override
    public void execute(Repository repository, String[] args) throws InvalidCommand {
        if (args.length < 4) {
            throw new InvalidCommand("Usage: add <name> <date> <tags> <filePath>");
        }
        String name = args[0];
        LocalDate date = LocalDate.parse(args[1]);
        List<String> tags = Arrays.asList(args[2].split(","));
        String filePath = args[3];

        repository.addImage(new Image(name, date, tags, filePath));
        System.out.println("Image added: " + name);
    }
}

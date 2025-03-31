package org.example;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class SaveCommand implements Command {
    @Override
    public void execute(Repository repository, String[] args) throws IOException, InvalidCommand {
        if (args.length < 1) {
            throw new InvalidCommand("Usage: save <filePath>");
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        try {
            mapper.writeValue(new File(args[0]), repository.getImages());
            System.out.println("Repository saved to " + args[0]);
        } catch (IOException e) {
            System.err.println("Error saving repository: " + e.getMessage());
        }
    }
}

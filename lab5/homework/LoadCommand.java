package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class LoadCommand implements Command {
    @Override
    public void execute(Repository repository, String[] args) throws IOException, InvalidCommand {
        if (args.length < 1) {
            throw new InvalidCommand("Usage: load <filePath>");
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        File file = new File(args[0]);
        try {
            List<Image> images = mapper.readValue(file, new TypeReference<List<Image>>() {});
            repository.getImages().clear();
            repository.getImages().addAll(images);
            System.out.println("Repository loaded from " + args[0]);
        } catch (IOException e) {
            System.err.println("Error loading JSON file: " + e.getMessage());
        }
    }
}

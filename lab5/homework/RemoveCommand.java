package org.example;

public class RemoveCommand implements Command {
    @Override
    public void execute(Repository repository, String[] args) throws ImageNotFound {
        if (args.length < 1) {
            throw new ImageNotFound("Usage: remove <name>");
        }
        String name = args[0];
        boolean removed = repository.getImages().removeIf(img -> img.name().equalsIgnoreCase(name));
        if (!removed) {
            throw new ImageNotFound("Image not found: " + name);
        }
        System.out.println("Image removed: " + name);
    }
}

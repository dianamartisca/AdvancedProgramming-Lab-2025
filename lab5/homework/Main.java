package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Repository repository = new Repository();
        Map<String, Command> commands = new HashMap<>();
        commands.put("add", new AddCommand());
        commands.put("remove", new RemoveCommand());
        commands.put("update", new UpdateCommand());
        commands.put("save", new SaveCommand());
        commands.put("load", new LoadCommand());
        commands.put("report", new ReportCommand());

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter commands:");
        while (scanner.hasNextLine()) {
            String[] input = scanner.nextLine().split(" ", 2);
            String commandName = input[0];
            String[] commandArgs = input.length > 1 ? input[1].split(" ") : new String[0];
            if (commandName.equalsIgnoreCase("exit")) {
                System.out.println("Exiting program...");
                break;
            }
            try {
                if (commands.containsKey(commandName)) {
                    commands.get(commandName).execute(repository, commandArgs);
                } else {
                    throw new InvalidCommand("Unknown command: " + commandName);
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}

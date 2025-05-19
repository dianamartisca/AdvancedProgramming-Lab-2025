package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameClient {
    private final String serverAddress;
    private final int serverPort;

    public GameClient(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public void start() {
        try (
                Socket socket = new Socket(serverAddress, serverPort);
                BufferedReader keyboardInput = new BufferedReader(new InputStreamReader(System.in));
                BufferedReader serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter serverOutput = new PrintWriter(socket.getOutputStream(), true)
        ) {
            System.out.println("Connected to the server. Type your commands:");
            String command;

            while (true) {
                System.out.print("> ");
                command = keyboardInput.readLine();

                if ("exit".equalsIgnoreCase(command)) {
                    System.out.println("Exiting...");
                    break;
                }

                serverOutput.println(command);

                String response;
                while ((response = serverInput.readLine()) != null && !response.isEmpty()) {
                    System.out.println("Server response: " + response);
                    if (!command.toLowerCase().startsWith("move")) break;
                }

                if ("stop".equalsIgnoreCase(command)) {
                    System.out.println("Server stopped");
                    System.out.println("Exiting...");
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String serverAddress = "localhost";
        int serverPort = 12345;
        GameClient client = new GameClient(serverAddress, serverPort);
        client.start();
    }
}
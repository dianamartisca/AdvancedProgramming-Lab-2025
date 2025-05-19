package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    private final Socket clientSocket;
    private final GameServer server;
    private final GameManager gameManager;
    private HexGame currentGame = null;
    private HexPlayer currentPlayer = null;

    public ClientThread(Socket clientSocket, GameServer server, GameManager gameManager) {
        this.clientSocket = clientSocket;
        this.server = server;
        this.gameManager = gameManager;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            String request;
            while ((request = in.readLine()) != null) {
                String[] tokens = request.trim().split("\\s+");
                if (tokens.length == 0) continue;
                String command = tokens[0].toLowerCase();

                switch (command) {
                    case "create":
                        int size = tokens.length > 1 ? Integer.parseInt(tokens[1]) : 11;
                        int gameId = gameManager.createGame(size);
                        out.println("Game created with ID: " + gameId);
                        break;
                    case "join":
                        if (tokens.length < 4) {
                            out.println("Usage: join <gameId> <playerName> <color>");
                            break;
                        }
                        int joinGameId;
                        try {
                            joinGameId = Integer.parseInt(tokens[1]);
                        } catch (NumberFormatException e) {
                            out.println("Invalid game ID.");
                            break;
                        }
                        currentGame = gameManager.getGame(joinGameId);
                        if (currentGame == null) {
                            out.println("Game not found.");
                            break;
                        }
                        String playerName = tokens[2];
                        String color = tokens[3];
                        currentPlayer = new HexPlayer(playerName, color, 300_000);
                        if (currentGame.addPlayer(currentPlayer, out)) {
                            out.println("Joined game as " + color);
                        } else {
                            out.println("Game full.");
                        }
                        break;
                    case "move":
                        if (currentGame == null || currentPlayer == null) {
                            out.println("Join a game first.");
                            break;
                        }
                        if (tokens.length < 3) {
                            out.println("Usage: move <x> <y>");
                            break;
                        }
                        int x = Integer.parseInt(tokens[1]);
                        int y = Integer.parseInt(tokens[2]);
                        String result = currentGame.submitMove(currentPlayer.getName(), x, y);
                        out.println(result);
                        out.println(currentGame.getBoardString());
                        break;
                    case "exit":
                        out.println("Client disconnected");
                        return;
                    case "stop":
                        out.println("Server stopped");
                        server.stop();
                        return;
                    default:
                        out.println("Unknown command.");
                }
            }
        } catch (IOException e) {
            System.err.println("Error in client thread: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
                if (server.isRunning()) {
                    System.out.println("Client disconnected");
                }
            } catch (IOException e) {
                System.err.println("Error closing client socket: " + e.getMessage());
            }
        }
    }
}
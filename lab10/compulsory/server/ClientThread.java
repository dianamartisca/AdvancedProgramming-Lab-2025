package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    private final Socket clientSocket;
    private final GameServer server;

    public ClientThread(Socket clientSocket, GameServer server) {
        this.clientSocket = clientSocket;
        this.server = server;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            String request;
            while ((request = in.readLine()) != null) {
                System.out.println("Received request: " + request);

                if ("stop".equalsIgnoreCase(request)) {
                    out.println("Server stopped");
                    server.stop();
                    break;
                } else if ("exit".equalsIgnoreCase(request)) {
                    out.println("Client disconnected");
                    break;
                } else {
                    out.println("Server received the request: " + request);
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
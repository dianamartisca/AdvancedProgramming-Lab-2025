package org.example;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HexGame {
    private final int size;
    private final String[][] board;
    private final HexPlayer[] players = new HexPlayer[2];
    private int currentPlayerIdx = 0;
    private boolean started = false;
    private boolean finished = false;
    private String winner = null;
    private long lastMoveTimestamp;
    private final List<PrintWriter> playerOutputs = new ArrayList<>();

    public HexGame(int size) {
        this.size = size;
        this.board = new String[size][size];
        for (String[] row : board) Arrays.fill(row, ".");
    }

    public boolean addPlayer(HexPlayer player, PrintWriter out) {
        if (players[0] == null) {
            players[0] = player;
            playerOutputs.add(out);
            return true;
        } else if (players[1] == null) {
            players[1] = player;
            playerOutputs.add(out);
            started = true;
            lastMoveTimestamp = System.currentTimeMillis();
            return true;
        }
        return false;
    }

    public synchronized String submitMove(String playerName, int x, int y) {
        if (finished) return "Game already finished.";
        if (!started) return "Game not started yet.";
        HexPlayer player = players[currentPlayerIdx];
        if (!player.getName().equals(playerName)) return "Not your turn.";
        if (x < 0 || x >= size || y < 0 || y >= size) return "Invalid move.";
        if (!board[x][y].equals(".")) return "Cell occupied.";

        long now = System.currentTimeMillis();
        long elapsed = now - lastMoveTimestamp;
        player.deductTime(elapsed);
        if (player.getTimeLeftMillis() <= 0) {
            finished = true;
            winner = players[1 - currentPlayerIdx].getName();
            return "Time out! " + winner + " wins.";
        }
        lastMoveTimestamp = now;

        board[x][y] = player.getColor();
        if (checkWin(player.getColor())) {
            finished = true;
            winner = player.getName();
            String winMsg = "Move accepted. " + winner + " wins!";
            for (PrintWriter out : playerOutputs) {
                out.println(winMsg);
                out.println(getBoardString());
            }
            return winMsg;
        }
        currentPlayerIdx = 1 - currentPlayerIdx;
        return "Move accepted.";
    }

    private boolean checkWin(String color) {
        boolean[][] visited = new boolean[size][size];
        if (color.equals("r")) {
            for (int col = 0; col < size; col++) {
                if (board[0][col].equals("r") && dfs(0, col, "r", visited)) {
                    return true;
                }
            }
        } else if (color.equals("b")) {
            for (int row = 0; row < size; row++) {
                if (board[row][0].equals("b") && dfs(row, 0, "b", visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int x, int y, String color, boolean[][] visited) {
        if (visited[x][y]) return false;
        visited[x][y] = true;

        if (color.equals("r") && x == size - 1) return true;
        if (color.equals("b") && y == size - 1) return true;

        int[] dx = {-1, -1, 0, 0, 1, 1};
        int[] dy = {0, 1, -1, 1, -1, 0};

        for (int dir = 0; dir < 6; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if (nx >= 0 && nx < size && ny >= 0 && ny < size) {
                if (board[nx][ny].equals(color) && !visited[nx][ny]) {
                    if (dfs(nx, ny, color, visited)) return true;
                }
            }
        }
        return false;
    }

    public String getBoardString() {
        StringBuilder sb = new StringBuilder();
        for (String[] row : board) {
            for (String cell : row) sb.append(cell).append(" ");
            sb.append("\n");
        }
        return sb.toString();
    }
}
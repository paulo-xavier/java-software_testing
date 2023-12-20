package com.example;

import javax.swing.*;

public class App {
    private int boardWidth = 600;
    private int boardHeight = boardWidth;

    public int getBoardWidth() {
        return boardWidth;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public static void main(String[] args) throws Exception {
        App app = new App();

        JFrame frame = new JFrame("Snake");
        frame.setVisible(true);
        frame.setSize(app.getBoardWidth(), app.getBoardHeight());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SnakeGame snakeGame = new SnakeGame(app.getBoardWidth(), app.getBoardHeight());
        frame.add(snakeGame);
        frame.pack();
        snakeGame.requestFocus();
    }
}

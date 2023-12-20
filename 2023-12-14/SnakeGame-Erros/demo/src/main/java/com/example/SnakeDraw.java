package com.example;

import java.awt.*;
import javax.swing.JPanel;

/* Esta classe é responsável por desenhar o jogo na tela. */
public class SnakeDraw extends JPanel {
    private SnakeGame snakeGame;

    // Construtor que recebe uma instância de SnakeGame
    SnakeDraw(SnakeGame snakeGame) {
        this.snakeGame = snakeGame;
    }

    // Sobrescreve o método paintComponent da classe JPanel
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Chama o método draw para desenhar os componentes do jogo
        draw(g);
    }

    // Método responsável por desenhar os componentes do jogo
    public void draw(Graphics g) {
        // Desenha a grade do tabuleiro
        for (int i = 0; i < snakeGame.boardWidth / snakeGame.tileSize; i++) {
            g.drawLine(i * snakeGame.tileSize, 0, i * snakeGame.tileSize, snakeGame.boardHeight);
            g.drawLine(0, i * snakeGame.tileSize, snakeGame.boardWidth, i * snakeGame.tileSize);
        }

        // Desenha a comida em vermelho
        g.setColor(Color.red);
        g.fill3DRect(snakeGame.food.x * snakeGame.tileSize, snakeGame.food.y * snakeGame.tileSize, snakeGame.tileSize, snakeGame.tileSize, true);

        // Desenha a cabeça da cobra em verde
        g.setColor(Color.green);
        g.fill3DRect(snakeGame.snakeHead.x * snakeGame.tileSize, snakeGame.snakeHead.y * snakeGame.tileSize, snakeGame.tileSize, snakeGame.tileSize, true);

        // Desenha o corpo da cobra
        for (int i = 0; i < snakeGame.snakeBody.size(); i++) {
            SnakeGame.Tile snakePart = snakeGame.snakeBody.get(i);
            g.fill3DRect(snakePart.x * snakeGame.tileSize, snakePart.y * snakeGame.tileSize, snakeGame.tileSize, snakeGame.tileSize, true);
        }

        // Configura a fonte para exibir a pontuação
        g.setFont(new Font("Arial", Font.PLAIN, 16));

        // Verifica se o jogo terminou
        if (snakeGame.gameOver) {
            g.setColor(Color.red);
            // Exibe a mensagem de "Game Over" e a pontuação
            g.drawString("Game Over: " + String.valueOf(snakeGame.snakeBody.size()), snakeGame.tileSize - 16, snakeGame.tileSize);
        } else {
            // Se o jogo não terminou, exibe a pontuação
            g.drawString("Score: "+ String.valueOf(snakeGame.snakeBody.size()), snakeGame.tileSize - 16, snakeGame.tileSize);
        }
    }
}

package com.example;

import java.awt.event.KeyEvent;

/*
  Esta classe é responsável pelo movimento da cobra e a detecção de colisões
  */
public class MovementAndCollision {
    private SnakeGame snakeGame;

    MovementAndCollision(SnakeGame snakeGame) {
        this.snakeGame = snakeGame;
    }

    /* Método responsável por mover a cobra no jogo Snake e verificar se o jogo terminou. */
    public void move() {
        // Verifica colisão com a comida
        if (snakeGame.collision(snakeGame.snakeHead, snakeGame.food)) {
            // Come a comida
            snakeGame.snakeBody.add(snakeGame.new Tile(snakeGame.food.x, snakeGame.food.y));
            // Reposiciona a comida
            snakeGame.placeFood();
        }
    
        // Move o corpo da cobra
        for (int i = snakeGame.snakeBody.size() - 1; i >= 0; i--) {
            // Recupera o segmento atual do corpo
            SnakeGame.Tile snakePart = snakeGame.snakeBody.get(i);
    
            // Se for o primeiro segmento (cabeça)
            if (i == 0) {
                // Atualiza a posição X e Y com a posição da cabeça
                snakePart.x = snakeGame.snakeHead.x;
                snakePart.y = snakeGame.snakeHead.y;
            }
            // Se não for o primeiro segmento
            else {
                // Recupera o segmento anterior do corpo
                SnakeGame.Tile prevSnakePart = snakeGame.snakeBody.get(i - 1);
    
                // Atualiza a posição X e Y com a posição do segmento anterior
                snakePart.x = prevSnakePart.x;
                snakePart.y = prevSnakePart.y;
            }
        }
    
        // Move a cabeça da cobra
        snakeGame.snakeHead.x += snakeGame.directionX;
        snakeGame.snakeHead.y += snakeGame.directionY;
    
        // Verifica colisão com o corpo da cobra
        for (int i = 0; i < snakeGame.snakeBody.size(); i++) {
            // Recupera o segmento atual do corpo
            SnakeGame.Tile snakePart = snakeGame.snakeBody.get(i);
    
            // Verifica colisão com a cabeça
            if (snakeGame.collision(snakeGame.snakeHead, snakePart)) {
                // O jogo termina se colidir
                snakeGame.gameOver = true;
            }
        }
    
        // Verifica colisão com as paredes
        if (snakeGame.snakeHead.x * snakeGame.tileSize < 0 || snakeGame.snakeHead.x * snakeGame.tileSize > snakeGame.boardWidth ||
            snakeGame.snakeHead.y * snakeGame.tileSize < 0 || snakeGame.snakeHead.y * snakeGame.tileSize > snakeGame.boardHeight) {
            // O jogo termina se colidir
            snakeGame.gameOver = true;
        }
    }

    public boolean collision(SnakeGame.Tile tile1, SnakeGame.Tile tile2) {
        // Verifica se as coordenadas X e Y dos dois objetos Tile são iguais
        return tile1.x == tile2.x && tile1.y == tile2.y;
    }


    /* Método chamado quando uma tecla é pressionada */
    public void keyPressed(KeyEvent tecla) {
    // Verifica o código da tecla pressionada
    switch (tecla.getKeyCode()) {
        case KeyEvent.VK_UP:
            // Se a tecla pressionada for VK_UP (seta para cima)
            // e a velocidade vertical atual não for -1 (para baixo),
            // a velocidade horizontal é definida como 0 e a velocidade vertical é definida como -1 (para cima).
            if (snakeGame.directionY != 1) {
                snakeGame.directionX = 0;
                snakeGame.directionY = -1;
            }
            break;
        case KeyEvent.VK_DOWN:
            // Se a tecla pressionada for VK_DOWN (seta para baixo)
            // e a velocidade vertical atual não for 1 (para cima),
            // a velocidade horizontal é definida como 0 e a velocidade vertical é definida como 1 (para baixo).
            if (snakeGame.directionY != -1) {
                snakeGame.directionX = 0;
                snakeGame.directionY = 1;
            }
            break;
        case KeyEvent.VK_LEFT:
            // Se a tecla pressionada for VK_LEFT (seta para esquerda)
            // e a velocidade horizontal atual não for 1 (para direita),
            // a velocidade horizontal é definida como -1 (para esquerda) e a velocidade vertical é definida como 0.
            if (snakeGame.directionX != 1) {
                snakeGame.directionX = -1;
                snakeGame.directionY = 0;
            }
            break;
        case KeyEvent.VK_RIGHT:
            // Se a tecla pressionada for VK_RIGHT (seta para direita)
            // e a velocidade horizontal atual não for -1 (para esquerda),
            // a velocidade horizontal é definida como 1 (para direita) e a velocidade vertical é definida como 0.
            if (snakeGame.directionX != -1) {
                snakeGame.directionX = 1;
                snakeGame.directionY = 0;
            }
            break;
    }
}
}

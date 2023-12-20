package com.example;
import static org.junit.Assert.*; 
import org.junit.Before; 
import org.junit.Test; 
import java.awt.event.*; 



public class SnakeGameTest {
    
    SnakeGame snakeGame;   
    App board; 
    
    @Before 
    public void setUp() {
        board = new App(); 
        snakeGame = new SnakeGame(board.getBoardWidth(), board.getBoardHeight());
    
    }


    @Test 
    public void testSnakeHead() {
        // A cabeça da cobra deve iniciar na posição definida (por exemplo, x=0, y=0).
        int snakeHeadX = snakeGame.snakeHead.x;  
        int snakeHeadY = snakeGame.snakeHead.y; 

        assertEquals(0, snakeHeadX); 
        assertEquals(0, snakeHeadY);
    }


    @Test 
    public void testFoodPosition() {
        // A comida deve iniciar em uma posição aleatória, diferente da posição inicial da cabeça da cobra.
        int foodPositionX = snakeGame.food.x; 
        int foodPositionY = snakeGame.food.y; 

        assertNotEquals(0, foodPositionX);
        assertNotEquals(0, foodPositionY);

    }

    
    @Test 
    public void testScoreStartsInZero () {
        // O score deve começar em zero.
        int initialScore = snakeGame.snakeBody.size(); 

        assertEquals(0, initialScore); 
    }


    @Test 
    public void boardStartsCorrectDimension() {
        // O painel (board) deve ter as dimensões corretas (600x600)

        int boardHeight = snakeGame.boardHeight; 
        int boardWidth = snakeGame.boardWidth; 

        assertEquals(600, boardHeight); 
        assertEquals(600, boardWidth); 

    }

    
    @Test 
    public void testBoardTiles() {
        // Os tiles no tabuleiro devem ter o tamanho correto (25x25)

        int tileSize = snakeGame.tileSize; 

        assertEquals(25, tileSize); 

    }


    @Test 
    public void testGameInterval() {
        // O jogo deve ser atualizado em intervalos regulares, mantendo uma jogabilidade suave.

        int gameInterval = snakeGame.gameLoop.getDelay(); 

        assertEquals(100, gameInterval); 

    }

    
    @Test 
    public void testGameOver () {
        // A condição de game over deve iniciar como false.

        boolean gameOver = snakeGame.gameOver; 

        assertFalse(gameOver); 
    }

    @Test 
    public void testRightMoving () {
        // A cabeça da cobra deve mover-se para a direita em relação à sua posição inicial.

        snakeGame.keyPressed(new KeyEvent(snakeGame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, 'D' ));
        
        assertEquals(+1, snakeGame.directionX); 
        assertEquals(0, snakeGame.directionY); 
    }

    @Test 
    public void testLeftMoving () {
        // A cabeça da cobra deve mover-se para a esquerda em relação à sua posição inicial.

        snakeGame.directionX = 0; 

        snakeGame.keyPressed(new KeyEvent(snakeGame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, 'A'));
        

        assertEquals(-1, snakeGame.directionX); 
        assertEquals(0, snakeGame.directionY); 
    }

    @Test 
    public void testUpMoving () {
        // A cabeça da cobra deve mover-se para cima em relação à sua posição inicial.

        snakeGame.keyPressed(new KeyEvent(snakeGame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_UP, 'W'));
        
        assertEquals(-1, snakeGame.directionY); 
        assertEquals(0, snakeGame.directionX); 

    }

    @Test 
    public void testDownMoving () {
        // A cabeça da cobra deve mover-se para baixo em relação à sua posição inicial.

        snakeGame.keyPressed(new KeyEvent(snakeGame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_DOWN, 'S'));

        assertEquals(1, snakeGame.directionY);
        assertEquals(0, snakeGame.directionX); 
    
    }


    @Test 
    public void testSnakeCollision () {
        // A colisão deve ser detectada quando a cobra colide com ela mesma

        snakeGame.snakeBody.add(snakeGame.new Tile(5, 5)); 
        snakeGame.snakeBody.add(snakeGame.new Tile(5, 6));
        snakeGame.snakeBody.add(snakeGame.new Tile(5, 7));
        
        snakeGame.snakeHead = snakeGame.new Tile(5, 5);

        assertTrue(snakeGame.movementAndCollision.collision(snakeGame.snakeHead, snakeGame.snakeBody.get(0))); 
        
    }

    @Test
    public void testScoreIncreasingAfterEatFood() {
        // O score deve ser incrementado corretamente quando a cobra come a comida.


        snakeGame.snakeHead = snakeGame.new Tile(5, 5); 
        snakeGame.food = snakeGame.new Tile(5, 5); 

        snakeGame.snakeBody.add(snakeGame.new Tile(5, 4)); 
        snakeGame.snakeBody.add(snakeGame.new Tile(5, 3));
        
        snakeGame.move(); 

        assertEquals(3, snakeGame.snakeBody.size()); // 3 , because the snake has already eaten 3 fragments 
        
    }








}


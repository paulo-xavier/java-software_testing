https://docs.google.com/spreadsheets/d/16jTtRoQYQP_931y2cQ2QKp37x8LzqNd3ziPAe_chcm8/edit?usp=sharing



T001 

- Objetivo:
    - Testar se a cabeça da cobra inicia na posição correta

- Resultados 
    - Resultados esperados: A cabeça da cobra deve iniciar na posição definida (por exemplo, x=0, y=0).
    
    - Resultados obtidos: **java.lang.AssertionError: expected:[0] but was:[52]
 at com.example.SnakeGameTest.testSnakeHead(SnakeGameTest.java:26)**

 - Observações: O causador do erro foi que a cabeça da cobra está posicionada no incorretamente. Na coordenada X ela está posicionada no 52 e na coordenada Y ele está posicionada no 9. O erro está localizado na classe SnakeGame.java na linha 87. `snakeHead = new Tile(52, 9);`. 





 T001.2

 - Objetivo: 
    - Testar se a comida inicia em uma posição aleatória
    
- Resultados: 
    - Resultados esperados: A comida deve iniciar em uma posição aleatória, diferente da posição inicial da cabeça da cobra.

    - Resultados obtidos: **java.lang.AssertionError: Values should be different. Actual: 10
 at com.example.SnakeGameTest.testFoodPosition(SnakeGameTest.java:41)**

 - Observações: O erro encontra-se na classe SnakeGame.java na linha 152. O código em questão, `int posX = 10;` deveria realizar uma operção para deixar aleatório a posição inicial X  da comida, porém apenas atribuiu o valor 10 a posX. O mesmo erro ocorre na linha 161 para a posição inicial Y da comida, localizado na linha 161. `int posY = 10;`. 


    

T001.3

- Objetivo: 
    - Testar se o score inicia zerado. 

- Resultados: 
    - Resultados esperados: O score deve começar em zero. 
    
    - Resultados obtidos: O teste não apresentou erros e o score começou em zero. 




T001.4

- Objetivo: 
    - Testar se o painel inicia no tamanho correto

- Resultados: 
    - Resultados esperados: O painel (board) deve ter as dimensões corretas (600x600)

    - Resultados obtidos: **java.lang.AssertionError: expected:[600] but was:[400]
    at com.example.SnakeGameTest.boardStartsCorrectDimension(SnakeGameTest.java:62)**

- Observações: O erro encontra-se na classe App.java na linha 6. O código `private int boardWidth = 400;`, está setando a largura em 400 porém nesse caso o esperado era uma largura de 600. Por conta desse equívoco, na linha 7, o atributo de altura também foi afetado de maneira errada:  `private int boardHeight = boardWidth;`. Gerando assim, uma largura e altura de 600. 





T001.5

- Objetivo: 
    - Testar se os tiles tem o tamanho correto

- Resultados: 
    - Resultados esperados: Os tiles no tabuleiro devem ter o tamanho correto (25x25)

    - Resultados obtidos:java.lang.AssertionError: expected:[25] but was:[50]
    at com.example.SnakeGameTest.testBoardTiles(SnakeGameTest.java:74)

- Observações: O erro encontra-se na linha 29 da classe SnakeGame.java. No trecho `int tileSize = 50;` , o valor que deveria ser atribuído era 25 e não 50. 




T001.6 

- Objetivo: 
    - Testar se o jogo atualiza no intervalo ideal

- Resultados: 
    - Resultados esperados: O jogo deve ser atualizado em intervalos regulares, mantendo uma jogabilidade suave.

    - Resultados obtidos: java.lang.AssertionError: expected:[100] but was:[2000]
 at com.example.SnakeGameTest.testGameInterval(SnakeGameTest.java:85)

- Observações: 
    - O erro encontra-se na linha 139 da classe SnakeGame.java. A linha `gameLoop = new Timer(2000, this);` deveia conter uma valor de 100 no delay ao invés de 2000. 







T002

- Objetivo: 
    - Testar se a cobra se move para a direita, um tile por vez

Resultados: 
    - Resultados esperados: A cabeça da cobra deve mover-se para a direita em relação à sua posição inicial.

    - Resultados obtidos: O resultado esperado foi alcançado. O teste não apresentou eventuais erros. 

- Observações:






T002.1

- Objetivo:
    - Testar se a cobra se move para a esquerda, um tile por vez

Resultados: 
    - Resultados esperados: A cabeça da cobra deve mover-se para a esquerda em relação à sua posição inicial.

    - Resultados obtidos: java.lang.AssertionError: expected:[-1] but was:[0]
 at com.example.SnakeGameTest.testLeftMoving(SnakeGameTest.java:110)

 Observações: O erro encontra-se na classe MovementAndCollision.java, na linha 106. A linha 106 `snakeGame.directionX = 1;` deveria conter a directionX  = -1 e não 1. O mesmo ocorre na linha 107 `snakeGame.directionY = 1;` onde o esperado era directionY = 0. 





T002.2

- Objetivo: 
    - Testar se a cobra se move para cima, um tile por vez

Resultados: 
    - Resultados esperados: A cabeça da cobra deve mover-se para cima em relação à sua posição inicial. 

    - Resultados obtidos: java.lang.AssertionError: expected:[0] but was:[1]
 at com.example.SnakeGameTest.testUpMoving(SnakeGameTest.java:122)

Observações: O erro econtra-se na classe MovementAndCollision, na linha 88. A linha 88 `snakeGame.directionX = 1;` deveria conter o directionX = 0 e não 1. 



T002.3 

- Objetivo: 
    - Testar se a cobra se move para baixo, um tile por vez

- Resultados: 
    - Resultados esperados: A cabeça da cobra deve mover-se para baixo em relação à sua posição inicial.

    - Resultados obtidos: java.lang.AssertionError: expected:[1] but was:[0]
 at com.example.SnakeGameTest.testDownMoving(SnakeGameTest.java:131)

- Observações: O erro ocorreu na classe MovementAndCollision, na linha 98. A linha `snakeGame.directionY = -1;` deveria conter um directionY = 1 e não -1. Também ocorrreu um erro na linha 97 `snakeGame.directionX = 1;` onde o esperado para o directionX era 0 e não 1. 



T003 

- Objetivo: 
    - Testar se a cabeça da cobra colide com ela mesma

- Resultados: 
    - Resultados esperados: A colisão deve ser detectada quando a cobra colide com ela mesma

    - Resultados obtidos: java.lang.AssertionError
 at com.example.SnakeGameTest.testSnakeCollision(SnakeGameTest.java:148)

- Observações: O erro está na classe MovementAndCollision, na linha 74. Na linha `return tile1.x != tile2.x && tile1.y != tile2.y ` o correto seria `return tile1.x == tile2.x && tile1.y == tile2.y `. 




T004 

- Objetivo: 
    - Testar se game over inicia como false

- Resultados: 
    - Resultados espeardos: A condição de game over deve iniciar como false 

    - Resultados obtidos: java.lang.AssertionError
 at com.example.SnakeGameTest.testGameOver(SnakeGameTest.java:157)

- Observações: O erro está na linha 40 da classe SnakeGame.java. A linha `boolean gameOver = true;` deveria iniciar o gameOver como false. 



T004.1 

- Objetivo: 
    - Testar se ocorre game over quando detecta colisão com ela mesma 

- Resultados: 
    - Resultados esperados: O game over deve ocorrer quando a cobra colide com ela mesma

    - Resultados oabtidos: 





T005 

- Objetivo: 
    - Testar se o score atualiza corretamente ao comer a comida

- Resultados: 
    - Resultados esperados: O score deve ser incrementado corretamente quando a cobra come a comida.

    - Resultados obtidos: java.lang.AssertionError: expected:[3] but was:[2]
 at com.example.SnakeGameTest.testScoreIncreasingAfterEatFood(SnakeGameTest.java:193)

- Obeservações: O erro está na linha 20 da classe MovementAndCollision. A linha 20 não está implementando nenhum método que aumente o corpo da cobra e o score após ela comer a comida. 
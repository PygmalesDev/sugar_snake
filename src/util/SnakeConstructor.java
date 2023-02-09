package util;

import objects.Snake;
import objects.SnakeBody;
import objects.SnakeHead;
import objects.SnakeTail;
import processing.core.PApplet;

public class SnakeConstructor {
    private Snake[] snakeMain;
    private Snake[] snakeTemp;
    private final PApplet sketch;
    public int snakeColor;


    public SnakeConstructor(PApplet sketch, Snake[] snakeMain) {
        this.snakeMain = snakeMain;
        this.sketch = sketch;
        createSnakeColor();

    }

    public Snake[] constructSnake(float snakeStartX, float snakeStartY, int snakeLength) {
        this.snakeMain = new Snake[snakeLength];
        for(int i = 0; i < this.snakeMain.length; i++) {
            if (i == 0 ) this.snakeMain[0] = new SnakeHead(this.sketch, snakeStartX, snakeStartY, this.snakeColor) {
            };
            else if (i == this.snakeMain.length-1) this.snakeMain[i] = new SnakeTail(this.sketch, snakeStartX, snakeStartY - 32*i, this.snakeColor);
            else this.snakeMain[i] = new SnakeBody(this.sketch, snakeStartX, snakeStartY - 32*i, this.snakeColor);
        }
        return snakeMain;
    }

    public Snake[] addSnakePart(int snakeLength) {
        this.snakeTemp = new Snake[snakeLength];
        for (int i = 0; i < this.snakeMain.length; i++) {
            this.snakeTemp[i] = this.snakeMain[i];
        }
        this.snakeTemp[snakeLength-1] = this.snakeTemp[snakeLength-2];
        float newPartX = snakeTemp[snakeLength-3].coords.getX();
        float newPartY = snakeTemp[snakeLength-3].coords.getY()-36;
        this.snakeTemp[snakeLength-2] = new SnakeBody(this.sketch, newPartX, newPartY, this.snakeColor);
        snakeMain = new Snake[snakeLength];
        for (int i = 0; i < this.snakeMain.length; i++) {
            this.snakeMain[i] = this.snakeTemp[i];
        }
        return snakeMain;
    }
    public Snake[] removeSnakePart(int snakeLength) {
        snakeTemp = new Snake[snakeLength];
        for (int i = 0; i < snakeLength; i++) {
            this.snakeTemp[i] = this.snakeMain[i];
        }
        float newPartX = snakeTemp[snakeLength-2].coords.getX();
        float newPartY = snakeTemp[snakeLength-2].coords.getY();
        this.snakeTemp[snakeLength-1] = new SnakeTail(this.sketch, newPartX, newPartY, this.snakeColor);
        this.snakeMain = new Snake[snakeLength];
        for (int i = 0; i < this.snakeMain.length; i++) {
            this.snakeMain[i] = this.snakeTemp[i];
        }
        return snakeMain;
    }

    public void createSnakeColor() {
        this.snakeColor = (int) this.sketch.random(0, 3);
    }
}

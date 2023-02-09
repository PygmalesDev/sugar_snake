package objects;

import processing.core.PApplet;

public class SnakeBody extends Snake {

    public SnakeBody(PApplet sketch, float x, float y, int color) {
        super(sketch, x, y, color);
        switch (color) {
            case 0 -> this.sprite = this.sketch.loadImage("assets/SBodyRed.png");
            case 1 -> this.sprite = this.sketch.loadImage("assets/SBodyGreen.png");
            case 2 -> this.sprite = this.sketch.loadImage("assets/SBodyBlue.png");

        }
    }

    public void rotate(Snake snakePart) {
        if (snakePart.vectorNorm <= 36f) {
            switch (snakePart.CurrentDirection) {
                case RIGHT -> {
                    this.CurrentDirection = this.Direction.RIGHT;
                    switch (this.color) {
                        case 0 -> this.sprite = this.sketch.loadImage("assets/leftRight_SBodyRed.png");
                        case 1 -> this.sprite = this.sketch.loadImage("assets/leftRight_SBodyGreen.png");
                        case 2 -> this.sprite = this.sketch.loadImage("assets/leftRight_SBodyBlue.png");
                    }
                }
                case LEFT -> {
                    this.CurrentDirection = this.Direction.LEFT;
                    switch (this.color) {
                        case 0 -> this.sprite = this.sketch.loadImage("assets/leftRight_SBodyRed.png");
                        case 1 -> this.sprite = this.sketch.loadImage("assets/leftRight_SBodyGreen.png");
                        case 2 -> this.sprite = this.sketch.loadImage("assets/leftRight_SBodyBlue.png");
                    }
                }
                case UP -> {
                    this.CurrentDirection = this.Direction.UP;
                    switch (this.color) {
                        case 0 -> this.sprite = this.sketch.loadImage("assets/upDown_SBodyRed.png");
                        case 1 -> this.sprite = this.sketch.loadImage("assets/upDown_SBodyGreen.png");
                        case 2 -> this.sprite = this.sketch.loadImage("assets/upDown_SBodyBlue.png");
                    }
                }
                case DOWN -> {
                    this.CurrentDirection = this.Direction.DOWN;
                    switch (this.color) {
                        case 0 -> this.sprite = this.sketch.loadImage("assets/upDown_SBodyRed.png");
                        case 1 -> this.sprite = this.sketch.loadImage("assets/upDown_SBodyGreen.png");
                        case 2 -> this.sprite = this.sketch.loadImage("assets/upDown_SBodyBlue.png");
                    }
                }
            }
        }
    }

    public void setDirection(util.Direction direction) {}

    public void move() {}
}

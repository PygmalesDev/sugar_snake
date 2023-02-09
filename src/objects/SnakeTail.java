package objects;

import processing.core.PApplet;

public class SnakeTail extends Snake{
    public SnakeTail(PApplet sketch, float x, float y, int color) {
        super(sketch, x, y, color);
        switch (color) {
            case 0 -> this.sprite = this.sketch.loadImage("assets/STailRed.png");
            case 1 -> this.sprite = this.sketch.loadImage("assets/STailGreen.png");
            case 2 -> this.sprite = this.sketch.loadImage("assets/STailBlue.png");
        }
    }

    public void rotate(Snake snakePart) {
        if (snakePart.vectorNorm <= 30.6f) {
        switch (snakePart.CurrentDirection) {
            case RIGHT -> {
                this.CurrentDirection = this.Direction.RIGHT;
                switch (this.color) {
                    case 0 -> this.sprite = this.sketch.loadImage("assets/left_STailRed.png");
                    case 1 -> this.sprite = this.sketch.loadImage("assets/left_STailGreen.png");
                    case 2 -> this.sprite = this.sketch.loadImage("assets/left_STailBlue.png");
                }
            }
            case LEFT -> {
                this.CurrentDirection = this.Direction.LEFT;
                switch (this.color) {
                    case 0 -> this.sprite = this.sketch.loadImage("assets/right_STailRed.png");
                    case 1 -> this.sprite = this.sketch.loadImage("assets/right_STailGreen.png");
                    case 2 -> this.sprite = this.sketch.loadImage("assets/right_STailBlue.png");
                }
            }
            case UP -> {
                this.CurrentDirection = this.Direction.DOWN;
                switch (this.color) {
                    case 0 -> this.sprite = this.sketch.loadImage("assets/up_STailRed.png");
                    case 1 -> this.sprite = this.sketch.loadImage("assets/up_STailGreen.png");
                    case 2 -> this.sprite = this.sketch.loadImage("assets/up_STailBlue.png");
                }
            }
            case DOWN -> {
                this.CurrentDirection = this.Direction.UP;
                switch (this.color) {
                    case 0 -> this.sprite = this.sketch.loadImage("assets/down_STailRed.png");
                    case 1 -> this.sprite = this.sketch.loadImage("assets/down_STailGreen.png");
                    case 2 -> this.sprite = this.sketch.loadImage("assets/down_STailBlue.png");
                }
            }
        }
        }
    }

    public void setDirection(util.Direction direction) {}

    public void move() {}
}

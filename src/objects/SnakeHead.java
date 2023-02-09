package objects;

import util.Direction;
import processing.core.PApplet;

public class SnakeHead extends Snake {
    public SnakeHead(PApplet sketch, float x, float y, int color) {
        super(sketch, x, y, color);
        this.color = color;
        this.CurrentDirection = Direction.DOWN;
        switch (color) {
            case 0 -> this.sprite = this.sketch.loadImage("assets/down_SHeadRed.png");
            case 1 -> this.sprite = this.sketch.loadImage("assets/down_SHeadGreen.png");
            case 2 -> this.sprite = this.sketch.loadImage("assets/down_SHeadBlue.png");
        }
    }

    public void move() {
        if (CurrentDirection != null) {
            switch (CurrentDirection) {
                case RIGHT -> this.coords.setX(this.coords.getX() + this.speedX);
                case LEFT -> this.coords.setX(this.coords.getX() - this.speedX);
                case UP -> this.coords.setY(this.coords.getY() - this.speedY);
                case DOWN -> this.coords.setY(this.coords.getY() + this.speedY);
            }
        }
    }

    public void setDirection(Direction direction) {
        switch (direction) {
            case RIGHT -> { if (this.CurrentDirection != this.Direction.LEFT) this.CurrentDirection = this.Direction.RIGHT; }
            case LEFT -> { if (this.CurrentDirection != this.Direction.RIGHT) this.CurrentDirection = this.Direction.LEFT; }
            case UP -> { if (this.CurrentDirection != this.Direction.DOWN) this.CurrentDirection = this.Direction.UP; }
            case DOWN -> { if (this.CurrentDirection != this.Direction.UP) this.CurrentDirection = this.Direction.DOWN; }
        }
    }

    public void rotate(Snake snakePart) {
        switch (snakePart.CurrentDirection) {
            case RIGHT -> {
                switch (this.color) {
                    case 0 -> this.sprite = this.sketch.loadImage("assets/right_SHeadRed.png");
                    case 1 -> this.sprite = this.sketch.loadImage("assets/right_SHeadGreen.png");
                    case 2 -> this.sprite = this.sketch.loadImage("assets/right_SHeadBlue.png");
                }
            }
            case LEFT -> {
                switch (this.color) {
                    case 0 -> this.sprite = this.sketch.loadImage("assets/left_SHeadRed.png");
                    case 1 -> this.sprite = this.sketch.loadImage("assets/left_SHeadGreen.png");
                    case 2 -> this.sprite = this.sketch.loadImage("assets/left_SHeadBlue.png");
                }
            }
            case UP -> {
                switch (this.color) {
                    case 0 -> this.sprite = this.sketch.loadImage("assets/up_SHeadRed.png");
                    case 1 -> this.sprite = this.sketch.loadImage("assets/up_SHeadGreen.png");
                    case 2 -> this.sprite = this.sketch.loadImage("assets/up_SHeadBlue.png");
                }
            }
            case DOWN -> {
                switch (this.color) {
                    case 0 -> this.sprite = this.sketch.loadImage("assets/down_SHeadRed.png");
                    case 1 -> this.sprite = this.sketch.loadImage("assets/down_SHeadGreen.png");
                    case 2 -> this.sprite = this.sketch.loadImage("assets/down_SHeadBlue.png");
                }
            }
        }
    }

}

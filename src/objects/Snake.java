package objects;

import processing.core.PApplet;
import util.Direction;

public abstract class Snake extends Object{
    public int color;
    public float speedX = 2f;
    public float speedY = 2f;
    private float z;
    public float zDifference;
    public boolean isEatable;
    public boolean isFlying;
    public float vectorX;
    public float vectorY;
    public float vectorNorm;

    public Direction Direction;
    public Direction CurrentDirection;

    public Snake(PApplet sketch, float x, float y, int color) {
        super(sketch, x, y);
        this.color = color;
        this.isFlying = false;
        this.isEatable = true;
        this.CurrentDirection = this.Direction.DOWN;
        switch (color) {
            case 0 -> this.sprite = this.sketch.loadImage("assets/down_SHeadRed.png");
            case 1 -> this.sprite = this.sketch.loadImage("assets/down_SHeadGreen.png");
            case 2 -> this.sprite = this.sketch.loadImage("assets/down_SHeadBlue.png");
        }
    }

    public void follow(Snake snakePart) {
        computeNormalizedVector(snakePart);
        if (this.vectorNorm >= 28) {
            this.coords.setX(this.coords.getX() + this.vectorX * this.speedX);
            this.coords.setY(this.coords.getY() + this.vectorY * this.speedY);
        }
    }

    public boolean isColliding(Object object) {
        if (this.coords.getX() - 16 < object.coords.getX() && object.coords.getX() < this.coords.getX() + 16) {
            if (this.coords.getY() - 16 < object.coords.getY() && object.coords.getY() < this.coords.getY() + 16) {
                return true;
            }
        }
        return false;
    }
    public void fly() {
            if (this.isFlying) {
                this.coords.setY(this.coords.getY() - 0.5f);
                z += 0.5f;
            } else if (z > 0){
                this.coords.setY(this.coords.getY() + 0.5f);
                z -= 0.5f;
            }
            if (this.z == (45 - this.zDifference)) {
                this.isFlying = false;
            } else if (this.z == 0) {
                this.zDifference = 0;
                this.isEatable = true;
            }
    }

    public void drawShadow() {
        this.sketch.fill(0, 0, 0, 100);
        this.sketch.ellipse(this.coords.getX() + 16, this.coords.getY() + 22 + this.z, 30, 32);
    }

    public boolean leftScreen() {
        if (this.coords.getX() < 45 || 685 < this.coords.getX()) return true;
        if (this.coords.getY() < 60 || 720 < this.coords.getY()) return true;
        return false;
    }

    abstract public void rotate(Snake snakePart);
    abstract public void setDirection(Direction direction);
    abstract public void move();


    public void computeNormalizedVector(Snake snakePart) {
        vectorX = snakePart.coords.getX() - this.coords.getX();
        vectorY = snakePart.coords.getY() - this.coords.getY();
        vectorNorm = PApplet.pow(vectorX*vectorX + vectorY*vectorY, 0.5f);
        vectorX = vectorX/vectorNorm;
        vectorY = vectorY/vectorNorm;
    }
}

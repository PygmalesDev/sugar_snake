package objects;

import processing.core.PApplet;
import util.FruitType;

public class Fruit extends Object {
    public FruitType fruitType;
    private final float fallY;
    public boolean isCollectable;

    public Fruit(PApplet sketch, float x, float y) {
        super(sketch, x, y);
        this.fallY = this.coords.getY() + 720;
        this.isCollectable = false;

        switch ((int) sketch.random(0, 8)) {
            case 0 -> {
                this.sprite = this.sketch.loadImage("assets/apple.png");
                this.fruitType = FruitType.APPLE;
            }
            case 1 -> {
                this.sprite = this.sketch.loadImage("assets/melon.png");
                this.fruitType = FruitType.MELON;
            }
            case 2 -> {
                this.sprite = this.sketch.loadImage("assets/mango.png");
                this.fruitType = FruitType.MANGO;
            }
            case 3 -> {
                this.sprite = this.sketch.loadImage("assets/plum.png");
                this.fruitType = FruitType.PLUM;
            }
            case 4 -> {
                this.sprite = this.sketch.loadImage("assets/starFruit.png");
                this.fruitType = FruitType.STARFRUIT;
            }
            case 5 -> {
                this.sprite = this.sketch.loadImage("assets/banana.png");
                this.fruitType = FruitType.BANANA;
            }
            case 6 -> {
                this.sprite = this.sketch.loadImage("assets/coconut.png");
                this.fruitType = FruitType.COCONUT;
            }
            case 7 -> {
                this.sprite = this.sketch.loadImage("assets/kiwi.png");
                this.fruitType = FruitType.KIWI;
            }
        }

    }

    public void fall() {
        if (this.coords.getY() < this.fallY) {
            this.coords.setY(this.coords.getY() + 4);
        } else isCollectable = true;
    }
    public void drawShadow() {
        this.sketch.fill(80, 180, 120, (this.fallY - this.coords.getY())/2);
        this.sketch.ellipse(this.coords.getX() + 16, this.fallY + 22, 32, 28);

    }
}

package objects;

import processing.core.PApplet;
import util.FruitType;

public class FruitUI extends Fruit {

    public FruitUI(PApplet sketch, float x, float y, FruitType fruitType) {
        super(sketch, x, y);
        switch (fruitType) {
            case APPLE -> this.sprite = this.sketch.loadImage("assets/apple.png");
            case MELON -> this.sprite = this.sketch.loadImage("assets/melon.png");
            case MANGO -> this.sprite = this.sketch.loadImage("assets/mango.png");
            case PLUM -> this.sprite = this.sketch.loadImage("assets/plum.png");
            case BANANA -> this.sprite = this.sketch.loadImage("assets/banana.png");
            case STARFRUIT -> this.sprite = this.sketch.loadImage("assets/starFruit.png");
            case COCONUT -> this.sprite = this.sketch.loadImage("assets/coconut.png");
            case KIWI -> this.sprite = this.sketch.loadImage("assets/kiwi.png");
        }
    }
}

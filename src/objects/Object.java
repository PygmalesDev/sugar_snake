package objects;

import processing.core.PApplet;
import processing.core.PImage;
import util.Pair;

public abstract class Object {
    public PApplet sketch;
    public PImage sprite;
    public final Pair<Float, Float> coords;

    public Object(PApplet sketch, float x, float y) {
        this.sketch = sketch;
        this.coords = new Pair<>(x, y);
    }

    public void drawSprite() {
        this.sketch.image(this.sprite, this.coords.getX(), this.coords.getY());
    }
    abstract void drawShadow();
}


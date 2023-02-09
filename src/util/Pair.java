package util;

public class Pair<T, U> {
    private T x;
    private U y;

    public Pair(T x, U y) {
        this.x = x;
        this.y = y;
    }

    public T getX() {
        return this.x;
    }

    public U getY() {
        return this.y;
    }

    public void setX(T newX) {
        x = newX;
    }
    public void setY(U newY) {
        y = newY;
    }

}

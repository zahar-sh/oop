package sample.logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class MyFigure implements Figure, Drawable {
    private Color fill;

    public void setFillColor(Color color) {
        this.fill = color;
    }
    public Color getFillColor() {
        return fill;
    }

    public void moveX(int dx) {
        setX(getX() + dx);
    }
    public void moveY(int dy) {
        setY(getY() + dy);
    }

    public int getMinX() {
        return getX();
    }
    public int getMinY() {
        return getY();
    }
    public int getMaxX() {
        return getX() + getWidth();
    }
    public int getMaxY() {
        return getY() + getHeight();
    }

    public boolean contains(int x, int y) {
        return x > getMinX() && x < getMaxX() &&
                y > getMinY() && y < getMaxY();
    }

    public abstract void drawStroke(GraphicsContext g);
}
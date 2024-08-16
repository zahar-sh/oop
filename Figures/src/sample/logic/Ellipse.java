package sample.logic;

import javafx.scene.canvas.GraphicsContext;

public class Ellipse extends Circle {
    private int r2;

    public Ellipse() {
        super();
    }
    public Ellipse(int x, int y, int width, int height) {
        super(x, y, Math.min(width, height));
        this.r2 = width;
    }

    public void setHeight(int height) {
        this.r2 = height;
    }
    public int getHeight() {
        return r2;
    }

    public void draw(GraphicsContext g) {
        g.setFill(getFillColor());
        g.fillOval(getX(), getY(), getWidth(), r2);
    }
    public void drawStroke(GraphicsContext g) {
        g.strokeOval(getX(), getY(), getWidth(), r2);
    }
}
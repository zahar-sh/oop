package sample.logic;

import javafx.scene.canvas.GraphicsContext;

import java.util.Arrays;

public class Polygon extends MyFigure {
    private final double[] xs, ys;
    private int x, y, width, height;

    public Polygon(int l) {
        checkLength(l);
        xs = new double[l];
        ys = new double[l];
    }
    public Polygon(double[] xs, double[] ys) {
        this(xs, ys, Math.min(xs.length, ys.length));
    }
    public Polygon(double[] xs, double[] ys, int l) {
        checkLength(l);
        this.xs = Arrays.copyOf(xs, l);
        this.ys = Arrays.copyOf(ys, l);
        setBounds();
    }

    private void checkLength(int l) {
        if (l < 0)
            throw new IllegalArgumentException();
    }
    private void setBounds() {
        int minX = (int) xs[0];
        int maxX = (int) xs[0];
        int minY = (int) ys[0];
        int maxY = (int) ys[0];

        for (int i = 1; i < xs.length; i++) {
            minX = Math.min(minX, (int) xs[i]);
            maxX = Math.max(maxX, (int) xs[i]);
            minY = Math.min(minY, (int) ys[i]);
            maxY = Math.max(maxY, (int) ys[i]);
        }

        x = minX;
        y = minY;
        width = maxX - minX;
        height = maxY - minY;
    }

    public void setX(int x) {
        int dx = x - this.x;
        for (int i = 0; i < xs.length; i++)
            xs[i] += dx;
        this.x = x;
    }
    public void setY(int y) {
        int dy = y - this.y;
        for (int i = 0; i < ys.length; i++)
            ys[i] += dy;
        this.y = y;
    }
    public void setWidth(int width) {
        int dw = width - this.width;
        double each = (double) dw / xs.length;
        double maxX = xs[0];
        for (int i = 0; i < xs.length; i++) {
            xs[i] += i * each;
            if (xs[i] > maxX)
                maxX = xs[i];
        }
        this.width = (int) maxX - x;
    }
    public void setHeight(int height) {
        int dh = height - this.height;
        double each = (double) dh / ys.length;
        double maxY = ys[0];
        for (int i = 0; i < ys.length; i++) {
            ys[i] += i * each;
            if (ys[i] > maxY)
                maxY = ys[i];
        }
        this.height = (int) maxY - y;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    public void setPoint(int x, int y, int i) {
        xs[i] = x;
        ys[i] = y;
    }
    public Point getPoint(int i) {
        return new Point((int) xs[i], (int) ys[i]);
    }

    public void draw(GraphicsContext g) {
        g.setFill(getFillColor());
        g.fillPolygon(xs, ys, xs.length);
    }
    public void drawStroke(GraphicsContext g) {
        g.strokePolygon(xs, ys, xs.length);
        g.strokeRect(x, y, width, height);
    }
}
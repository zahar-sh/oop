package sample.logic;

public interface Figure {
    void setX(int x);
    void setY(int y);
    void setWidth(int width);
    void setHeight(int height);

    void moveX(int dx);
    void moveY(int dy);

    int getX();
    int getY();
    int getWidth();
    int getHeight();

    int getMinX();
    int getMinY();
    int getMaxX();
    int getMaxY();

    boolean contains(int x, int y);
}
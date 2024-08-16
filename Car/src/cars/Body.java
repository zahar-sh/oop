package cars;

public class Body {
    private int width, height, depth;
    private int weight;
    private int countPassengers;

    public Body(int width, int height, int depth, int weight, int countPassengers) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.weight = weight;
        this.countPassengers = countPassengers;
    }

    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        if (width < 0)
            throw new IllegalArgumentException();
        this.width = width;
    }

    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        if (height < 0)
            throw new IllegalArgumentException();
        this.height = height;
    }

    public int getDepth() {
        return depth;
    }
    public void setDepth(int depth) {
        if (depth < 0)
            throw new IllegalArgumentException();
        this.depth = depth;
    }

    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        if (weight < 0)
            throw new IllegalArgumentException();
        this.weight = weight;
    }

    public int getCountPassengers() {
        return countPassengers;
    }
    public void setCountPassengers(int countPassengers) {
        if (countPassengers < 0)
            throw new IllegalArgumentException();
        this.countPassengers = countPassengers;
    }

    public String toString() {
        return "Body{width=" + width +
                "\n height=" + height +
                "\n depth=" + depth +
                "\n weight=" + weight +
                "\n countPassengers=" + countPassengers + '}';
    }
}

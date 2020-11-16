package net.amygdalum.codejewels.constructors;

public class Format {
    private int width;
    private int height;

    public Format(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getArea() {
        return width * height;
    }
}

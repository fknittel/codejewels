package net.amygdalum.codejewels.constructors;

public class ImageFormat extends Format {

    private String category;
    private int border;

    public ImageFormat(int width, int height, int border) {
        super(width, height + border);
        this.border = border;
        this.category = buildCategory();
    }

    public String getCategory() {
        return category;
    }

    private String buildCategory() {
        if (getWidth() == getHeight()) {
            return "square";
        } else if (getWidth() > getHeight()) {
            return "landscape";
        } else if (getWidth() < getHeight()) {
            return "portrait";
        } else {
            throw new RuntimeException();
        }
    }

}

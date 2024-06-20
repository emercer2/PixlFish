package project;

import javafx.geometry.BoundingBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Pixel {
    double posX;
    double posY;
    double sideLength = 0;
    Color currentColor = Color.WHITE;

    public Pixel(double x, double y, double pxSize) {
        posX = x;
        posY = y;
        sideLength = pxSize;
    }

    public void setCurrentColor(Color color) {
        this.currentColor = color;
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public void setxy(double x, double y) {
        posX = x;
        posY = y;
    }

    public boolean intersects(Rectangle rect) {
        BoundingBox pxlrect = new BoundingBox(this.posX, this.posY, sideLength, sideLength);
        return rect.intersects(pxlrect);
    }
    public boolean containsPoint(double x, double y ) {
        BoundingBox pxlrect = new BoundingBox(this.posX, this.posY, sideLength, sideLength);
        return pxlrect.contains(x, y);
    }
}

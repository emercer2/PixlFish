package project;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Grid {
    private SimpleListProperty<Pixel> GridListProperty;
    double totalWid;
    double totalHeight;
    boolean visible = true;
    double pixelSize;

    public Grid(double width, double height, double size) {
        pixelSize = size;
        ArrayList<Pixel> list = new ArrayList<Pixel>();
        ObservableList<Pixel> observableList = (ObservableList<Pixel>) FXCollections.observableArrayList(list);
        GridListProperty = new SimpleListProperty<Pixel>(observableList);
        totalWid = width;
        totalHeight = height;
        int numx = (int) totalWid / (int)pixelSize;
        int numy = (int) totalHeight / (int)pixelSize;
        for (int i = 0; i < numx; i++) {
            for (int k = 0; k < numy; k++) {
                Pixel px = new Pixel(i *pixelSize, k * pixelSize, pixelSize);
                GridListProperty.add(px);
            }
        }
    }

    public SimpleListProperty<Pixel> getPixelList(){
        return GridListProperty;
    }

    public void colorPixel(double x, double y, double size, Color col){
        double centerx = 0;
        double centery = 0;
        for(Pixel p: GridListProperty){
            if(p.containsPoint(x, y)){
                centerx = p.posX+p.sideLength/2;
                centery = p.posY+ p.sideLength/2;
                p.setCurrentColor(col);
            }
        }
        for(Pixel p: GridListProperty){
            if(size%2 == 1){
                if (centerx - (size * pixelSize / 2)-0.5 <= p.posX && centerx + (size * pixelSize / 2)-0.5  / 2 >= p.posX && centery - (size *pixelSize / 2)-0.5  <= p.posY && centery + (size * pixelSize / 2)-0.5  >= p.posY) {
                    p.setCurrentColor(col);
                }
            }
            else {
                if (centerx - size * pixelSize / 2 <= p.posX && centerx + size *pixelSize / 2 >= p.posX && centery - size *pixelSize / 2 <= p.posY && centery + size * pixelSize / 2 >= p.posY) {
                    p.setCurrentColor(col);
                }
            }
        }
    }
    public void erasePixel(double x, double y, double size) {
        double centerx = 0;
        double centery = 0;
        for (Pixel p : GridListProperty) {
            if (p.containsPoint(x, y)) {
                centerx = p.posX + p.sideLength / 2;
                centery = p.posY + p.sideLength / 2;
                p.setCurrentColor(Color.WHITE);
            }
        }
        for (Pixel p : GridListProperty) {
            if (size % 2 == 1) {
                if (centerx - (size * pixelSize / 2) - 0.5 <= p.posX && centerx + (size * pixelSize / 2) - 0.5 / 2 >= p.posX && centery - (size * pixelSize / 2) - 0.5 <= p.posY && centery + (size * pixelSize / 2) - 0.5 >= p.posY) {
                    p.setCurrentColor(Color.WHITE);
                }
            } else {
                if (centerx - size * pixelSize / 2 <= p.posX && centerx + size * pixelSize / 2 >= p.posX && centery - size * pixelSize / 2 <= p.posY && centery + size * pixelSize / 2 >= p.posY) {
                    p.setCurrentColor(Color.WHITE);
                }
            }
        }
    }
}

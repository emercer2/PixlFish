package project;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class ColorList extends FlowPane {
    List<Color> recents = new ArrayList();
    int curX =0;
    int curY =0;
    List<Rectangle> recentRects = new ArrayList();
    public ColorList(){
        this.setMaxWidth(160);
        this.setBackground(new Background(new BackgroundFill(Color.rgb(63, 155, 123), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void addColor(Color col){
        boolean isthere = false;
        for(int i =0 ; i<recents.size(); i++){
            if(recents.get(i) == col ||((int)Math.round(col.getRed()*255) == (int)Math.round(recents.get(i).getRed()*255) && (int)Math.round(col.getGreen()*255)== (int)Math.round(recents.get(i).getGreen()*255) && (int)Math.round(col.getBlue()*255)== (int)Math.round(recents.get(i).getBlue()*255)&&(int)Math.round(col.getOpacity())== (int)Math.round(recents.get(i).getOpacity()))){
                isthere = true;
            }
        }
        if(!isthere && recents.size()<50) {
            recents.add(col);
            addDraw();
        }
        else if ( isthere){
            toFrontDraw(col);
        }
        else if (!isthere ){
            toFrontDrawReplace(col);
        }
    }

    public void addDraw(){
        Rectangle rect = new Rectangle();
        rect.setHeight(15);
        rect.setWidth(15);
        rect.setX(curX);
        rect.setY(curY);
        rect.setStroke(Color.BLACK);
        rect.setFill(recents.get(recents.size()-1));
        curX = curX + 15;
        if(curX%150 == 0){
            curY = curY+15;
            curX =0;
        }
        recentRects.add(rect);
        this.getChildren().add(rect);
    }

    public void toFrontDraw(Color col){
        curX =0;
        curY =0;
        boolean found = false;
        for(int i=recents.size()-1;i>0;i--) {
            if(found){
                recents.set(i, recents.get(i-1));
            }
            if (recents.get(i) == col || ((int) Math.round(col.getRed() * 255) == (int) Math.round(recents.get(i).getRed() * 255) && (int) Math.round(col.getGreen() * 255) == (int) Math.round(recents.get(i).getGreen() * 255) && (int) Math.round(col.getBlue() * 255) == (int) Math.round(recents.get(i).getBlue() * 255) && (int) Math.round(col.getOpacity() * 255) == (int) Math.round(recents.get(i).getOpacity() * 255))) {
                recents.set(i, recents.get(i-1));
                found=true;
            }
        }
        recents.set(0, col);
        this.getChildren().removeAll(recentRects);
        recentRects.clear();
        for(int i=0; i<recents.size();i++){
            Rectangle rect = new Rectangle();
            rect.setHeight(15);
            rect.setWidth(15);
            rect.setX(curX);
            rect.setY(curY);
            rect.setStroke(Color.BLACK);
            rect.setFill(recents.get(i));
            curX = curX + 15;
            if(curX%150 == 0){
                curY = curY+15;
                curX =0;
            }
            recentRects.add(rect);
        }
        this.getChildren().addAll(recentRects);
    }

    public void toFrontDrawReplace(Color col){
        curX =0;
        curY =0;
        for(int i=recents.size()-1;i>0;i--) {
            recents.set(i, recents.get(i-1));
        }
        recents.set(0, col);
        this.getChildren().removeAll(recentRects);
        recentRects.clear();
        for(int i=0; i<recents.size();i++){
            Rectangle rect = new Rectangle();
            rect.setHeight(15);
            rect.setWidth(15);
            rect.setX(curX);
            rect.setY(curY);
            rect.setStroke(Color.BLACK);
            rect.setFill(recents.get(i));
            curX = curX + 15;
            if(curX%150 == 0){
                curY = curY+15;
                curX =0;
            }
            recentRects.add(rect);
        }
        this.getChildren().addAll(recentRects);
    }

    public void clear(){
        recents.clear();
        this.getChildren().removeAll(recentRects);
        recentRects.clear();

    }
}

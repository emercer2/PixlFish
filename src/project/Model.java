package project;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Model{
        Grid gridVar;
        ColorList cList = new ColorList();


        public Model(){


        }

        //functions!
        public void reset(){
                Main.viewCreate.reset();
        }

        public void create(){
                double wid =Double.parseDouble(Main.viewCreate.widthField.getText());
                double hig = Double.parseDouble(Main.viewCreate.heightField.getText());
                double size = Double.parseDouble( Main.viewCreate.pixSizeField.getText());
                cList.clear();
                Main.viewCreate.viewDraw.setCan(wid, hig ,size);
                gridVar = new Grid(wid, hig, size);
                grid(true);
                Main.viewCreate.create();
        }

        public void adjustWidth(String newVal){
                Main.viewCreate.adjustWidth(newVal);
        }

        public void adjustHeight(String newVal){
                Main.viewCreate.adjustHeight(newVal);
        }


        public void adjustPixelSize(String newVal){
                Main.viewCreate.pixSize(newVal);
        }

        public void flipGrid(){
                grid(!gridVar.visible);
        }

        public void erase(){
                Main.viewCreate.viewDraw.erase(gridVar);
        }

        public void draw(){
                Main.viewCreate.viewDraw.draw(gridVar, cList);
        }

        public void save(){
                grid(false);
                Main.viewCreate.viewDraw.save();
                grid(true);
        }

        public void drawHelp(){
                Main.viewCreate.viewDraw.help();
        }

        public void createHelp(){
                Main.viewCreate.help();
        }

        public void newCan(){
               Main.viewCreate.viewDraw.create();
        }

        public void recentColorSelected(Rectangle rectClicked){
                cList.toFrontDraw((Color) rectClicked.getFill());
                Main.viewCreate.viewDraw.setColorFromList((Color) rectClicked.getFill());
        }

        public void drawingInProgress(double x, double y, boolean drag){
                double size = Main.viewCreate.viewDraw.getDrawSize();
                gridVar.colorPixel(x- Main.viewCreate.viewDraw.can.getLayoutX(), y - Main.viewCreate.viewDraw.can.getLayoutY() , size, (Color) Main.viewCreate.viewDraw.rect1.getFill());
                grid(gridVar.visible);
                if(!drag) {
                        cList.addColor((Color) Main.viewCreate.viewDraw.rect1.getFill());
                }
        }

        public void eraseInProgress(double x, double y){
                double size = Main.viewCreate.viewDraw.getEraseSize();
                gridVar.erasePixel(x- Main.viewCreate.viewDraw.can.getLayoutX(), y - Main.viewCreate.viewDraw.can.getLayoutY(), size);
                grid(gridVar.visible);
        }

        public void colorWheel(double x, double y){
                Main.viewCreate.viewDraw.setNewColor(x, y);
        }

        public void grid(boolean set){
                Main.viewCreate.viewDraw.grid(set, gridVar);
        }

}

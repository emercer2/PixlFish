package project;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import java.util.ArrayList;
import java.util.List;

public class Colors extends Pane {
    static Canvas layer1 = new Canvas();
    Color currentSelected = Color.BLACK;
    GraphicsContext gc;
    List<Color> colorOrder = new ArrayList();
    public Colors() {

        for (int r=0; r<100; r+=5) colorOrder.add(Color.rgb(r*255/100,255,0));
        for (int g=100; g>0; g--) colorOrder.add(Color.rgb(255, g*255/100,0));
        for (int b=0; b<100; b+=5) colorOrder.add(Color.rgb(255,0, b*255/100));
        for (int r=100; r>0; r--) colorOrder.add(Color.rgb(r*255/100,0,255));
        for (int g=0; g<100; g+=5) colorOrder.add(Color.rgb(0, g * 255 / 100, 255));
        for (int b=100; b>0; b--) colorOrder.add(Color.rgb(0,255,b*255/100));
        colorOrder.add(Color.rgb(0,255,0));
        gc = layer1.getGraphicsContext2D();

        this.getChildren().add(layer1);
        currentSelected = Color.BLACK;
        drawShapes(100, 100);
        }

    public void setValue(Color color, double x, double y){
        currentSelected = color;
        drawShapes(x, y);
    }

    public Color getValue() {
        return currentSelected;
    }

    private void drawShapes(double x, double y) {
        gc = layer1.getGraphicsContext2D();
        layer1.setWidth(200);
        layer1.setHeight(200);
        gc.setFill(Color.rgb(9,111,102));
        gc.fillRect(0, 0, layer1.getWidth(), layer1.getHeight());
        for (int i = 0; i <= 360; i++) {
            Color circcolor = colorOrder.get(i);
            Stop[] stops = new Stop[]{
                    new Stop(0.3, Color.WHITE),
                    new Stop(1.0, circcolor)
            };
            RadialGradient gradient = new RadialGradient(0, 0, 100, 100, 100, false, CycleMethod.NO_CYCLE, stops);
            gc.setStroke(gradient);
            gc.setLineWidth(3);

            double circx = 100 * Math.sin(Math.PI * 2 * i / 360);
            double circy = 100 * Math.cos(Math.PI * 2 * i / 360);
            circx = Math.round(circx * 100) / 100;
            circy = Math.round(circy * 100) / 100;
            gc.strokeLine(100, 100, circx + 100, circy + 100);
        }
        gc.setFill(Color.BLACK);
        gc.fillOval(100 - 15, 100 - 15, 30, 30);
        if(x!=0 && y!= 0) {
            gc.setStroke(Color.BLACK);
            gc.setLineWidth(3);
            gc.strokeOval(x - 5, y - 5, 10, 10);
            gc.setStroke(Color.WHITE);
            gc.setLineWidth(1);
            gc.strokeOval(x - 5, y - 5, 10, 10);
        }
    }

    }

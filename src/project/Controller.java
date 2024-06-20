package project;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Controller {
    Circle circ = new Circle();
    public Controller() {
        Main.viewCreate.viewDraw.addEventHandler(MouseEvent.ANY, new MouseHandler());
        Main.viewCreate.reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.model.reset();
            }
        });
        Main.viewCreate.create.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.model.create();
            }
        });
        Main.viewCreate.widthField.textProperty().addListener((observable, oldValue, newValue) -> {
            Main.model.adjustWidth(newValue);
        });
        Main.viewCreate.heightField.textProperty().addListener((observable, oldValue, newValue) -> {
            Main.model.adjustHeight(newValue);
        });
        Main.viewCreate.pixSizeField.textProperty().addListener((observable, oldValue, newValue) -> {
            Main.model.adjustPixelSize(newValue);
        });
        Main.viewCreate.viewDraw.grid.setOnAction(e -> {
            Main.model.flipGrid();
        });

        Main.viewCreate.viewDraw.erase.setOnAction(e -> {
            Main.model.erase();
        });
        Main.viewCreate.viewDraw.draw.setOnAction(e -> {
            Main.model.draw();
        });

        Main.viewCreate.viewDraw.tg1.selectedToggleProperty().addListener((obsVal, oldVal, newVal) -> {
            if (newVal == null)
                oldVal.setSelected(true);
        });

        Main.viewCreate.viewDraw.save.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                Main.model.save();
            }
        });
        Main.viewCreate.viewDraw.help.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                Main.model.drawHelp();
            }
        });
        Main.viewCreate.help.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                Main.model.createHelp();
            }
        });
        Main.viewCreate.viewDraw.back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.model.drawHelp();
            }
        });
        Main.viewCreate.back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.model.createHelp();

            }
        });

        Main.viewCreate.viewDraw.newItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.model.newCan();
            }
        });
    }

    public class MouseHandler implements EventHandler<javafx.scene.input.MouseEvent> {
        @Override
        public void handle(javafx.scene.input.MouseEvent e) {
            Rectangle rectClicked = null;
            if (Main.viewCreate.viewDraw.draw.isSelected()) {
                if (e.getEventType() == MouseEvent.MOUSE_PRESSED) {
                    if (Main.model.cList.contains(calcCListX(e.getX()), calcCListY(e.getY()))){
                        for (Rectangle rect : Main.model.cList.recentRects) {
                            if (rect.contains(calcCListX(e.getX()), calcCListY(e.getY()))){
                                rectClicked = rect;
                            }
                        }
                    }
                }
                setCirc();
            }
            if (Main.viewCreate.viewDraw.draw.isSelected() && rectClicked != null) {
                Main.model.recentColorSelected(rectClicked);
            }

            if (e.getEventType() == MouseEvent.MOUSE_PRESSED) {
                if (Main.viewCreate.viewDraw.draw.isSelected() && Main.viewCreate.viewDraw.can.contains(e.getX() - Main.viewCreate.viewDraw.can.getLayoutX(), e.getY() - Main.viewCreate.viewDraw.can.getLayoutY())) {
                    Main.model.drawingInProgress(e.getX(), e.getY(), false);
                }
                if (Main.viewCreate.viewDraw.draw.isSelected() && circ.contains(e.getX(), e.getY())) {
                    Main.model.colorWheel(e.getX(), e.getY());
                }
                if (Main.viewCreate.viewDraw.erase.isSelected() && Main.viewCreate.viewDraw.can.contains(e.getX() - Main.viewCreate.viewDraw.can.getLayoutX(), e.getY() - Main.viewCreate.viewDraw.can.getLayoutY())) {
                    Main.model.eraseInProgress(e.getX(), e.getY());
                }
            }
            if (e.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                if (Main.viewCreate.viewDraw.draw.isSelected() && Main.viewCreate.viewDraw.can.contains(e.getX() - Main.viewCreate.viewDraw.can.getLayoutX(), e.getY() - Main.viewCreate.viewDraw.can.getLayoutY())) {
                    Main.model.drawingInProgress(e.getX(), e.getY(), true);
                }
                if (Main.viewCreate.viewDraw.erase.isSelected() && Main.viewCreate.viewDraw.can.contains(e.getX() - Main.viewCreate.viewDraw.can.getLayoutX(), e.getY() - Main.viewCreate.viewDraw.can.getLayoutY())) {
                    Main.model.eraseInProgress(e.getX(), e.getY());
                }
                if (Main.viewCreate.viewDraw.draw.isSelected() && circ.contains(e.getX(), e.getY())) {
                    Main.model.colorWheel(e.getX(), e.getY());
                }
            }
        }
    }

    public double calcCListX(double x){
        return x - Main.viewCreate.viewDraw.sideDrawer.getLayoutX() - ((Main.viewCreate.viewDraw.sideDrawer.getWidth() - Main.model.cList.getWidth()) / 2);
    }
    public double calcCListY(double y){
        return y- Main.viewCreate.viewDraw.color.getHeight() - Main.viewCreate.viewDraw.buttons.getHeight() - Main.viewCreate.viewDraw.drawslider.getHeight() - Main.viewCreate.viewDraw.rect1.getHeight() - Main.viewCreate.viewDraw.transslide.getHeight() - Main.viewCreate.viewDraw.menuBar.getHeight() - 54;
    }

    public void setCirc(){
        circ.setLayoutX(Main.viewCreate.viewDraw.color.getLayoutX());
        circ.setLayoutY(Main.viewCreate.viewDraw.color.getLayoutY() + Main.viewCreate.viewDraw.sideDrawer.getLayoutY());
        circ.setRadius(100);
        circ.setCenterX(Main.viewCreate.viewDraw.sideDrawer.getLayoutX() + 108);
        circ.setCenterY(Main.viewCreate.viewDraw.color.getLayoutY() + Main.viewCreate.viewDraw.sideDrawer.getLayoutY() + 100 + Main.viewCreate.viewDraw.buttons.getHeight());
    }
}

package project;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ViewDrawing extends BorderPane {
    VBox items = new VBox();
    Colors color = new Colors();
    HBox buttons = new HBox();
    Button back = new Button("Back");
    Popup pop = new Popup();
    final Text title = new Text("Pencil Size");
    ToggleGroup tg1 = new ToggleGroup();
    Image drawImg = new Image(getClass().getClassLoader().getResourceAsStream("images/draw.png"));
    ToggleButton draw = new ToggleButton("", new ImageView(drawImg));
    static Image eraseImg = new Image(ViewDrawing.class.getClassLoader().getResourceAsStream("images/erase.png"));
    static ToggleButton erase = new ToggleButton("", new ImageView(eraseImg));
    static Image gridImg = new Image(ViewDrawing.class.getClassLoader().getResourceAsStream("images/grid.png"));
    static ToggleButton grid = new ToggleButton("", new ImageView(gridImg));
    Canvas can = new Canvas();
    GraphicsContext gc;
    Slider drawslider = new Slider();
    Slider eraseslider = new Slider();
    Pane sideDrawer = new Pane();
    MenuBar menuBar = new MenuBar();
    Menu file = new Menu("Menu");
    MenuItem newItem = new MenuItem("New");
    MenuItem save = new MenuItem("Save");
    MenuItem help = new MenuItem("Help");

    Text currentcolorL = new Text("Current Color: ");
    Text transparentL = new Text("Transparency: ");
    Text recentcolorsL = new Text("Recent Colors Used: ");

    Rectangle rect1 = new Rectangle(12, 12);
    Slider transslide = new Slider();
    public ViewDrawing(){

        currentcolorL.setFill(Color.LIGHTGREEN);
        currentcolorL.setFont(Font.font(null, 14));


        title.setFill(Color.LIGHTGREEN);
        title.setFont(Font.font(null, 14));

        transparentL.setFill(Color.LIGHTGREEN);
        transparentL.setFont(Font.font(null, 14));

        recentcolorsL.setFill(Color.LIGHTGREEN);
        recentcolorsL.setFont(Font.font(null, 14));

        file.getItems().addAll(newItem, save, help);
        menuBar.getMenus().addAll(file);
        menuBar.setBackground(new Background(new BackgroundFill(Color.rgb(0,225,221), CornerRadii.EMPTY, Insets.EMPTY)));

        tg1.getToggles().addAll(draw, erase);
        buttons.getChildren().addAll(draw, erase, grid);
        buttons.setMinWidth(200);
        buttons.setAlignment(Pos.CENTER);
        items.getChildren().addAll(buttons);
        items.setAlignment(Pos.CENTER);
    }

    public void setCan(double wid, double hig, double size){
        transslide.setValue(100);
        drawslider.setValue(0);
        eraseslider.setValue(0);
        rect1.setFill(Color.BLACK);
        color.setValue(Color.BLACK, 100, 100);
        can = new Canvas();
        can.setLayoutY(can.getLayoutY());
        can.setWidth(wid);
        can.setHeight(hig);
        gc = can.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(can.getLayoutX(), can.getLayoutY(), can.getWidth(), can.getHeight());
        GridPane.setHalignment(can, HPos.CENTER);
        GridPane.setValignment(can, VPos.CENTER);

        sideDrawer.setMinWidth(200);
        sideDrawer.setBackground(new Background(new BackgroundFill(Color.rgb(9,111,102), CornerRadii.EMPTY, Insets.EMPTY)));

        items.setAlignment(Pos.CENTER);
        sideDrawer.getChildren().removeAll(items);
        sideDrawer.getChildren().addAll(items);
        this.getChildren().removeAll(menuBar, sideDrawer, can);
        this.setTop(menuBar);
        this.setRight(sideDrawer);
        this.setCenter(can);
        this.setBackground(new Background(new BackgroundFill(Color.rgb(63, 155, 123), CornerRadii.EMPTY, Insets.EMPTY)));
    }
    public void draw(Grid gridVar, ColorList cList){
        items.getChildren().removeAll(items.getChildren());
        drawslider.setMin(1.0);
        double max = can.getHeight()/gridVar.pixelSize;
        if(can.getWidth()>can.getHeight()){
            max = can.getWidth()/gridVar.pixelSize;
        }
        drawslider.setMax(max);
        drawslider.setValue(1.0);
        final Label text1 = new Label((Double.toString(drawslider.getValue())));
        drawslider.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                drawslider.setValue(newValue.intValue());
                text1.setText(String.format("%.1f" + " Px", drawslider.getValue()));
            }
        });
        HBox slides = new HBox();
        slides.getChildren().addAll(drawslider, text1);
        slides.setAlignment(Pos.CENTER);
        HBox cur = new HBox();
        rect1.setFill(Color.BLACK);
        rect1.setStroke(Color.BLACK);
        transslide.setMax(100);
        transslide.setValue(0);
        transslide.setValue(100);
        transslide.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                transslide.setValue(newValue.intValue());
                setNewColor(newValue.intValue());
            }
        });
        cur.getChildren().addAll(currentcolorL, rect1);
        HBox trans = new HBox();
        trans.setAlignment(Pos.CENTER);
        trans.getChildren().addAll(transparentL, transslide);
        HBox forcolor = new HBox();
        forcolor.setAlignment(Pos.CENTER);
        forcolor.getChildren().add(color);
        items.setAlignment(Pos.CENTER);

        HBox forlist = new HBox();
        forlist.getChildren().add(cList);
        forlist.setAlignment(Pos.CENTER);
        items.getChildren().addAll( buttons, forcolor, title, slides, cur, trans, recentcolorsL, forlist);
    }
    public void erase(Grid gridVar){
        items.getChildren().removeAll(items.getChildren());
        eraseslider.setMin(1.0);
        double max = can.getHeight()/gridVar.pixelSize;
        if(can.getWidth()>can.getHeight()){
            max = can.getWidth()/gridVar.pixelSize;
        }
        eraseslider.setMax(max);
        eraseslider.setValue(1.0);
        final Label title = new Label("Eraser Size");
        final Label text1 = new Label((Double.toString(eraseslider.getValue())));
        eraseslider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                eraseslider.setValue(newValue.intValue());
                text1.setText(String.format("%.1f" + " Px", eraseslider.getValue()));
            }
        });
        HBox slides = new HBox();
        slides.setAlignment(Pos.CENTER);
        slides.getChildren().addAll(eraseslider, text1);
        items.getChildren().addAll( buttons,title, slides);
    }
    public void grid(boolean set, Grid gridVar){
        gridVar.visible = set;
        gc = can.getGraphicsContext2D();
        gc.clearRect(0, 0, can.getWidth(), can.getHeight());
        for(Pixel p : gridVar.getPixelList()){
            gc.setFill(p.getCurrentColor());
            if(gridVar.visible) {
                gc.setStroke(Color.LIGHTGRAY);
                gc.strokeRect(p.posX, p.posY, p.sideLength, p.sideLength);
            }
            gc.fillRect(p.posX, p.posY, p.sideLength, p.sideLength);
        }
    }
    public double getDrawSize(){
        return drawslider.getValue();
    }

    public double getEraseSize(){
        return eraseslider.getValue();
    }

    public void save() {
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG","*.png"));
            fc.setTitle("Save image ");
            File file = fc.showSaveDialog(Main.stage);
            if(file != null){
                SnapshotParameters sp = new SnapshotParameters();
                sp.setFill(Color.TRANSPARENT);
                WritableImage wi = new WritableImage((int)can.getWidth(),(int)can.getHeight());
                try {                    ImageIO.write(SwingFXUtils.fromFXImage(can.snapshot(sp,wi),null),"png",file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }
    public void create(){
        BorderPane root = new BorderPane();
        root.setMinWidth(700);
        root.setMinHeight(500);
        root.setCenter(Main.viewCreate);
        Main.root = root;
        Main.scene.setRoot(root);
        Main.stage.setScene(Main.scene);
    }

    public void setNewColor(double x, double y){
        Color color2 = Color.BLACK;
        WritableImage writableImage = new WritableImage((int) color.layer1.getWidth(), (int) color.layer1.getWidth());
        color.layer1.snapshot(null, writableImage);
        PixelReader pixelReader = writableImage.getPixelReader();
        color2 = pixelReader.getColor((int) (x - color.getLayoutX() - sideDrawer.getLayoutX() ), (int) (y -buttons.getHeight()-menuBar.getHeight()));
        color.setValue(color2, x- color.getLayoutX() - sideDrawer.getLayoutX(), y -buttons.getHeight()-menuBar.getHeight());
        rect1.setFill(color2);
        transslide.setValue(100);
    }

    public void setNewColor(double transparency){
        Color col = color.currentSelected;
        col = Color.rgb((int)Math.round(col.getRed()*255), (int)Math.round(col.getGreen()*255), (int)Math.round(col.getBlue()*255), transparency/100);
        rect1.setFill(col);
    }

    public void setColorFromList(Color col){
        Color color2 = Color.rgb((int)Math.round(col.getRed()*255),(int)Math.round(col.getGreen()*255), (int)Math.round(col.getBlue()*255),  (int)Math.round(col.getOpacity()));
        color.setValue(color2, 0, 0);
        rect1.setFill(color2);
        transslide.setValue(100);
    }
    public void help(){

        if (!pop.isShowing()) {
            Image img = new Image("images/logo.png");
            Pane pane = new Pane();
            pane.setBackground(new Background(new BackgroundFill(Color.rgb(227, 255, 127), CornerRadii.EMPTY, Insets.EMPTY)));
            back.setAlignment(Pos.CENTER);
            back.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            VBox vb = new VBox();
            vb.setBackground(new Background(new BackgroundFill(Color.rgb(148, 255, 199), CornerRadii.EMPTY, Insets.EMPTY)));
            vb.setAlignment(Pos.CENTER);
            vb.setLayoutX(pane.getLayoutX()+15);
            vb.setLayoutY(pane.getLayoutY()+15);

            pane.setMinWidth(850);
            pane.setMinHeight(550);
            vb.setMinWidth(820);
            vb.setMinHeight(520);

            Text tx1 = new Text("Drawing in PixelFish");
            Text txAuthor = new Text("created by Elizabeth Mercer");

            Text tx2 = new Text("Currently you are in our Drawing View\n");
            Text tx3 = new Text("Here there are meany options for your drawing needs:\n\n" +
                    "Draw: Do you want to add to your canvas, well choose a color and get to it\n" +
                            "\tColor Wheel: Choose any color you wish\n"+
                            "\tPencil Size: Adjust the size of the pencil and how many pixels are covered with one click\n"+
                            "\tCurrent Color: Displays the color you currently have selected from either the color wheel or recent colors list\n"+
                            "\tTransparency: Adjust the transparency of your current color, it goes all the way to completely transparent\n"+
                            "\tRecent Color List: This lists the past 50 colors you have used on the canvas! Feel free to select any of them to reuse\n"+
                    "Erase: Do you want to return to your original canvas color, erase your mistakes with ease\n" +
                        "\tEraser Size: Adjust the size of the eraser and how many pixels are covered with one click\n"+
                    "Show Grid: Too good for the grid huh, well fine, click the grid button and it will disappear. This can be useful for seeing\n what the final png will look like after a save\n" +
                    "Menu: There is a few other options than this help section if you didn't see\n"+
                        "\tNew: Dont like your current project or canvas size, start over and return to the canvas Creation View\n"+
                        "\tSave: Are you done drawing, want to save as a png to show off click this option and you can do that exact thing\n");
            tx1.setFont(Font.font(null, FontWeight.BOLD, 20));
            tx1.setFill(Color.CORAL);

            txAuthor.setFill(Color.CORAL);
            txAuthor.setFont(Font.font(null, 14));

            tx2.setFill(Color.CORAL);
            tx2.setFont(Font.font(null, 14));

            tx3.setFill(Color.DARKGREEN);
            tx3.setFont(Font.font(null, 14));

            ImageView imageView = new ImageView();
            imageView.setImage(img);
            imageView.setX(10);
            imageView.setY(10);
            imageView.setFitWidth(100);
            imageView.setPreserveRatio(true);

            vb.getChildren().addAll(imageView,tx1,txAuthor, tx2, tx3,  back);
            pane.getChildren().addAll(vb);

            pop.getContent().add(pane);
            pop.show(Main.stage);
        }
        else {
            pop.hide();
        }
    }
}
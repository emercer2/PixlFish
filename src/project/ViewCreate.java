package project;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Popup;

public class ViewCreate extends BorderPane{
    static GridPane pane = new GridPane();
    ViewDrawing viewDraw = new ViewDrawing();
    static Canvas can = new Canvas();
    Popup pop = new Popup();
    Pane sideDrawer = new Pane();
    Text enterWid = new Text("Enter Width: ");
    Text enterHig = new Text("Enter Height: ");
    Menu menu = new Menu("Menu");
    MenuItem help = new MenuItem("Help");
    Text PixSize = new Text("Desired Pixel Size: ");
    static TextField widthField = new TextField("400");
    static TextField heightField = new TextField("400");
    static TextField pixSizeField = new TextField("20");
    static Text widthMessage = new Text("");
    static Text heightMessage = new Text("");
    static Text pixSizeMessage = new Text("");
    static Text error = new Text("");
    VBox items = new VBox();
    HBox hbox = new HBox();
    static Button create = new Button("Create");
    static Button reset = new Button("Reset");
    Button back = new Button("Back");
    public ViewCreate() {
        enterWid.setFill(Color.LIGHTGREEN);
        enterWid.setFont(Font.font(null, 14));

        enterHig.setFill(Color.LIGHTGREEN);
        enterHig.setFont(Font.font(null, 14));

        PixSize.setFill(Color.LIGHTGREEN);
        PixSize.setFont(Font.font(null, 14));

        widthMessage.setFill(Color.LIGHTGREEN);
        widthMessage.setFont(Font.font(null, 14));

        heightMessage.setFill(Color.LIGHTGREEN);
        heightMessage.setFont(Font.font(null, 14));
        pixSizeMessage.setFill(Color.LIGHTGREEN);
        pixSizeMessage.setFont(Font.font(null, 14));
        error.setFill(Color.LIGHTGREEN);
        error.setFont(Font.font(null, 14));

        pane.setBackground(new Background(new BackgroundFill(Color.rgb(63, 155, 123), CornerRadii.EMPTY, Insets.EMPTY)));
        drawCan(Double.parseDouble(widthField.getText()), Double.parseDouble(heightField.getText()), Double.parseDouble(pixSizeField.getText()));
        pane.getChildren().add(can);

        sideDrawer.setMinWidth(150);
        sideDrawer.setBackground(new Background(new BackgroundFill(Color.rgb(9,111,102), CornerRadii.EMPTY, Insets.EMPTY)));
        hbox.getChildren().addAll(create, reset);
        hbox.setAlignment(Pos.CENTER);
        items.getChildren().addAll(enterWid,widthField,widthMessage, enterHig, heightField,heightMessage,PixSize,pixSizeField, pixSizeMessage, hbox,error);
        items.setAlignment(Pos.CENTER);
        sideDrawer.getChildren().addAll(items);
        MenuBar menuBar = new MenuBar();
        menuBar.setBackground(new Background(new BackgroundFill(Color.rgb(0,225,221), CornerRadii.EMPTY, Insets.EMPTY)));
        menu.getItems().add(help);
        menuBar.getMenus().add(menu);
        this.setTop(menuBar);
        this.setCenter(pane);
        this.setRight(sideDrawer);
    }
    public void create(){
        if(Double.parseDouble(heightField.getText())%Double.parseDouble( pixSizeField.getText())!=0 ||Double.parseDouble(widthField.getText())%Double.parseDouble( pixSizeField.getText())!=0){
            error.setText("Please choose a pixel, \nwidth and height that divide evenly");
        }
        if( error.getText().equals("") ) {
            BorderPane root = new BorderPane();
            root.setMinWidth(can.getWidth() + 200);
            root.setMinHeight(can.getHeight() + 100);
            root.setCenter(viewDraw);
            Main.root = root;
            Main.scene.setRoot(root);
            Main.stage.setScene(Main.scene);
        }
    }

    public void reset(){

        can.setLayoutX(0);
        can.setLayoutY(0);
        widthField.setText("400");
        heightField.setText("400");
        pixSizeField.setText("20");
        widthMessage.setText("");
        heightMessage.setText("" );
        error.setText("");
        pixSizeMessage.setText("");
        drawCan(Double.parseDouble(widthField.getText()), Double.parseDouble(heightField.getText()), Double.parseDouble(pixSizeField.getText()));
        Main.root.setMinWidth(can.getWidth() + 400);
        Main.root.setMinHeight(can.getHeight() + 200);
    }

    public void adjustHeight(String newVal){
        try {
            double height = Double.parseDouble(newVal);
            can.setLayoutX(0);
            can.setLayoutY(0);
            drawCan(can.getWidth(), height, Double.parseDouble(pixSizeField.getText()));
            heightMessage.setText("");
            if(height<=0){
                heightMessage.setText("invalid input: value must be more than 0");
            }
            Main.root.setMinHeight(can.getHeight()+200);
        }
        catch(NumberFormatException e){
            heightMessage.setText("invalid input");
        }
        if(!heightMessage.getText().equals("")){
            error.setText("Invalid Input");
        }
        else{
            error.setText("");
        }
    }
    public void adjustWidth(String newVal){
        try {
            double width = Double.parseDouble(newVal);

            can.setLayoutX(0);
            can.setLayoutY(0);
            drawCan(width, can.getHeight(), Double.parseDouble(pixSizeField.getText()));
            widthMessage.setText("");
            if(width<=0){
                widthMessage.setText("invalid input: value must be more than 0");
            }
            Main.root.setMinWidth(can.getWidth()+400);
        }
        catch(NumberFormatException e){
            widthMessage.setText("invalid input");
        }
        if(!widthMessage.getText().equals("")){
            error.setText("Invalid Input");
        }
        else{
            error.setText("");
        }
    }
    public void pixSize(String newVal){
        try {
            can.setLayoutX(0);
            can.setLayoutY(0);
            drawCan(can.getWidth(), can.getHeight(), Double.parseDouble(newVal));
            pixSizeMessage.setText("");
            if(Double.parseDouble(newVal)<=0){
                pixSizeMessage.setText("invalid input: value must be more than 0");
            }
        }
        catch(NumberFormatException e){
            pixSizeMessage.setText("invalid input");
        }
        if(!pixSizeMessage.getText().equals("")){
            error.setText("Invalid Input");
        }
        else{
            error.setText("");
        }
    }
    public void drawCan(double width, double height, double size){
        pane.setAlignment(Pos.CENTER);
        can.setWidth( width);
        can.setHeight(height);
        GraphicsContext gc = can.getGraphicsContext2D();
        gc.clearRect(can.getLayoutX(),  can.getLayoutY(), can.getWidth(), can.getHeight());
        gc.setFill(Color.WHITE);
        gc.fillRect(can.getLayoutX(), can.getLayoutY(), width,height);
        gc.setFill(Color.ORANGE);
        gc.fillRect(can.getLayoutX(), can.getLayoutY(), size, size);
        GridPane.setHalignment(can, HPos.CENTER);
        GridPane.setValignment(can, VPos.CENTER);
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

            pane.setMinWidth(800);
            pane.setMinHeight(480);
            vb.setMinWidth(770);
            vb.setMinHeight(450);

            Text tx1 = new Text("Welcome to PixelFish!");

            Text txAuthor = new Text("created by Elizabeth Mercer");
            Text tx2 = new Text("Currently you are in our Canvas Creation View\n");
            Text tx3 = new Text("Here you have three decisions to make:\n\n" +
                    "Width: In pixels must be more than 0, the canvas will extend to show you the real image width that will be created\n" +
                    "Height: In pixels must be more than 0, the canvas will extend to show you the real image height that will be created\n" +
                    "Pixel Size: In pixels must be more than 0, This must evenly divide the width and height to be valid. The orange square\n \twill show you a preview of one pixel on your canvas\n\n" +
                    "The reset button will return settings to original values, (Width: 400, Height: 400, Pixel Size: 20)\n" +
                    "If you press the create button you must be ready to draw, enjoy!\n");
            tx1.setFont(Font.font(null, FontWeight.BOLD, 36));
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

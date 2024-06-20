package project;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main extends Application {

    public static Scene scene;
    public static final Model model = new Model();
    public static ViewCreate viewCreate = new ViewCreate();
    public static final Controller control = new Controller();
    public static BorderPane root = new BorderPane();
    public static Stage stage;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {



        stage = primaryStage;

        loadSplashScreen();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void loadSplashScreen() {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(("Splash.fxml")));
            root.setMinWidth(700);
            root.setMinHeight(500);
            root.getChildren().setAll(pane);


            FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), pane);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), pane);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);

            fadeIn.play();

            fadeIn.setOnFinished((e) -> {
                fadeOut.play();
            });

            fadeOut.setOnFinished((e) -> {
                    root.getChildren().removeAll();
                    root.setMinWidth(700);
                    root.setMinHeight(500);
                    root.setCenter(viewCreate);
            });
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


package pl.comp.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainApp extends Application {

    private Stage stage;
    private static final Logger LOG = LoggerFactory.getLogger(MainApp.class);

    @Override
    public void start(Stage primaryStage) throws Exception {
        //RESIURCE BUUNDLE JAKO 2 argument
        stage = primaryStage;

        Locale locale = new Locale("en", "EN");
        ResourceBundle resourceBundle = ResourceBundle.getBundle("LanguageBundle", locale);
        Parent root = FXMLLoader.load(getClass().getResource("/Scene.fxml"), resourceBundle);
        Scene scene = new Scene(root, 650, 400);
        scene.getStylesheets().add("sudoku.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

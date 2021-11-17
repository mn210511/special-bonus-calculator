package com.mn210511.specialbonuscalculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("bonus-calculator.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 1200, 1000);
        scene.getStylesheets().add((Main.class.getResource("style.css").toExternalForm()));
        stage.setTitle("special-bonus Calculator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();



    }
}
package com.bibliotecavirtual;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/ventanas/Login.fxml"));
        Scene scene = new Scene(root);
        //scene.getStylesheets().add(getClass().getResource());
        stage.setTitle("Biblioteca Virtual");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

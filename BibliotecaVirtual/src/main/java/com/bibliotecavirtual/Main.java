package com.bibliotecavirtual;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Main extends Application{
    @Override
    public void start(Stage stage){
                Label label = new Label("Biblioteca Virtual");
                Scene scene = new Scene(label, 400, 300);
                stage.setScene(scene);
                stage.setTitle("Biblioteca Virtual");
                stage.show();
    }

    public static void main(String[] args){
        launch();
    }

}

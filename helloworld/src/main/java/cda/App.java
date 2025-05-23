package cda;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    // Toutes les sous-class de App doivent avoir une méthode start. start est le point d'entrée de l'application javafx.
    // start prend un seul parametre de type Stage
    public void start(Stage stage) { 
       

        Label label = new Label("Hello World, ah mais c'est la que ça ecrit !!!");

        VBox vBox = new VBox();

        Label label2 = new Label("Vive JavaFX");

        vBox.getChildren().add(label);
        vBox.getChildren().add(label2);

        vBox.setAlignment(Pos.CENTER);

        var scene = new Scene(vBox, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}

 /*
         * var => permet de déduire le type du code, permet au compilateur
         * de faire de l inférence de type
         * var javaVersion = SystemInfo.javaVersion();
         * var javafxVersion = SystemInfo.javafxVersion();
         */
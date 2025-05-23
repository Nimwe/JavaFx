package cda;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * VBox
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) {

        // Titre de la fenêtre
        primaryStage.setTitle("Fenêtre - VBox");

        // Texte dans la fenêtre
        Label label = new Label("Bonjour ");
        Label label2 = new Label(" le ");
        Label label3 = new Label(" monde");
        // Bouton dans la fenêtre
        Button button = new Button("Quitter");

        // Création d'une VBox pour alignement vertical des parametres label et button
        VBox vbox = new VBox(label, label2, label3, button);
        vbox.setAlignment(Pos.CENTER);

        // Ajout et affichage de la scene
        Scene scene = new Scene(vbox, 200, 100);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
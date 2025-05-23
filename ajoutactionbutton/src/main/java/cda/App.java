package cda;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
        Label label = new Label("Bonjour le monde !");

        // Bouton dans la fenêtre
        Button button = new Button("Quitter");
        // Rendre le bouton actif - Changement de label au clic
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                label.setText("Hello World !");
            }
        });
        /*
         * Avec une Lambda pour un code plus court
         * button.setOnAction(event -> {
         * System.out.println("Bouton cliqué (avec lambda) !");
         * });
         */

        // Création d'une VBox pour alignement vertical des parametres label et button
        VBox vbox = new VBox(label, button);
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
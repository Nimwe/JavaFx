package cda;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/* Questions 

- Comment définir les dimensions de Stage ?

*/

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) {

        // Titre de la fenêtre
        primaryStage.setTitle("First App");

        // Label affiche du texte (Utiliser les types dont on a besoin suivant projet)
        Label label = new Label("Hello World First App!");

        // Scene affiche ce qu'il y a dans Stage - Sans Scene, Stage n'affiche rien
        Scene scene = new Scene(label, 400, 200); // Label = élément racine, 400 =largeur de la scene, 200 = hauteur de
                                                  // la scene
        // Ajout de la scène à la fenêtre avec setScene
        primaryStage.setScene(scene);

        // Permet l'affichage de la fênetre - A mettre à la fin
        primaryStage.show();

    }

    public static void main(String[] args) {
        Application.launch(args); // => Ecriture plus conventionnelle de launch
    }

}

/*
 * Notes
 * 
 * - Stage = fenêtre
 * 
 * - primaryStage => Convention pour indiquer qe c'est la fenetre principale de
 * l'app
 * - Application.launch(args) & launch()
 * => Le 1er prend des arguments ce qui va être utile si on veut traiter des
 * options au lancement - Méthode appelée via la classe - C'est l'écriture
 * conventionnelle
 * => Le second n'envoi pas d'arguments a JavaFx, c'est une méthode static
 * héritée de la classe Application
 * 
 * Résumé visuel (analogie théâtre) :
 * 
 * Stage = Scène de théâtre (la fenêtre principale)
 * Scene = Acte ou décor spécifique affiché
 * Label, Button, etc. = Éléments de décor (acteurs, objets...)
 * 
 * 
 * 
 */
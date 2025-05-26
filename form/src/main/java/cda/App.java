package cda;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * VBox
 * 👌 code propre, variables bien nommées, good.
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) {

        // Titre
        primaryStage.setTitle("Formulaire");

        // Mise en page

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(20);
        gridPane.setVgap(20);

        // Labels
        Label labelEntree = new Label("Entrée utilisateur");
        Label labelCopie = new Label("Copie de l'entrée");

        VBox vboxLabels = new VBox(labelEntree, labelCopie);
        vboxLabels.setAlignment(Pos.CENTER_LEFT);

        // Zones de text
        TextField textFieldEntree = new TextField("Saisissez un texte");
        textFieldEntree.setPrefColumnCount(25);
        TextField textFieldCopie = new TextField("Saisissez un texte");
        textFieldCopie.setPrefColumnCount(25);
        textFieldCopie.setEditable(false);
        textFieldCopie.setDisable(true);

        VBox vboxTextFields = new VBox(textFieldEntree, textFieldCopie);
        vboxTextFields.setAlignment(Pos.CENTER);

        // Boutons
        double buttonWidth = 100;

        Button buttonCopie = new Button("Recopier");
        buttonCopie.setPrefWidth(buttonWidth);
        buttonCopie.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String saisie = textFieldEntree.getText();
                textFieldCopie.setText(saisie);
            }
        });
        Button buttonEffacer = new Button("Effacer");
        buttonEffacer.setPrefWidth(buttonWidth);
        buttonEffacer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textFieldEntree.clear();
                textFieldCopie.clear();
            }
        });
        Button buttonQuit = new Button("Quitter");
        buttonQuit.setPrefWidth(buttonWidth);
        buttonQuit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });

        VBox vBoxButton = new VBox(10, buttonCopie, buttonEffacer, buttonQuit);
        vBoxButton.setAlignment(Pos.CENTER_RIGHT);

        // Ajout des VBox a GridPane
        // Colonnes
        gridPane.add(vboxLabels, 0, 0);
        gridPane.add(vboxTextFields, 1, 0);
        gridPane.add(vBoxButton, 2, 0);
        // Lignes
        GridPane.setRowSpan(vboxLabels, 2);
        GridPane.setRowSpan(vboxTextFields, 2);
        GridPane.setRowSpan(vBoxButton, 2);

        // Ajout et affichage de la scene
        Scene scene = new Scene(gridPane, 500, 150);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
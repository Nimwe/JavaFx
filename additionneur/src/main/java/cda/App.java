package cda;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private TextArea textArea; // Protection de la zone de texte

    @Override
    public void start(Stage primaryStage) {

        // Titre
        primaryStage.setTitle("Additionneur");

        // TextArea protégé en écriture
        textArea = new TextArea();
        textArea.setEditable(false); // Texte non éditable donc impossible d'écrire
        textArea.setWrapText(true); // Renvoi à la ligne automatique pour ne pas scroller vers la droite

        // ScrollPane
        ScrollPane scrollPane = new ScrollPane(textArea);
        scrollPane.setFitToWidth(true); // Permet au contenu du TextArea d'occuper toute la largeur de ScrollPane

        // Mise en page
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        // Boutons
        // Boutons chiffres sur 2 lignes avec boucles for pour ne pas avoir 10 boutons
        int number = 0;

        // 2 lignes
        for (int row = 0; row < 2; row++) {
            // 5 colonnes
            for (int col = 0; col < 5; col++) {

                int current = number;

                // Création du bouton avec le chiffre
                Button button = new Button(String.valueOf(current));
                // Dimensions du bouton
                button.setPrefSize(40, 50);
                // Gestionnaire d'événements
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        // Appel de la méthode appendNumber
                        appendNumber(current);
                    }
                });
                // Ajout des boutons dans GridPane
                gridPane.add(button, col, row);
                number++;
            }
        }

        // Bouton Calculer
        // Création du bouton
        Button buttonCalculer = new Button("Calculer");
        // Gestionnaire d'évenements
        buttonCalculer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Appel de la méthode Calculer
                calculer(event);
            }
        });

        // Bouton Vider
        // Création du bouton
        Button buttonVider = new Button("Vider");
        // Gestionnaire d'évenements
        buttonVider.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Appel de la méthode Vider(avec confirmation)
                viderAvecConfirmation(event);
            }
        });

        // HBox boutons
        HBox buttonBox = new HBox(10, buttonCalculer, buttonVider);
        buttonBox.setAlignment(Pos.CENTER);

        // Mise en page stage
        VBox stage = new VBox(15, scrollPane, gridPane, buttonBox);
        stage.setPadding(new Insets(15));

        // Création et affichage de la scène
        Scene scene = new Scene(stage, 500, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Méthodes
    // Ajout du chiffre cliqué suivi de +
    private void appendNumber(int current) {
        textArea.appendText(current + " + ");
    }

    // Calculer
    private void calculer(ActionEvent event) {
        String texte = textArea.getText().trim(); // trim = Retrait des espaces
        // Retrait du + si il est en fin de texte
        if (texte.endsWith(" + ")) {
            texte = texte.substring(0, texte.length() - 1).trim(); // substring = garder une partie de texte entre 2
                                                                   // index
        }
        // Découpe de la String pour récupérer les chiffres
        String[] chiffres = texte.split("\\+");

        int somme = 0;

        // Addition des chiffres
        for (String chiffre : chiffres) {
            chiffre = chiffre.trim();
            if (!chiffre.equals("")) {
                int nombre = Integer.parseInt(chiffre); // Convertir une string en int
                somme += nombre;
            }
        }
        // Ajout du résultat à la fin du textArea
        textArea.appendText(" = " + somme + " + ");
    }

    // Vider avec un label parqu'avec Alert la fenetre en me plaisait pas
    // Demande de confirmation avant de vider le champ
    private void viderAvecConfirmation(ActionEvent event) {

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Confirmation");

        Label contentLabel = new Label("Êtes vous sur de vouloir effacer ?");
        contentLabel.setAlignment(Pos.CENTER);
        dialog.getDialogPane().setContent(contentLabel);

        ButtonType okButton = new ButtonType("Ok");
        dialog.getDialogPane().getButtonTypes().setAll(okButton, ButtonType.CANCEL);

        // Affiche la boîte et attend la réponse
        dialog.showAndWait().ifPresent(response -> {
            if (response == okButton) {
                // Efface tout si OK
                textArea.clear();
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}

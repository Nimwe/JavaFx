package cda;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
// import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    // Variables générales
    private CheckBox casseCara;
    private RadioButton rbMaj;
    private RadioButton rbMin;
    private TextField textFieldEntree;
    private TextField textFieldCopie;
    private ToggleGroup toggleFond;
    private Slider slRouge;
    private Slider slVert;
    private Slider slBleu;

    @Override
    public void start(Stage primaryStage) {
        // Titre
        primaryStage.setTitle("Checkbox - RadioButton");

        // Labels et recopie
        // Mise en page
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(20);
        gridPane.setVgap(20);

        // Labels
        Label labelTitre = new Label("Saisissez votre texte");
        Label labelParam = new Label("Parametres des Labels");
        Label labelCoulFond = new Label("Couleurs de fond");
        Label labelCoulCara = new Label("Couleur des caractères");
        Label labelRouge = new Label("Rouge");
        Label labelVert = new Label("Vert");
        Label labelBleu = new Label("Bleu");
        Label labelCasse = new Label("Casse du texte");

        // Zones de texte
        textFieldEntree = new TextField();
        textFieldCopie = new TextField();
        textFieldCopie.setEditable(false);
        // Texte recopié caractères par caractère dans le label copie
        textFieldCopie.textProperty();
        // Modification de la casse
        textFieldEntree.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                casseCopie();
            }
        });

        // Checkbox
        CheckBox coulFond = new CheckBox("Couleurs de fond");
        CheckBox coulCara = new CheckBox("Couleurs caractères");
        casseCara = new CheckBox("Majuscules - Miniscules");

        // Boutons radio
        // Boutons Radio couleurs de fond
        RadioButton rbRouge = new RadioButton("Rouge");
        RadioButton rbVert = new RadioButton("Vert");
        RadioButton rbBleu = new RadioButton("Bleu");
        // Boutons Radio casse
        rbMaj = new RadioButton("Majuscule");
        rbMin = new RadioButton("Minuscule");

        // Toggle
        // Toggle couleurs de fond
        toggleFond = new ToggleGroup();
        toggleFond.getSelectedToggle();
        rbRouge.setToggleGroup(toggleFond);
        rbVert.setToggleGroup(toggleFond);
        rbBleu.setToggleGroup(toggleFond);
        // Toggle casse
        ToggleGroup toggleCasse = new ToggleGroup();
        rbMaj.setToggleGroup(toggleCasse);
        rbMin.setToggleGroup(toggleCasse);

        // Slider
        slRouge = new Slider(0, 255, 0);
        slVert = new Slider(0, 255, 0);
        slBleu = new Slider(0, 255, 0);

        // Application de la couleur à chaque changements
        ChangeListener<Number> listenerSlider = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> obs, Number oldVal, Number newVal) {
                String style = nvxCss();
                textFieldCopie.setStyle(style);
            }
        };
        // Ajout de l'écouteur aux sliders
        slRouge.valueProperty().addListener(listenerSlider);
        slVert.valueProperty().addListener(listenerSlider);
        slBleu.valueProperty().addListener(listenerSlider);

        // VBox
        // Vbox TextField
        VBox vboxSaisie = new VBox(10, labelTitre, textFieldEntree, textFieldCopie);
        vboxSaisie.setAlignment(Pos.TOP_LEFT);
        // Vbox tableau parametres
        VBox vboxParam = new VBox(10, labelParam, coulFond, coulCara, casseCara);
        vboxParam.setAlignment(Pos.TOP_LEFT);
        // Vbox parametres du fond
        VBox vboxCoulFond = new VBox(10, labelCoulFond, rbRouge, rbVert, rbBleu);
        vboxCoulFond.setPadding(new Insets(10));
        // Vbox couleur des caractères
        VBox vboxCoulCara = new VBox(10, labelCoulCara, labelRouge, slRouge, labelVert, slVert, labelBleu, slBleu);
        vboxCoulCara.setPadding(new Insets(10));
        // Vbox casse
        VBox vboxCasse = new VBox(10, labelCasse, rbMaj, rbMin);
        vboxCasse.setPadding(new Insets(10));

        // TitledPane -> Panneau dont le titre peut-être ouvert et fermé
        // TitledPane Paramètres
        TitledPane tpParam = new TitledPane("Paramètres des Labels", vboxParam);
        tpParam.setCollapsible(false);
        // TitledPane CoulFond
        TitledPane tpCoulFond = new TitledPane("Couleur de fond", vboxCoulFond);
        tpCoulFond.setCollapsible(false);
        tpCoulFond.setVisible(false); // Cacher visuellement mais le layout reserve l'espace
        tpCoulFond.setManaged(false); // Empêche le layout de réserver l'espace
        // TitledPane CoulCara
        TitledPane tpCoulCara = new TitledPane("Couleur de Caractères", vboxCoulCara);
        tpCoulCara.setCollapsible(false);
        tpCoulCara.setVisible(false); // Cacher visuellement mais le layout reserve l'espace
        tpCoulCara.setManaged(false); // Empêche le layout de réserver l'espace
        // TitledPane Casse
        TitledPane tpCasse = new TitledPane("Casse", vboxCasse);
        tpCasse.setCollapsible(false);
        tpCasse.setVisible(false); // Cacher visuellement mais le layout reserve l'espace
        tpCasse.setManaged(false); // Empêche le layout de réserver l'espace

        // GridPane haut de scène
        GridPane gridPaneEntete = new GridPane();
        gridPaneEntete.setPadding(new Insets(50, 20, 10, 20));
        gridPaneEntete.setHgap(20);
        gridPaneEntete.setVgap(20);
        // GridPane Labels
        ColumnConstraints colLabels = new ColumnConstraints();
        colLabels.setPercentWidth(50); // Selectionner la moitiée gauche
        // GridPane Param
        ColumnConstraints colParam = new ColumnConstraints();
        colParam.setPercentWidth(50); // Sélectionner la moitiée droite
        // Ajout des nouveaux GridPanes dans GridPaneEntete
        gridPaneEntete.getColumnConstraints().addAll(colLabels, colParam);
        // Placement des contenus dans leurs colonnes dédiées
        gridPaneEntete.add(vboxSaisie, 0, 0); // Colonne 0
        gridPaneEntete.add(tpParam, 1, 0); // Colonne 1
        // Alignement de Param
        GridPane.setHalignment(tpParam, HPos.CENTER); // Alignement horizontal
        GridPane.setValignment(tpParam, VPos.TOP); // Alignement haut

        // HBox TitledPane
        HBox hboxTTPane = new HBox(20, tpCoulFond, tpCoulCara, tpCasse);
        hboxTTPane.setAlignment(Pos.TOP_CENTER);
        hboxTTPane.setPadding(new Insets(10));

        // Vbox bas de scène
        VBox vboxPiedPage = new VBox(10);
        vboxPiedPage.setPadding(new Insets(20));
        vboxPiedPage.setAlignment(Pos.TOP_CENTER);
        vboxPiedPage.getChildren().addAll(hboxTTPane);

        // Activation des groupes
        // Couleurs de fond
        coulFond.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                vboxCoulFond.setVisible(coulFond.isSelected());
            }
        });
        // Couleurs caractères
        coulCara.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                vboxCoulCara.setVisible(coulCara.isSelected());
            }

        });
        // Modification de la casse
        casseCara.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                vboxCasse.setVisible(casseCara.isSelected());
            }
        });

        // Activation des TitledPane
        // Couleur de fond
        coulFond.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean selected = coulFond.isSelected();
                tpCoulFond.setVisible(selected);
                tpCoulFond.setManaged(selected);
            }
        });
        // Couleur caractères
        coulCara.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean selected = coulCara.isSelected();
                tpCoulCara.setVisible(selected);
                tpCoulCara.setManaged(selected);
            }
        });
        // Casse
        casseCara.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean selected = casseCara.isSelected();
                tpCasse.setVisible(selected);
                tpCasse.setManaged(selected);
            }
        });
        // Changement de couleurs du fond à la selection du bouton radio correspondant
        // Rouge
        rbRouge.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textFieldCopie.setStyle("-fx-background-color: red;");
            }
        });
        // Vert
        rbVert.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textFieldCopie.setStyle("-fx-background-color: green;");
            }
        });
        // Bleu
        rbBleu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textFieldCopie.setStyle("-fx-background-color: blue;");
            }
        });
        // Changement de la casse
        rbMaj.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                casseCopie();
            }
        });

        rbMin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                casseCopie();
            }
        });

        casseCara.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean selected = casseCara.isSelected();
                tpCasse.setVisible(selected);
                tpCasse.setManaged(selected);
                casseCopie(); // Actualise aussi le texte immédiatement
            }
        });

        // Création et affichage de la scène
        VBox vboxRoot = new VBox(30, gridPaneEntete, vboxPiedPage);
        Scene scene = new Scene(vboxRoot, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Méthodes
    // Changement de couleur du texte
    public String couleurRGB(int r, int g, int b) {
        return "RGB(" + r + ", " + g + ", " + b + ")";
    }

    // Modification de la casse
    private void casseCopie() {
        String entree = textFieldEntree.getText();
        String updatedCopie = entree;

        if (casseCara.isSelected()) {
            if (rbMaj.isSelected()) {
                updatedCopie = entree.toUpperCase();
            } else if (rbMin.isSelected()) {
                updatedCopie = entree.toLowerCase();
            }
        }
        textFieldCopie.setText(updatedCopie);
    }

    // Changement css
    public String nvxCss() {

        String nvxCss = ""; // Initialisation de variable nvxCss vide pour ajouter les éléments au fur et a
                            // mesure

        RadioButton backgroundColorRB = (RadioButton) toggleFond.getSelectedToggle(); // Récupération de la couleur BG

        if (backgroundColorRB != null) {

            String backgroundColor = backgroundColorRB.getText(); // Traduction des couleurs pour le CSS

            if (backgroundColor == "Rouge") {
                backgroundColor = "red";
            }
            if (backgroundColor == "Vert") {
                backgroundColor = "green";
            }
            if (backgroundColor == "Bleu") {
                backgroundColor = "blue";
            }
            // mise a jour variable nvxCss avec BG une fois verif null ok
            nvxCss = "-fx-background-color : " + backgroundColor + ";";
        }
        // mise à jour variable nvxCss avec les couleurs de texte
        int textColorR = (int) slRouge.getValue();
        int textColorG = (int) slVert.getValue();
        int textColorB = (int) slBleu.getValue();
        String textColor = couleurRGB(textColorR, textColorG, textColorB);

        // mise a jour variable nvxCss avec les couleurs texte
        nvxCss += "-fx-text-inner-color : " + textColor + ";";

        return nvxCss;

    }

    public static void main(String[] args) {
        launch();
    }

}
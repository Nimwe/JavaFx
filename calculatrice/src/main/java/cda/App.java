package cda;

import java.util.Stack;

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
        primaryStage.setTitle("Calculatrice"); // Parce que c'est trop triste de juste additionner
        
                                // Attention ne gere pas les priorité des opérandes //

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
        // Boutons chiffres et opérateurs
        String[][] buttons = {
                { "7", "8", "9", "+" },
                { "4", "5", "6", "-" },
                { "1", "2", "3", "x" },
                { " ", "0", " ", "/" }
        };

        // Ajout des boutons dans la gridpane sur 4 lignes et 4 colonnes
        for (int row = 0; row < buttons.length; row++) {
            for (int col = 0; col < buttons[row].length; col++) {
                String buttonText = buttons[row][col];
                if (!buttonText.isEmpty()) {
                    Button button = new Button(buttonText);
                    button.setPrefSize(50, 50);

                    // Gestionnaire d'événements
                    button.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            // Appel de la méthode
                            handleButtonClick(buttonText);
                        }
                    });

                    // Ajout des boutons dans GridPane
                    gridPane.add(button, col, row);

                }
            }
        }

        // Création bouton Calculer
        Button buttonCalculer = new Button("Calculer");
        buttonCalculer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                calculer();
            }
        });

        // Création bouton Vider
        Button buttonVider = new Button("Vider");
        buttonVider.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
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
        Scene scene = new Scene(stage, 350, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Méthodes

    // Méthode pour gérer les click
    private void handleButtonClick(String buttonText) {
        if ("Vider".equals(buttonText)) {
            textArea.clear();
        } else if ("Calculer".equals(buttonText)) {
            calculer();
        } else {
            textArea.appendText(buttonText);
        }
    }

    // Calculer
    private void calculer() {
        String texte = textArea.getText().trim(); // trim = Retrait des espaces
        // Retrait du + si il est en fin de texte
        if (!texte.isEmpty()) {
            try {
                double result = eval(texte);
                textArea.setText(String.valueOf(result));
            } catch (Exception excep) {
                textArea.setText("Erreur");
            }
        }
    }

    // Méthode pour évaluer une expression arithmétique simple
    private double eval(String expression) {
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        int i = 0;
        while (i < expression.length()) {
            char current = expression.charAt(i);

            if (Character.isDigit(current)) {
                // Si c'est un chiffre, on le récupère et on le pousse dans la pile
                StringBuilder num = new StringBuilder();
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    num.append(expression.charAt(i));
                    i++;
                }
                numbers.push(Double.parseDouble(num.toString()));
            } else if (current == '+' || current == '-' || current == 'x' || current == '/') {
                // Si c'est un opérateur, on le pousse dans la pile des opérateurs
                while (!operators.isEmpty() && hasPrecedence(current, operators.peek())) {
                    numbers.push(applyOp(operators.pop(), numbers.pop(), numbers.pop()));
                }
                operators.push(current);
                i++;
            } else {
                i++;
            }
        }

        // Effectuer les opérations restantes
        while (!operators.isEmpty()) {
            numbers.push(applyOp(operators.pop(), numbers.pop(), numbers.pop()));
        }

        // Le résultat est le dernier élément de la pile
        return numbers.pop();
    }

    // Appliquer un opérateur sur deux nombres
    private double applyOp(char op, double b, double a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case 'x':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new UnsupportedOperationException("Division par zéro");
                }
                return a / b;
        }
        return 0;
    }

    // Déterminer si un opérateur a une priorité plus faible
    private boolean hasPrecedence(char currentOp, char prevOp) {
        if ((currentOp == '+' || currentOp == '-') && (prevOp == 'x' || prevOp == '/')) {
            return false;
        }
        return true;
    }

    // Vider avec un label parce qu'avec Alert la fenetre en me plaisait pas
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

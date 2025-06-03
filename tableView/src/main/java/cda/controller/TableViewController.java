package cda.controller;

import java.io.IOException;

import cda.App;
import javafx.fxml.FXML;

public class TableViewController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}

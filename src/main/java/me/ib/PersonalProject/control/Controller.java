package me.ib.PersonalProject.control;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public final class Controller {

    @FXML
    public Pane bgPane;

    @FXML
    public void initialize() {
        new StarryBackground(bgPane, 400, 150, 50, 95);
    }
}

package me.ib.PersonalProject.control;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;

public final class TitleOptionsController {

    @FXML
    public Pane bgPane;

    @FXML
    public Slider slider;

    @FXML
    public void initialize() {
        new StarryBackground(bgPane);
    }
}

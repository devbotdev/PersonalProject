package me.ib.PersonalProject.control;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import me.ib.PersonalProject.util.Utility;

public final class TitleOptionsController {

    @FXML
    public Pane bgPane;

    @FXML
    public Slider slider;

    @FXML
    public TextArea credits;

    @FXML
    public Button exitCreditsButton, creditsButton;

    @FXML
    public void initialize() {
        setCreditsVisibility(false);
        new StarryBackground(bgPane);

        creditsButton.setOnAction(event -> setCreditsVisibility(true));
        exitCreditsButton.setOnAction(event -> {
            setCreditsVisibility(false);
            Utility.sound.stopCreditsMusic();
        });

        credits.setText(Utility.extractCredits());

        slider.valueProperty().addListener((obs, oldVal, newVal) -> Utility.sound.setVolume(newVal.floatValue()));
    }

    private void setCreditsVisibility(boolean state) {
        if (state) Utility.sound.startCreditsMusic();

        exitCreditsButton.setVisible(state);
        credits.setVisible(state);
    }
}

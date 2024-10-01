package me.ib.PersonalProject.util;

import javafx.scene.image.Image;
import me.ib.PersonalProject.Main;

import java.io.InputStream;
import java.util.Objects;

public abstract class Utility {
    public static State state;
    public static final String optionsIcon = "optionsIcon.png";

    //Should be png icon
    public static Image getResourceAsImage(String fileName) {
        return new Image(Objects.requireNonNull(Main.class.getClassLoader().getResourceAsStream("me.ib/" + fileName)));
    }
}

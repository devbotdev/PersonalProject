package me.ib.PersonalProject.util;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import me.ib.PersonalProject.Main;

import java.util.Objects;

public abstract class Utility {
    public static State state;
    public static final String optionsIcon = "optionsIcon.png";
    public final static String directory = "me.ib/";
    //Should be png icon
    public static Image getResourceAsImage(String fileName) {
        return new Image(Objects.requireNonNull(Main.class.getClassLoader().getResourceAsStream("me.ib/" + fileName)));
    }

    public static double getStageScale(Stage stage) {
        return ((stage.getWidth() / 1920) + (stage.getHeight() / 1080)) / 2;
    }
    public static double getStageXScale(Stage stage) {
        return stage.getWidth() / 1920;
    }
    public static double getStageYScale(Stage stage) {
        return stage.getHeight() / 1080;
    }

    public static long convertToNum(double i, String s) {
        if (Objects.equals(s, "b") || Objects.equals(s, "billion")) {
            return (long) (i * 1_000_000_000L);
        } else if (Objects.equals(s, "m") ||  Objects.equals(s, "million")) {
            return (long) (i * 1_000_000L);
        } else if (s.isEmpty()){
            return 0;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public String convertNumberToString(long number) {
        if (number < 1_000_000) {
            return String.valueOf(number);
        } else if (number < 1_000_000_000) {
            return formatNumber(number, 1_000_000, "million");
        } else if (number < 1_000_000_000_000L) {
            return formatNumber(number, 1_000_000_000, "billion");
        } else if (number < 1_000_000_000_000_000L) {
            return formatNumber(number, 1_000_000_000_000L, "trillion");
        } else {
            throw new StackOverflowError("Number too Large");
        }
    }

    private String formatNumber(long number, long divisor, String unit) {
        double value = (double) number / divisor;
        return String.format("%.0f %s", value, unit);
    }
}

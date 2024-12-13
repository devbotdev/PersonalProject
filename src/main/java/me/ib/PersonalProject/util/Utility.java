package me.ib.PersonalProject.util;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import me.ib.PersonalProject.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

public abstract class Utility {
    public static final String optionsIcon = "optionsIcon.png";
    public static State state;
    public static Sound sound;

    //Should be png icon
    public static Image getResourceAsImage(String fileName) {
        URL resourceUrl = Main.class.getClassLoader().getResource("me.ib/" + fileName);
        if (resourceUrl == null) {
            throw new RuntimeException("Resource not found: ");
        }
        return new Image(resourceUrl.toString());
    }

    public static String formatWithCommas(long number) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        return numberFormat.format(number);
    }

    public static String extractText(String id) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Main.class.getClassLoader().getResourceAsStream("me.ib/info.txt"))))) {
            String line;
            boolean isTargetId = false;
            StringBuilder result = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.startsWith("Id:")) {
                    if (line.equals("Id: " + id)) {
                        isTargetId = true;
                    } else if (isTargetId) {
                        break;
                    }
                } else if (isTargetId) {
                    result.append(line).append("\n");
                }
            }
            return isTargetId ? result.toString().trim() : null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        } else if (Objects.equals(s, "m") || Objects.equals(s, "million")) {
            return (long) (i * 1_000_000L);
        } else if (s.isEmpty()) {
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

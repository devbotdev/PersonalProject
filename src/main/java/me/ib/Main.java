package me.ib;

import javafx.application.Application;
import me.ib.panels.ProjectFrame;

public class Main {

    public static void main(String[] args) throws Exception {
        ProjectFrame frame = new ProjectFrame();

        Application.launch(frame.getClass(), args);
    }
}
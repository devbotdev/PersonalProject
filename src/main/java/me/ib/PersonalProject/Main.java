package me.ib.PersonalProject;

import javafx.application.Application;
import me.ib.PersonalProject.panels.ProjectFrame;
import me.ib.PersonalProject.util.Sound;

import static me.ib.PersonalProject.util.Utility.sound;

public class Main {
    public static void main(String[] args) {
        sound = new Sound();

        sound.setFile(0, 0);
        sound.play();

        Application.launch(ProjectFrame.class, args);
    }
}
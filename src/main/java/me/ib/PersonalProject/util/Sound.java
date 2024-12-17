package me.ib.PersonalProject.util;

import me.ib.PersonalProject.Main;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Sound {

    public static byte currentTrack = 0;
    private static float musicVolume;
    private final URL[] soundUrlMusic = new URL[9];
    private Clip clipMusic;
    private AudioInputStream aisMusic;
    private FloatControl gainControlMusic;
    private long clipTimeMusic;
    private boolean isPaused = false;

    public Sound() {
        musicVolume = 0;

        soundUrlMusic[0] = Main.class.getClassLoader().getResource("me.ib/soundtrack/0.wav");
        soundUrlMusic[1] = Main.class.getClassLoader().getResource("me.ib/soundtrack/1.wav");
        soundUrlMusic[2] = Main.class.getClassLoader().getResource("me.ib/soundtrack/2.wav");
        soundUrlMusic[3] = Main.class.getClassLoader().getResource("me.ib/soundtrack/3.wav");
        soundUrlMusic[4] = Main.class.getClassLoader().getResource("me.ib/soundtrack/4.wav");
        soundUrlMusic[5] = Main.class.getClassLoader().getResource("me.ib/soundtrack/5.wav");
        soundUrlMusic[6] = Main.class.getClassLoader().getResource("me.ib/soundtrack/6.wav");
        soundUrlMusic[7] = Main.class.getClassLoader().getResource("me.ib/soundtrack/7.wav");
        soundUrlMusic[8] = Main.class.getClassLoader().getResource("me.ib/soundtrack/credits.wav");
    }

    public void setFile(int i, float volume) {
        if (i >= soundUrlMusic.length) return;
        try {
            aisMusic = AudioSystem.getAudioInputStream(soundUrlMusic[i]);
            clipMusic = AudioSystem.getClip();
            open();
            gainControlMusic = (FloatControl) clipMusic.getControl(FloatControl.Type.MASTER_GAIN);
            gainControlMusic.setValue(volume);

            clipMusic.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clipMusic.close();
                    if (!clipMusic.isOpen()) {
                        open();
                    }
                    if (!isPaused) {
                        playNextTrack();
                    }
                }
            });
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    private void playNextTrack() {
        if (currentTrack == soundUrlMusic.length - 1)
            currentTrack = 0;
        else
            currentTrack += 1;

        setFile(currentTrack, musicVolume);
        play();
    }


    private void open() {
        try {
            clipMusic.open(aisMusic);
        } catch (LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void play() {
        if (clipMusic != null) {
            clipMusic.start();
            isPaused = false;
        }
    }

    public void pause() {
        if (clipMusic != null) {
            isPaused = true;
            clipTimeMusic = clipMusic.getMicrosecondPosition();
            clipMusic.stop();
        }
    }

    public void resume() {
        if (clipMusic != null) {
            clipMusic.setMicrosecondPosition(clipTimeMusic);
            clipMusic.start();
            isPaused = false;
        }
    }

    public void startCreditsMusic() {
        pause();
        setFile(8, musicVolume);
        play();
    }

    public void stopCreditsMusic() {
        clipMusic.stop();
        setFile(currentTrack, musicVolume);
        resume();
        isPaused = false;
    }

    public void setVolume(float value) {
        if (value <= -40.00f) {
            musicVolume = -80;
        } else {
            musicVolume = value;
        }
        if (gainControlMusic != null) gainControlMusic.setValue(musicVolume);
    }

    public float getVolume() {
        return musicVolume;
    }
}
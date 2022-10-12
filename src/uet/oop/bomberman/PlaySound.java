package uet.oop.bomberman;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.IOException;

public class PlaySound {
    AudioInputStream audioInputStream;
    Clip clip;
    public static void play(String soundFile, int times) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundFile).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            if(times > 0) {
                clip.loop(times);
            }
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    public void playTheme(String soundFile) {
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(soundFile).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    public void stopTheme() {
        try {
            audioInputStream.close();
            clip.close();
        }
        catch (IOException ioException) {
            System.out.println("audio or clip still null!");
        }
    }
}

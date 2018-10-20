package media;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SoundSnake implements Runnable{
    public static String path = "C:\\Repository\\snakeGame\\src\\main\\resourses\\Sound_Shot.mp3";
    public static Player playMP3;
    public static String pathSound = path;
    FileInputStream fis;

    public SoundSnake() {
        try {
            fis = new FileInputStream(pathSound);
            playMP3 = new Player(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }

    public static void playSound(){
        try {
            playMP3.play();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }
    public static void muteSound() {
        pathSound = "C:\\Repository\\snakeGame\\src\\main\\resourses\\Sound_Shot.mp3";
    }

    public static void onSound() {
        pathSound = path;
    }

    @Override
    public void run() {
        playSound();
    }
}

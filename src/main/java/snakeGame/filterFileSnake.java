package snakeGame;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class filterFileSnake extends FileFilter {

    @Override
    public boolean accept(File pathname) {
        String filename = pathname.getName();
        if (pathname.isDirectory()) {
            return true;

        } else if (filename.endsWith("snk")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getDescription() {
        return "Snake saves";
    }
}
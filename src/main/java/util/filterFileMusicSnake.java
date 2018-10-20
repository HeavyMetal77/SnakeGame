package util;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class filterFileMusicSnake extends FileFilter {

    @Override
    public boolean accept(File pathname) {
        String filename = pathname.getName();
        if (pathname.isDirectory()) {
            return true;

        } else if (filename.endsWith("mp3")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getDescription() {
        return "mp3";
    }
}

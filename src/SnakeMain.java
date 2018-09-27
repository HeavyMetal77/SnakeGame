import com.jtattoo.plaf.smart.SmartLookAndFeel;
import javax.swing.*;

public class SnakeMain {
    protected SnakePanel snakePanel;
    protected static SnakeLogicGame logicGame;
    public static void main(String[] args) {


        new SnakeMain();
    }

    public SnakeMain() {
        try {
            UIManager.setLookAndFeel(new SmartLookAndFeel());
            JFrame.setDefaultLookAndFeelDecorated(true);
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        snakePanel = new SnakePanel();
        logicGame = new SnakeLogicGame();
    }
}

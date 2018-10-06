import com.jtattoo.plaf.smart.SmartLookAndFeel;
import javax.swing.*;

public class SnakeMain {
    protected SnakePanel snakePanel;
    protected static SnakeLogicGame logicGame;
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new SmartLookAndFeel());
            JFrame.setDefaultLookAndFeelDecorated(true);
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SnakeMain();
            }
        });
    }

    public SnakeMain() {
        snakePanel = new SnakePanel();
        logicGame = new SnakeLogicGame();
    }
}

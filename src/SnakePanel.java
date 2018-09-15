import javax.swing.*;
import java.awt.*;

public class SnakePanel extends JPanel {
    private final int WIDTH = 600;
    private JMenuBar menuBar;
    private Color colorPanel = Color.yellow;

    public SnakePanel() throws HeadlessException {
        JFrame frame = new JFrame();
        frame.setTitle("Snake");
        frame.setMinimumSize(new Dimension(WIDTH, WIDTH));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        initMenu();
        setBackground(colorPanel);
        frame.getContentPane().add(this);
        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
        frame.pack();
        repaint();
    }

    public void initMenu(){
        menuBar = new JMenuBar();
        JMenu menuGame = new JMenu("Game");
        JMenu menuSettings = new JMenu("Settings");
        JMenu menuPause = new JMenu("Pause");
        JMenuItem menuItemNewGame = new JMenuItem("New Game");
        JMenuItem menuItemOpenGame = new JMenuItem("Open Game");
        JMenuItem menuItemSaveGame = new JMenuItem("Save Game");
        JMenuItem menuItemPauseGame = new JMenuItem("Pause Game");
        JMenuItem menuItemConfigStartGame = new JMenuItem("ConfigStartGame");
        JMenuItem menuItemSkin = new JMenuItem("Skin");
        menuGame.add(menuItemNewGame);
        menuGame.add(menuItemOpenGame);
        menuGame.add(menuItemSaveGame);
        menuGame.add(menuItemPauseGame);
        menuSettings.add(menuItemConfigStartGame);
        menuSettings.add(menuItemSkin);
        menuBar.add(menuGame);
        menuBar.add(menuSettings);
        menuBar.add(menuPause);
    }


}

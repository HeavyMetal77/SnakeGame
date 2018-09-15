import javax.swing.*;
import java.awt.*;

public class SnakeFrame extends JFrame {
    private final int WIDTH = 600;
    private JPanel panel;
    private JMenuBar menuBar;
    private Color colorPanel = Color.yellow;

    public SnakeFrame() throws HeadlessException {
        setTitle("Snake");
        setMinimumSize(new Dimension(WIDTH, WIDTH));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initMenu();
        initPanel();
        getContentPane().add(panel);
        setJMenuBar(menuBar);
        setVisible(true);
        pack();
        repaint();
    }

    private void initPanel() {
        panel = new JPanel();
        panel.setBackground(colorPanel);
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

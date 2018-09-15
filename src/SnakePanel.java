import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SnakePanel extends JPanel implements ActionListener {
    protected static final int WIDTH = 600;
    protected JMenuBar menuBar;
    protected Color colorPanel = Color.yellow;
    protected static Timer timer;

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
        frame.setAlwaysOnTop(true);
        frame.setResizable(false);
        addKeyListener(new FieldKeyListener());
        repaint();
        timer = new Timer(SnakeLogicGame.speedGame, this);
        timer.start();
        setFocusable(true);
        requestFocus();
        //System.out.println(java.awt.KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner());
    }

    protected void initMenu(){
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

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        for (int i = 0; i < SnakeLogicGame.longSnake; i++) {
            graphics.setColor(Color.BLACK);
            graphics.fillRect(SnakeLogicGame.snake.get(i).CoordX, SnakeLogicGame.snake.get(i).CoordY, SnakeLogicGame.sizeBlock, SnakeLogicGame.sizeBlock);
        }
        for (int i = 0; i < SnakeLogicGame.eatBlock.size(); i++) {
            int a = SnakeLogicGame.eatBlock.get(i).CoordX * SnakeLogicGame.sizeBlock;
            int b = SnakeLogicGame.eatBlock.get(i).CoordY * SnakeLogicGame.sizeBlock;
            //Color color = colorSnakeBlock();
            graphics.setColor(Color.RED);
//            System.out.println("eatBlock " + i + " : " +  " a: " + a + " b: " + b);
            graphics.fillRect(a, b, SnakeLogicGame.sizeBlock, SnakeLogicGame.sizeBlock);
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        SnakeLogicGame.moveSnake(SnakeLogicGame.snake);
        repaint();
    }
    protected Color colorSnakeBlock () {
        Color colorRandom = Color.yellow;
        Random random = new Random();
        int randomValue = random.nextInt(10);
        switch (randomValue) {
            case 1:
                colorRandom = Color.BLUE;
                break;
            case 2:
                colorRandom = Color.BLACK;
                break;
            case 3:
                colorRandom = Color.CYAN;
                break;
            case 4:
                colorRandom = Color.GREEN;
                break;
            case 5:
                colorRandom = Color.PINK;
                break;
            case 6:
                colorRandom = Color.MAGENTA;
                break;
            case 7:
                colorRandom = Color.ORANGE;
                break;
            case 8:
                colorRandom = Color.DARK_GRAY;
                break;
            case 9:
                colorRandom = Color.WHITE;
                break;
            default:
                colorRandom = Color.DARK_GRAY;
        }
        return colorRandom;
    }
}

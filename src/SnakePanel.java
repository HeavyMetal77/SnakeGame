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
    protected static Color colorSnake = Color.BLACK;
    protected static Color colorEatBlock = Color.RED;
    protected static JLabel labelStatus;
    protected JFrame frame;

    public SnakePanel() throws HeadlessException {
        frame = new JFrame();
        frame.setTitle("Snake");
        frame.setMinimumSize(new Dimension(600, 685));
        frame.add(this, BorderLayout.WEST);
        setPreferredSize(new Dimension(WIDTH, WIDTH));
        setMinimumSize(new Dimension(WIDTH, WIDTH));
        setMaximumSize(new Dimension(WIDTH, WIDTH));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        initMenu();
        frame.setJMenuBar(menuBar);
        setBackground(colorPanel);
        frame.getContentPane().add(this);
        frame.setVisible(true);
        frame.pack();
        frame.setAlwaysOnTop(true);
        frame.setResizable(false);
        setBorder(BorderFactory.createEtchedBorder());
        initLabel();
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
        JMenuItem menuItemNewGame = new JMenuItem("New Game");
        JMenuItem menuItemOpenGame = new JMenuItem("Open Game");
        JMenuItem menuItemSaveGame = new JMenuItem("Save Game");
        JMenuItem menuItemPauseGame = new JMenuItem("Pause Game");
        JMenuItem menuItemResumeGame = new JMenuItem("Resume Game");



        JMenuItem menuItemConfigStartGame = new JMenuItem("ConfigStartGame");
        JMenuItem menuItemSkin = new JMenuItem("Skin");
        menuGame.add(menuItemNewGame);
        menuGame.add(menuItemOpenGame);
        menuGame.add(menuItemSaveGame);
        menuGame.add(menuItemPauseGame);
        menuGame.add(menuItemResumeGame);
        menuSettings.add(menuItemConfigStartGame);
        menuSettings.add(menuItemSkin);
        menuBar.add(menuGame);
        menuBar.add(menuSettings);

        menuItemPauseGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Pause");
                timer.stop();
            }
        });

        menuItemResumeGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Resume");
                timer.start();
            }
        });
    }

    private void initLabel(){
        SnakeLogicGame.score = 0;
        labelStatus = new JLabel("Welcome! Score: " + SnakeLogicGame.score);
        frame.add(labelStatus, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        for (int i = 0; i < SnakeLogicGame.snake.size(); i++) {
            graphics.setColor(colorSnake);
            graphics.fillRect(SnakeLogicGame.snake.get(i).CoordX, SnakeLogicGame.snake.get(i).CoordY, SnakeLogicGame.sizeBlock, SnakeLogicGame.sizeBlock);
        }
        for (int i = 0; i < SnakeLogicGame.eatBlock.size(); i++) {
            int a = SnakeLogicGame.eatBlock.get(i).CoordX;
            int b = SnakeLogicGame.eatBlock.get(i).CoordY;
            graphics.setColor(colorEatBlock);
            graphics.fillRect(a, b, SnakeLogicGame.sizeBlock, SnakeLogicGame.sizeBlock);
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        SnakeLogicGame.moveSnake(SnakeLogicGame.snake);
        SnakeLogicGame.coincidence();
        repaint();
    }
    protected static Color colorSnakeBlock () {
        Color colorRandom;
        Random random = new Random();
        int randomValue = random.nextInt(10);
        switch (randomValue) {
            case 0:
                colorRandom = Color.BLUE;
                break;
            case 1:
                colorRandom = Color.RED;
                break;
            case 2:
                colorRandom = Color.GREEN;
                break;
            case 3:
                colorRandom = Color.CYAN;
                break;
            case 4:
                colorRandom = Color.BLACK;
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
                colorRandom = Color.RED;
        }
        return colorRandom;
    }
}

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
    protected static JLabel labelStatus;
    protected JFrame frame;
    protected JButton buttonPause;
    protected JButton buttonResume;
    protected JButton buttonNewGame;

    public SnakePanel() throws HeadlessException {
        frame = new JFrame();
        frame.setTitle("Snake");
        frame.setMinimumSize(new Dimension(600, 735));
        frame.add(this, BorderLayout.CENTER);
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
        JSeparator separator = new JSeparator();
        JMenuItem menuItemExitGame = new JMenuItem("Exit Game");

        JMenuItem menuItemConfigStartGame = new JMenuItem("ConfigStartGame");
        JMenuItem menuItemSkin = new JMenuItem("Skin");
        menuGame.add(menuItemNewGame);
        menuGame.add(menuItemOpenGame);
        menuGame.add(menuItemSaveGame);
        menuGame.add(menuItemPauseGame);
        menuGame.add(menuItemResumeGame);
        menuGame.add(separator);
        menuGame.add(menuItemExitGame);
        menuSettings.add(menuItemConfigStartGame);
        menuSettings.add(menuItemSkin);
        menuBar.add(menuGame);
        menuBar.add(menuSettings);

        buttonNewGame = new JButton("New Game");
        buttonNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        buttonPause = new JButton("Pause");
        buttonPause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                timer.stop();
            }
        });
        buttonResume = new JButton("Resume");
        buttonResume.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                timer.start();
                requestFocus();
            }
        });
        JPanel panelBatton = new JPanel();
        panelBatton.add(buttonNewGame);
        panelBatton.add(buttonPause);
        panelBatton.add(buttonResume);
        frame.add(panelBatton, BorderLayout.NORTH);

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

        menuItemExitGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
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
        for (int i = 0; i < SnakeLogicGame.eatBlockColor.size(); i++) {
            int a = SnakeLogicGame.eatBlockColor.get(i).getCoord().CoordX;
            int b = SnakeLogicGame.eatBlockColor.get(i).getCoord().CoordY;
            graphics.setColor(SnakeLogicGame.eatBlockColor.get(i).getColorEatBlock());
            graphics.fillRect(a, b, SnakeLogicGame.sizeBlock, SnakeLogicGame.sizeBlock);
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        SnakeLogicGame.moveSnake(SnakeLogicGame.snake);
        SnakeLogicGame.coincidence();
        repaint();
    }

}

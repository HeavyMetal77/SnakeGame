import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;
import com.jtattoo.plaf.mint.MintLookAndFeel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;

public class SnakePanel extends JPanel implements ActionListener {
    protected static final int WIDTH = 600;
    protected JMenuBar menuBar;
    protected Color colorPanel = Color.yellow;
    protected static Timer timer;
    protected static Color colorSnake = Color.RED;
    protected static JLabel labelStatus;
    protected JFrame frame;
    protected JPanel panelConfig;
    public File file = new File("");

    public SnakePanel() throws HeadlessException {
        frame = new JFrame();
        frame.setTitle("Snake");
        frame.setIconImage(new ImageIcon("C:\\Repository\\snakeGame\\src\\snake.png").getImage());
        setMinimumSize(new Dimension(WIDTH, WIDTH));
        setPreferredSize(new Dimension(WIDTH, WIDTH));
        setMaximumSize(new Dimension(WIDTH, WIDTH));
        frame.setMinimumSize(new Dimension(600, 685));
        frame.add(this, BorderLayout.CENTER);
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
    }

    protected void initPanelConfig(){
        panelConfig = new JPanel();
        panelConfig.setMinimumSize(new Dimension(WIDTH,WIDTH));
        panelConfig.setBackground(Color.blue);
        frame.add(panelConfig, BorderLayout.CENTER);
    }

    protected void initMenu(){
        menuBar = new JMenuBar();

        JMenu menuGame = new JMenu("Game");
        JMenu menuSettings = new JMenu("Settings");
        JMenuItem menuItemOpenGame = new JMenuItem("Open Game");
        JMenuItem menuItemSaveGame = new JMenuItem("Save Game");
        JMenuItem menuItemNewGame = new JMenuItem("New Game");
        JMenuItem menuItemPauseGame = new JMenuItem("Pause");
        JMenuItem menuItemResumeGame = new JMenuItem("Resume");
        JMenuItem menuItemExitGame = new JMenuItem("Exit");
        JMenuItem menuItemConfigStartGame = new JMenuItem("ConfigStartGame");
        JSeparator separator = new JSeparator();
        separator.setOrientation(SwingConstants.VERTICAL);

        menuItemNewGame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        menuItemPauseGame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        menuItemResumeGame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        menuItemExitGame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        menuItemNewGame.setBackground(Color.GREEN);
        menuItemPauseGame.setBackground(Color.GREEN);
        menuItemResumeGame.setBackground(Color.GREEN);
        menuItemExitGame.setBackground(Color.GREEN);

        menuItemNewGame.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        menuItemPauseGame.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        menuItemResumeGame.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        menuItemExitGame.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        JMenu menuItemSkin = new JMenu("Skin");
        JMenuItem menuItemSkin1 = new JMenuItem("Skin1");
        JMenuItem menuItemSkin2 = new JMenuItem("Skin2");
        JMenuItem menuItemSkin3 = new JMenuItem("Skin3");

        menuItemSkin.add(menuItemSkin1);
        menuItemSkin.add(menuItemSkin2);
        menuItemSkin.add(menuItemSkin3);

        menuItemSaveGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("D:\\"));
                fileChooser.setFileFilter(new filterFileSnake());
                int ret = fileChooser.showSaveDialog(frame);
                if (ret == JFileChooser.APPROVE_OPTION) {
                    file = fileChooser.getSelectedFile();
                    if(!fileChooser.getSelectedFile().getName().endsWith("snk")){
                        file = new File(file.getPath()+ ".snk");
                    }
                    try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))){
                        SnakeLogicGame.serializedProgressGame(objectOutputStream);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        menuItemOpenGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("D:\\"));
                fileChooser.setFileFilter(new filterFileSnake());
                int ret = fileChooser.showDialog(frame, "Открыть файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    file = fileChooser.getSelectedFile();
                    try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))){
                        SnakeLogicGame.deserializedProgressGame(objectInputStream);
                        labelStatus.setText("Welcome! Score: " + SnakeLogicGame.score + ". Best result: " + SnakeLogicGame.bestScoreSession);
                        repaint();
                    } catch (ClassNotFoundException|IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        menuItemSkin1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    UIManager.setLookAndFeel(new MintLookAndFeel());
                    JFrame.setDefaultLookAndFeelDecorated(true);
                    SwingUtilities.updateComponentTreeUI(frame);
                } catch (UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }
            }
        });

        menuItemSkin2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    UIManager.setLookAndFeel(new AluminiumLookAndFeel());
                    JFrame.setDefaultLookAndFeelDecorated(true);
                    SwingUtilities.updateComponentTreeUI(frame);
                } catch (UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }
            }
        });

        menuItemSkin3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                    JFrame.setDefaultLookAndFeelDecorated(true);
                    SwingUtilities.updateComponentTreeUI(frame);
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }
            }
        });

        menuItemConfigStartGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                initPanelConfig();
            }
        });

        menuGame.add(menuItemOpenGame);
        menuGame.add(menuItemSaveGame);
        menuSettings.add(menuItemConfigStartGame);
        menuSettings.add(menuItemSkin);
        menuBar.add(menuGame);
        menuBar.add(menuSettings);
        menuBar.add(separator);
        menuBar.add(menuItemNewGame);
        menuBar.add(menuItemPauseGame);
        menuBar.add(menuItemResumeGame);
        menuBar.add(menuItemExitGame);


        menuItemNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SnakeMain.logicGame = new SnakeLogicGame();
                SnakeLogicGame.initStartValue();
                labelStatus.setText("Welcome! Score: " + SnakeLogicGame.score + ". Best result: " + SnakeLogicGame.bestScoreSession);
                repaint();
                timer.start();
            }
        });

        menuItemPauseGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                timer.stop();
            }
        });

        menuItemResumeGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                timer.start();
            }
        });

        menuItemExitGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        menuItemOpenGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Event.CTRL_MASK));
        menuItemSaveGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
        menuItemNewGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, Event.CTRL_MASK));
        menuItemPauseGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, Event.CTRL_MASK));
        menuItemResumeGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, Event.CTRL_MASK));
        menuItemExitGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, Event.CTRL_MASK));
    }

    private void initLabel(){
        SnakeLogicGame.score = 0;
        labelStatus = new JLabel("Welcome! Score: " + SnakeLogicGame.score + ". Best result: " + SnakeLogicGame.bestScoreSession);
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

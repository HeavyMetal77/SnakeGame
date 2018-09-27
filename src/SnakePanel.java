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
    protected static Color colorSnake = Color.BLACK;
    protected static JLabel labelStatus;
    protected JFrame frame;
    protected JPanel panelConfig;
    public File file = new File("C:\\Repository\\snakeGame\\src\\save1.snk");

    public SnakePanel() throws HeadlessException {
        frame = new JFrame();
        frame.setTitle("Snake");
        frame.setIconImage(new ImageIcon("C:\\Repository\\snakeGame\\src\\snake.png").getImage());
        frame.setMinimumSize(new Dimension(WIDTH, WIDTH+85));
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
        menuItemOpenGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileopen = new JFileChooser();
                int ret = fileopen.showDialog(frame, "Открыть файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileopen.getSelectedFile();
                    System.out.println(file.getName());
                }
                try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))){
                    SnakeLogicGame.deserializedProgressGame(objectInputStream);
                } catch (ClassNotFoundException|IOException e) {
                    e.printStackTrace();
                }
            }
        });

        menuItemOpenGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Event.CTRL_MASK));
        JMenuItem menuItemSaveGame = new JMenuItem("Save Game");
        menuItemSaveGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileopen = new JFileChooser();
                int ret = fileopen.showSaveDialog(frame);
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file1 = fileopen.getSelectedFile();
//                    file1 = file;
                    System.out.println(file1.getName());
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
        menuItemSaveGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
        JMenuItem menuItemNewGame = new JMenuItem("New Game");
        menuItemNewGame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        menuItemNewGame.setBackground(Color.GREEN);
        menuItemNewGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, Event.CTRL_MASK));
        menuItemNewGame.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        JMenuItem menuItemPauseGame = new JMenuItem("Pause");
        menuItemPauseGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, Event.CTRL_MASK));
        menuItemPauseGame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        menuItemPauseGame.setBackground(Color.GREEN);
        menuItemPauseGame.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        JMenuItem menuItemResumeGame = new JMenuItem("Resume");
        menuItemResumeGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, Event.CTRL_MASK));
        menuItemResumeGame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        menuItemResumeGame.setBackground(Color.GREEN);
        menuItemResumeGame.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        JMenuItem menuItemExitGame = new JMenuItem("Exit");
        menuItemExitGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, Event.CTRL_MASK));
        menuItemExitGame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        menuItemExitGame.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        menuItemExitGame.setBackground(Color.GREEN);
        JMenuItem menuItemConfigStartGame = new JMenuItem("ConfigStartGame");
        menuItemExitGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, Event.CTRL_MASK));

        JMenu menuItemSkin = new JMenu("Skin");
        JMenuItem menuItemSkin1 = new JMenuItem("Skin1");
        JMenuItem menuItemSkin2 = new JMenuItem("Skin2");
        JMenuItem menuItemSkin3 = new JMenuItem("Skin3");

        menuItemSkin.add(menuItemSkin1);
        menuItemSkin.add(menuItemSkin2);
        menuItemSkin.add(menuItemSkin3);

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

        menuGame.add(menuItemOpenGame);
        menuGame.add(menuItemSaveGame);
        menuSettings.add(menuItemConfigStartGame);
        menuSettings.add(menuItemSkin);
        menuBar.add(menuGame);
        menuBar.add(menuSettings);
        JSeparator separator = new JSeparator();
        separator.setOrientation(SwingConstants.VERTICAL);
        menuBar.add(separator);
        menuBar.add(menuItemNewGame);
        menuBar.add(menuItemPauseGame);
        menuBar.add(menuItemResumeGame);
        menuBar.add(menuItemExitGame);
        menuItemConfigStartGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                initPanelConfig();
            }
        });


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

package snakeGame;

import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;
import com.jtattoo.plaf.mint.MintLookAndFeel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;

public class SnakePanel extends JPanel implements ActionListener, Runnable {
    protected static final int WIDTH = 600;
    protected JMenuBar menuBar;
    protected Color colorPanel = Color.yellow;
    protected static Timer timer;
    protected static Color colorSnake = Color.RED;
    protected static JLabel labelStatus;
    protected JFrame frame;
    protected JFrame panelConfig;
    protected JFrame panelTableResult = new JFrame("Table of Winners");
    public File file = new File("");

    JMenu menuGame;
    JMenu menuSettings;
    JMenuItem menuItemOpenGame;
    JMenuItem menuItemSaveGame;
    JMenuItem menuItemNewGame;
    JMenuItem menuItemPauseGame;
    JMenuItem menuItemResumeGame;
    JMenuItem menuItemExitGame;
    JMenuItem menuItemConfigStartGame;
    JMenu menuItemSkin;
    JMenuItem menuItemSkin1;
    JMenuItem menuItemSkin2;
    JMenuItem menuItemSkin3;
    JSeparator separator;

    public SnakePanel() throws HeadlessException {
        frame = new JFrame();
        frame.setTitle("Snake");
        frame.setIconImage(new ImageIcon("C:\\Repository\\java.snakeGame\\src\\snake.png").getImage());
        setMinimumSize(new Dimension(WIDTH, WIDTH));
        setPreferredSize(new Dimension(WIDTH, WIDTH));
        setMaximumSize(new Dimension(WIDTH, WIDTH));
        setBorder(BorderFactory.createLineBorder(Color.blue));
        frame.setMinimumSize(new Dimension(WIDTH+18, WIDTH+81));
        frame.add(this, BorderLayout.WEST);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        initMenu();
        frame.setJMenuBar(menuBar);
        setBackground(colorPanel);
        frame.getContentPane().add(this);
        frame.setVisible(true);
        frame.pack();
        frame.setResizable(false);
        setBorder(BorderFactory.createEtchedBorder());
        initLabel();
        addKeyListener(new FieldKeyListener());
        listenersMenu();
        repaint();
        timer = new Timer(SnakeLogicGame.speedGame, this);
        timer.start();
        setFocusable(true);
        requestFocus();
    }

    protected void initPanelConfig(){
        timer.stop();
        panelConfig = new JFrame("Game configuration");
        panelConfig.setMinimumSize(new Dimension(WIDTH/2,210));
        panelConfig.setLocationRelativeTo(null);
        panelConfig.setVisible(true);
        panelConfig.setResizable(false);
        panelConfig.setLayout(new FlowLayout());
        JPanel subPanelConfig1 = new JPanel();
        JPanel subPanelConfig2 = new JPanel();
        JPanel subPanelConfig3 = new JPanel();
        JPanel subPanelConfig4 = new JPanel();

        subPanelConfig1.setPreferredSize(new Dimension(WIDTH/2, 50));
        subPanelConfig2.setPreferredSize(new Dimension(WIDTH/2, 30));
        subPanelConfig3.setPreferredSize(new Dimension(WIDTH/2, 30));
        subPanelConfig4.setPreferredSize(new Dimension(WIDTH/2, 50));

        final JButton buttonColorChooser = new JButton("Select color");
        buttonColorChooser.setBackground(colorPanel);
        buttonColorChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JColorChooser colorChooser = new JColorChooser();
                Color color = colorChooser.showDialog(null, "Select a color", colorPanel);
                buttonColorChooser.setBackground(color);
                colorPanel = color;
                setBackground(colorPanel);
            }
        });

        subPanelConfig2.add(buttonColorChooser);

        final JLabel labelSlider = new JLabel();
        labelSlider.setPreferredSize(new Dimension(100, 20));
        labelSlider.setText("Speed game: " + SnakeLogicGame.labelSliderSpeed(SnakeLogicGame.speedGame));

        final JSlider slider = new JSlider(1, 10, SnakeLogicGame.labelSliderSpeed(SnakeLogicGame.speedGame));
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setPaintTrack(true);
        slider.setMajorTickSpacing(1);
        slider.setMinorTickSpacing(1);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                JSlider source = (JSlider) changeEvent.getSource();
                labelSlider.setText("Speed game: " + source.getValue());
            }
        });

        subPanelConfig1.add(labelSlider);
        subPanelConfig1.add(slider);
        slider.setOrientation(SwingConstants.HORIZONTAL);

        JButton buttonOK = new JButton("OK");
        buttonOK.setPreferredSize(new Dimension(70,20));
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                panelConfig.setVisible(false);
                labelStatus.setText("Welcome! Score: " + SnakeLogicGame.score + ". Best result: " + SnakeLogicGame.bestScoreSession);
                if(SnakeLogicGame.inGame){
                    SnakeLogicGame.speedGameLevel(slider.getValue());
                    SnakeLogicGame.initStartValue();
                    repaint();
                    timer.setDelay(SnakeLogicGame.speedGame);
                    timer.restart();
                }else {
                    timer.stop();
                }
            }
        });

        JButton buttonCancel = new JButton("Cancel");
        buttonCancel.setPreferredSize(new Dimension(70,20));
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(SnakeLogicGame.inGame){
                    timer.setDelay(SnakeLogicGame.speedGame);
                    timer.restart();
                }else {
                    timer.stop();
                }
                panelConfig.setVisible(false);
            }
        });

        JButton buttonResultTable = new JButton("Table of Winners");
        buttonResultTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                panelTableResult.setLocationRelativeTo(null);
                panelTableResult.setSize(new Dimension(300, 500));
                panelTableResult.setVisible(true);
            }
        });
        subPanelConfig3.add(buttonResultTable);
        subPanelConfig4.add(buttonOK);
        subPanelConfig4.add(buttonCancel);

        panelConfig.add(subPanelConfig1);
        panelConfig.add(subPanelConfig2);
        panelConfig.add(subPanelConfig3);
        panelConfig.add(subPanelConfig4);
    }

    protected void initMenu(){
        menuBar = new JMenuBar();
        menuGame = new JMenu("Game");
        menuSettings = new JMenu("Settings");
        menuItemOpenGame = new JMenuItem("Open Game");
        menuItemSaveGame = new JMenuItem("Save Game");
        menuItemNewGame = new JMenuItem("New Game");
        menuItemPauseGame = new JMenuItem("Pause");
        menuItemResumeGame = new JMenuItem("Resume");
        menuItemExitGame = new JMenuItem("Exit");
        menuItemConfigStartGame = new JMenuItem("ConfigStartGame");
        menuItemSkin = new JMenu("Skin");
        menuItemSkin1 = new JMenuItem("Skin1");
        menuItemSkin2 = new JMenuItem("Skin2");
        menuItemSkin3 = new JMenuItem("Skin3");
        separator = new JSeparator();
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

        menuItemSkin.add(menuItemSkin1);
        menuItemSkin.add(menuItemSkin2);
        menuItemSkin.add(menuItemSkin3);
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

        menuItemOpenGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Event.CTRL_MASK));
        menuItemSaveGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
        menuItemNewGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, Event.CTRL_MASK));
        menuItemPauseGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, Event.CTRL_MASK));
        menuItemResumeGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, Event.CTRL_MASK));
        menuItemExitGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, Event.CTRL_MASK));
        menuItemConfigStartGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, Event.CTRL_MASK));
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

    private void listenersMenu() {
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
                int ret = fileChooser.showDialog(frame, "Open file");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    file = fileChooser.getSelectedFile();
                    if(!fileChooser.getSelectedFile().getName().endsWith("snk")){
                        JOptionPane.showMessageDialog(frame,
                                new String[] {"Ви вибрали неправильний файл: ",
                                        " - спробуйте найти файл ігрового збереження ('snk')",
                                        " - або створіть його через меню збереження гри"},
                                "Wrong type file",
                                JOptionPane.ERROR_MESSAGE);

                    } else {
                        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))){
                            SnakeLogicGame.deserializedProgressGame(objectInputStream);
                            labelStatus.setText("Welcome! Score: " + SnakeLogicGame.score + ". Best result: " + SnakeLogicGame.bestScoreSession);
                            repaint();
                        } catch (ClassNotFoundException|IOException e) {
                            e.printStackTrace();
                        }
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
        menuItemNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new SnakeLogicGame();
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
    }

    @Override
    public void run() {
    }
}

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class SnakeLogicGame implements Serializable{
    protected static int sizeBlock = 10;
    protected static int longSnake = 4;
    protected static int countEatBlock = 4;
    protected static int score;
    protected static int bestScoreSession = 0;
    protected static boolean left = false;
    protected static boolean right = true;
    protected static boolean up = false;
    protected static boolean down = false;
    protected static boolean inGame = true;
    protected static int speedGame = 400;
    protected static ArrayList<Coord> snake = new ArrayList<>();
    protected static ArrayList<EatBlock> eatBlockColor = new ArrayList<>();

    public SnakeLogicGame() {
        initSnake();
        generateEatBlockColor(countEatBlock);
    }

    protected static void initStartValue(){
        if(bestScoreSession < score)
            bestScoreSession = score;
        longSnake = 4;
        countEatBlock = 4;
        score = 0;
        left = false;
        right = true;
        up = false;
        down = false;
        inGame = true;
    }

    protected static void initSnake() {
        snake = new ArrayList<>(longSnake);
        for (int i = 0; i < longSnake; i++) {
            Coord coord = new Coord(i * sizeBlock, 0);
            snake.add(0, coord); //head is 0-element, tail is last element
        }
    }

    protected void generateEatBlockColor(int countEatBlock) {
        eatBlockColor = new ArrayList<>(countEatBlock);
        for (int i = 0; i < countEatBlock; i++) {
            int randomEatBlockX = new Random().nextInt(SnakePanel.WIDTH / sizeBlock);
            int randomEatBlockY = new Random().nextInt(SnakePanel.WIDTH  / sizeBlock);
            Coord newCoord = new Coord(randomEatBlockX* sizeBlock, randomEatBlockY* sizeBlock);
            Color colorBlock = colorSnakeBlock();
            eatBlockColor.add(new EatBlock(colorBlock, newCoord));
        }
    }

    public static void serializedProgressGame(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(sizeBlock);
        objectOutputStream.writeInt(longSnake);
        objectOutputStream.writeInt(countEatBlock);
        objectOutputStream.writeInt(score);
        objectOutputStream.writeInt(bestScoreSession);
        objectOutputStream.writeBoolean(left);
        objectOutputStream.writeBoolean(right);
        objectOutputStream.writeBoolean(up);
        objectOutputStream.writeBoolean(down);
        objectOutputStream.writeBoolean(inGame);
        objectOutputStream.writeObject(snake);
        objectOutputStream.writeObject(eatBlockColor);
    }
    public static void deserializedProgressGame(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        sizeBlock = objectInputStream.readInt();
        longSnake = objectInputStream.readInt();
        countEatBlock = objectInputStream.readInt();
        score = objectInputStream.readInt();
        bestScoreSession = objectInputStream.readInt();
        left = objectInputStream.readBoolean();
        right = objectInputStream.readBoolean();
        up = objectInputStream.readBoolean();
        down = objectInputStream.readBoolean();
        inGame = objectInputStream.readBoolean();
        snake = (ArrayList<Coord>)objectInputStream.readObject();
        eatBlockColor = (ArrayList<EatBlock>)objectInputStream.readObject();
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

    protected static void moveSnake(ArrayList<Coord> snake) {
        if(snake.get(0).CoordX <=SnakePanel.WIDTH && snake.get(0).CoordY <=SnakePanel.WIDTH
                && snake.get(0).CoordX >=0 && snake.get(0).CoordY >=0){
            for (int i = snake.size()-1; i > 0; i--) {
                snake.get(i).CoordX = snake.get(i-1).CoordX;
                snake.get(i).CoordY = snake.get(i-1).CoordY;
            }
            if (left) {
                snake.get(0).CoordX -= sizeBlock;
            }
            if (right) {
                snake.get(0).CoordX += sizeBlock;
            }
            if (up) {
                snake.get(0).CoordY -= sizeBlock;
            }
            if (down) {
                snake.get(0).CoordY += sizeBlock;
            }
        }else {
            SnakePanel.timer.stop();
            inGame = false;
            if(bestScoreSession<score){
                bestScoreSession = score;
            }
            SnakePanel.labelStatus.setText("Game Over! Your scores: " + score + ". Best result: " + bestScoreSession);
            System.out.println("Game Over");
        }
    }

    protected static void coincidence (){
        for (int i = 4; i < snake.size(); i++) {
            if(snake.size()>4 && snake.get(0).equals(snake.get(i))){
                SnakePanel.timer.stop();
                inGame = false;
                if(bestScoreSession<score){
                    bestScoreSession = score;
                }
                SnakePanel.labelStatus.setText("Game Over! Your scores: " + score + ". Best result: " + bestScoreSession);
                System.out.println("Game Over!");
            }
        }
        for (int i = 0; i < countEatBlock; i++) {
            if(snake.get(0).equals(eatBlockColor.get(i).coord)){
                System.out.println(snake.get(0) + " " + eatBlockColor.get(i).coord);
                Coord coord = eatBlockColor.get(i).getCoord();
                snake.add(coord);
                SnakePanel.labelStatus.setText("Your scores: " + (++score) + ". Best result: " + bestScoreSession);
                eatBlockColor.remove(i);
                int randomEatBlockX = new Random().nextInt(SnakePanel.WIDTH / sizeBlock);
                int randomEatBlockY = new Random().nextInt(SnakePanel.WIDTH  / sizeBlock);
                Color temp = colorSnakeBlock();
                eatBlockColor.add(i, new EatBlock(temp, new Coord(randomEatBlockX*sizeBlock, randomEatBlockY*sizeBlock)));
                System.out.println(snake.size() + " " + eatBlockColor.size() + eatBlockColor.get(i).getColorEatBlock());
            }
        }
    }
}

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class SnakeLogicGame {
    protected static int sizeBlock = 10;
    protected static int longSnake = 4;
    protected static int countEatBlock = 3;
    protected static int score;
    protected static boolean left = false;
    protected static boolean right = true;
    protected static boolean up = false;
    protected static boolean down = false;
    protected static boolean inGame = true;
    protected static int speedGame = 100;
    protected static ArrayList<Coord> snake;
    protected static ArrayList<Coord> eatBlock;

    public SnakeLogicGame() {
        initSnake();
        generateEatBlock(countEatBlock);
    }

    protected static void initSnake() {
        snake = new ArrayList<>(longSnake);
        for (int i = 0; i < longSnake; i++) {
            Coord coord = new Coord(i * sizeBlock, 0);
            snake.add(0, coord); //head is 0-element, tail is last element
        }
    }

    protected static void generateEatBlock(int countEatBlock) {
        eatBlock = new ArrayList<>(countEatBlock);
        for (int i = 0; i < countEatBlock; i++) {
            int randomEatBlockX = new Random().nextInt(SnakePanel.WIDTH / sizeBlock);
            int randomEatBlockY = new Random().nextInt(SnakePanel.WIDTH  / sizeBlock);
            eatBlock.add(new Coord(randomEatBlockX* sizeBlock, randomEatBlockY* sizeBlock));
        }
    }

    protected static void moveSnake(ArrayList<Coord> snake) {
        if(snake.get(0).CoordX <=SnakePanel.WIDTH && snake.get(0).CoordY <=SnakePanel.WIDTH
                && snake.get(0).CoordX >=0 && snake.get(0).CoordY >=0){
            for (int i = snake.size()-1; i > 0; i--) {//для тела змейки
                snake.get(i).CoordX = snake.get(i-1).CoordX;
                snake.get(i).CoordY = snake.get(i-1).CoordY;
            }
            //для головы змеи - повороты
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
            SnakePanel.labelStatus.setText("Game Over! Your scores: " + score);
            System.out.println("Game Over");
        }
    }

    protected static void coincidence (){
        for (int i = 4; i < snake.size(); i++) {
            if(snake.size()>4 && snake.get(0).equals(snake.get(i))){
                SnakePanel.timer.stop();
                inGame = false;
                SnakePanel.labelStatus.setText("Game Over! Your scores: " + score);
                System.out.println("Game Over!");
            }
        }
        for (int i = 0; i < countEatBlock; i++) {
            if(snake.get(0).equals(eatBlock.get(i))){
                System.out.println(snake.get(0) + " " + eatBlock.get(i));
                Coord coord = eatBlock.get(i);
                snake.add(coord);
                SnakePanel.labelStatus.setText("Score: " + (++score));
                eatBlock.remove(i);
                SnakePanel.colorEatBlock = SnakePanel.colorSnakeBlock();
                int randomEatBlockX = new Random().nextInt(SnakePanel.WIDTH / sizeBlock);
                int randomEatBlockY = new Random().nextInt(SnakePanel.WIDTH  / sizeBlock);
                eatBlock.add(i, new Coord(randomEatBlockX*sizeBlock, randomEatBlockY*sizeBlock));
                System.out.println(snake.size() + " " + eatBlock.size() + SnakePanel.colorEatBlock);
            }
        }

    }



}

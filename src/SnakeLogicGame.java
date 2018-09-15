import javax.swing.*;
import java.util.ArrayList;

public class SnakeLogicGame {
    protected static int sizeBlock = 10;
    protected static int longSnake = 4;
    protected static int countEatBlock = 3;
    Timer timer;
    protected static ArrayList<Coord> snake = new ArrayList<>(longSnake);
    ArrayList<Coord> eatBlock;

    private void initSnake() {
        for (int i = 0; i < longSnake; i++) {
            Coord coord = new Coord(i * sizeBlock, 0);
            snake.add(0, coord); //head is 0-element, tail is last element
        }
    }
}

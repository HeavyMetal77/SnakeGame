import java.util.ArrayList;

public class ExceptionSnake extends Exception {


    public ExceptionSnake(){

    }

    @Override
    public String toString() {
        return "ExceptionSnake{" +
                "longSnake=" + SnakeLogicGame.longSnake +
                ", countEatBlock=" + SnakeLogicGame.countEatBlock +
                ", score=" + SnakeLogicGame.score +
                ", bestScoreSession=" + SnakeLogicGame.bestScoreSession +
                ", left=" + SnakeLogicGame.left +
                ", right=" + SnakeLogicGame.right +
                ", up=" + SnakeLogicGame.up +
                ", down=" + SnakeLogicGame.down +
                ", inGame=" + SnakeLogicGame.inGame +
                ", speedGame=" + SnakeLogicGame.speedGame +
                ", snake=" + SnakeLogicGame.snake +
                ", eatBlockColor=" + SnakeLogicGame.eatBlockColor +
                '}';
    }
}

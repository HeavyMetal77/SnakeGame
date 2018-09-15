import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent keyEvent) {
            super.keyPressed(keyEvent);
            int key = keyEvent.getKeyCode();
            if (key == KeyEvent.VK_LEFT && !SnakeLogicGame.right) {
                SnakeLogicGame.left = true;
                SnakeLogicGame.up = false;
                SnakeLogicGame.down = false;
            }
            if (key == KeyEvent.VK_RIGHT && !SnakeLogicGame.left) {
                SnakeLogicGame.right = true;
                SnakeLogicGame.up = false;
                SnakeLogicGame.down = false;
            }
            if (key == KeyEvent.VK_UP && !SnakeLogicGame.down) {
                SnakeLogicGame.left = false;
                SnakeLogicGame.up = true;
                SnakeLogicGame.right = false;
            }
            if (key == KeyEvent.VK_DOWN && !SnakeLogicGame.up) {
                SnakeLogicGame.left = false;
                SnakeLogicGame.down = true;
                SnakeLogicGame.right = false;
            }
        }
    }


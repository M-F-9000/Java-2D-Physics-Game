package handler;

import java.awt.event.*;

/**
 * Player movement is done via the Player class as the player has multiple sprites, it requires a polling system instead
 * of an interrupt system. This class simply just checks the value of keys used for movement.
 */

public class KeyHandler implements KeyListener {
    public boolean upPressed, leftPressed, rightPressed;

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W  || code == KeyEvent.VK_SPACE || code == KeyEvent.VK_UP) {
            upPressed = true;
        } else if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = true;
        } else if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_SPACE || code == KeyEvent.VK_UP) {
            upPressed = false;
        } else if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        } else if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
    }
}

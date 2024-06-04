package handler;

import game.GameView;

import java.awt.event.ActionEvent;

/**
 * Used to reset the "You win" and score display in the game view.
 * Set to a timer so the player has time to view their score.
 */
public class resetTimerHandler extends TimerHandler{
    public resetTimerHandler(GameView view) {
        super(view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        view.resetScreen();
    }
}

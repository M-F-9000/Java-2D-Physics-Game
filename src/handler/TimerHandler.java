package handler;

import game.GameView;

import java.awt.event.*;

public class TimerHandler implements ActionListener {
    GameView view;

    /**
     * Updates the time tracked and displayed in the view.
     *
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        view.updateTime();
    }

    public TimerHandler(GameView view) {
        this.view = view;
    }

}

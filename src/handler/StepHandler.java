package handler;

import city.cs.engine.*;
import entity.Player;
import game.GameLevel;
import game.GameView;

public class StepHandler implements StepListener {
    GameView view;
    Player player;
    GameLevel world;

    /**
     * Player movement and sprite loading is done on a polling system
     * as there are several sprites to switch between based on several conditions: idle, run, jump
     *
     * Camera position is also updated based on the player's position
     */
    public void preStep(StepEvent stepEvent) {
        player.update();
        player.draw();

        view.update();
    }


    public void postStep(StepEvent stepEvent) {

    }

    public StepHandler(GameView view, GameLevel world) {
        this.view = view;
        this.player = world.getPlayer();
        this.world = world;
    }

    /**
     * Used to update the level when the level switches.
     *
     * @param world new game level to use, specifically for getting the player
     * @param view new view to update the camera in
     */
    public void updateLevel(GameLevel world, GameView view) {
        this.world = world;
        this.player = world.getPlayer();
        this.view = view;
    }
}

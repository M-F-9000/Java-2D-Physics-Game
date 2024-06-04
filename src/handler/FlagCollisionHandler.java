package handler;

import city.cs.engine.Body;
import city.cs.engine.CollisionEvent;
import entity.Player;
import game.GameLevel;

public class FlagCollisionHandler extends CollisionHandler{
    /**
     * boolean prevents player from colliding with the flag multiple times before a level switch.
     */
    boolean win = false;

    /**
     * Allows events to be activated from the player colliding with the flag.
     */
    public FlagCollisionHandler(GameLevel world) {
        super(world);
    }

    @Override
    public void collide(CollisionEvent e) {
        super.collide(e);
        Body other = e.getOtherBody();

        player.removeAllCollisionListeners();
        // !win to prevent multiple collisions before level switch
        if (other instanceof Player && !win) {
            win = true;
            world.getView().winGame();
        }
    }
}

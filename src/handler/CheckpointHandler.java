package handler;

import city.cs.engine.SensorEvent;
import entity.Player;
import game.GameLevel;
import org.jbox2d.common.Vec2;

public class CheckpointHandler extends SensorHandler{
    /**
     * x and y values to set the player's respawn point to
     */
    private final float x,y;

    /**
     * Allows events to be activated from checkpoints.
     */
    public CheckpointHandler(GameLevel world, float x, float y) {
        super(world);
        this.x = x;
        this.y = y;
    }

    @Override
    public void beginContact(SensorEvent e) {
        if (e.getContactBody() instanceof  Player) {
            // Updates the player's respawn point when they make contact with a checkpoint sensor.
            Player.setLastCheckpoint(new Vec2(x, y));
        }
    }


}

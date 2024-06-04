package worldCreator;

import city.cs.engine.BoxShape;
import city.cs.engine.Sensor;
import city.cs.engine.StaticBody;
import game.GameLevel;
import handler.CheckpointHandler;
import org.jbox2d.common.Vec2;

public class CheckpointCreator {
    GameLevel world;
    private final BoxShape sensorShape = new BoxShape(1,30);

    /**
     * Allows checkpoints to be setup.
     */
    public CheckpointCreator(GameLevel world) {
        this.world = world;
    }

    /**
     * Allows a checkpoint to be placed in the level. Automatically adds a sensor.
     *
     * @param  x x-position of where the player should respawn
     * @param  y y-position of where the player should respawn
     */
    public void setup(float x, float y) {
        StaticBody body = new StaticBody(world);

        CheckpointHandler sensorH = new CheckpointHandler(world,x,y);

        body.setPosition(new Vec2(x,y));
        Sensor sensor = new Sensor(body,sensorShape);

        sensor.addSensorListener(sensorH);
    }


}

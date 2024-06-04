package worldCreator;

import city.cs.engine.*;
import entity.Enemy;
import game.GameLevel;
import handler.EnemySensorHandler;
import org.jbox2d.common.Vec2;

public class SensorCreator{
    GameLevel world;
    private final BoxShape sensorShape = new BoxShape(1,1);

    /**
     * Allows sensors to be setup.
     */
    public SensorCreator(GameLevel world) {
        this.world = world;
    }

    /**
     * Sets up a pair of sensor for an enemy to patrol between
     *
     * @param  x1 x-position of the first sensor
     * @param  y1 y-position of the first sensor
     * @param  x2 x-position of the second sensor
     * @param  y2 y-position of the second sensor
     * @param  enemy enemy object that the sensor should react to
     */
    public void setup(float x1, float y1, float x2, float y2, Enemy enemy) {
        StaticBody body1 = new StaticBody(world);
        StaticBody body2 = new StaticBody(world);

        EnemySensorHandler sensorH1 = new EnemySensorHandler(world,enemy);

        body1.setPosition(new Vec2(x1,y1));
        Sensor sensor1 = new Sensor(body1,sensorShape);

        body2.setPosition(new Vec2(x2,y2));
        Sensor sensor2 = new Sensor(body2,sensorShape);

        sensor1.addSensorListener(sensorH1);
        sensor2.addSensorListener(sensorH1);
    }
}

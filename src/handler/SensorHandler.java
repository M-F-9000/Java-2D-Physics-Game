package handler;

import city.cs.engine.SensorEvent;
import city.cs.engine.SensorListener;
import game.GameLevel;

public class SensorHandler implements SensorListener {
    GameLevel world;
    @Override
    public void beginContact(SensorEvent e) {
    }

    @Override
    public void endContact(SensorEvent sensorEvent) {
    }

    public SensorHandler(GameLevel world) {
        this.world = world;
    }
}

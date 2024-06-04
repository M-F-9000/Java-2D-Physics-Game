package handler;

import city.cs.engine.Body;
import city.cs.engine.SensorEvent;
import entity.Enemy;
import entity.Koopa;
import game.GameLevel;

public class EnemySensorHandler extends SensorHandler {
    GameLevel world;
    Enemy enemy;
    @Override
    public void beginContact(SensorEvent e) {
        Body other = e.getContactBody();

        // if statement to prevent player from activating sensor
        if (other == enemy) {
            // reverse momentum
            enemy.setSpeed(-enemy.getSpeed());
            enemy.startWalking(enemy.getSpeed());

            // koopa enemy has images facing left and right when not in its shell form. Adds appropriate image
            if (other instanceof Koopa) {
                if (((Koopa) other).notDestroyed()) {
                    enemy.removeAllImages();
                    if (enemy.getSpeed() > 0) {
                        enemy.addImage(Koopa.getImageRight());
                    } else {
                        enemy.addImage(Koopa.getImageLeft());
                    }
                }
            }
        }
    }

    /**
     * Sensor handler to make enemies invert their velocity when hitting a sensor
     * allowing them to patrol an area.
     */
    public EnemySensorHandler(GameLevel world, Enemy enemy) {
        super(world);
        this.world = world;
        this.enemy = enemy;
    }
}

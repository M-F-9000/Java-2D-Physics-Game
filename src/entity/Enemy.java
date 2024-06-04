package entity;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import game.GameLevel;
import org.jbox2d.common.Vec2;

public class Enemy extends Entity {
    private float speed;

    public Enemy(GameLevel world, BoxShape characterShape, BodyImage image, float x, float y) {
        super(world,characterShape,image);
        this.setPosition(new Vec2(x,y));
    }

    /**
     * Gets the speed value of the enemy.
     * @return the horizontal speed of the enemy.
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * Sets the horizontal speed of the enemy.
     * @param speed float value to set the horizontal speed.
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }
}

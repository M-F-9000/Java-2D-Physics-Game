package worldCreator;

import city.cs.engine.StaticBody;
import game.GameLevel;
import org.jbox2d.common.Vec2;

public class Wall extends Block{
    /**
     * Allows walls to be created.
     */
    public Wall(GameLevel w) {
        super(w);
    }

    /**
     * Draws a wall.
     * @param  x x-position of the wall.
     * @param  y y-position of the bottom of the wall.
     * @param  height height of the wall.
     */
    public void draw(float x, float y, int height) {
        for (int i = 1; height >= i; i++) {
            StaticBody wall = new StaticBody(world, getOneByOne());
            wall.setPosition(new Vec2(x, y));
            wall.addImage(Stair.getStairBlockImage());
            y++;
        }
    }
}

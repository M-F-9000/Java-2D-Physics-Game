package worldCreator;

import city.cs.engine.BodyImage;
import city.cs.engine.StaticBody;
import game.GameLevel;
import handler.FlagCollisionHandler;
import org.jbox2d.common.Vec2;

public class Flag extends Block{
    FlagCollisionHandler collisionH;

    private static final BodyImage flagMiddleImage =
            new BodyImage("data/blocks/flag_middle.png", 1);

    private static final BodyImage flagTopImage =
            new BodyImage("data/blocks/flag_top.png", 1);

    /**
     * Allows a flag to be setup.
     */
    public Flag(GameLevel w) {
        super(w);
        // Creates collision handler to detect when the player has reached the flag.
        collisionH = new FlagCollisionHandler(w);
    }

    /**
     * Allows a flag to be created, the main goal of the level
     *
     * @param  x x-position of the flag
     * @param  y y-position the bottom of the flag
     * @param  height height of the flag
     */
    public void draw(float x, float y, int height) {
        for (int i = 0; height >= i; i++) {
            StaticBody flag = new StaticBody(world, getOneByOne());
            flag.setPosition(new Vec2(x, y));
            flag.addCollisionListener(collisionH);
            // flag consists of both the pole and the actual flag at the very top
            if (height != i) {
                flag.addImage(flagMiddleImage);
            } else {
                flag.addImage(flagTopImage);

            }
            y++;
        }
    }
}

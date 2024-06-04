package worldCreator;

import city.cs.engine.BodyImage;
import city.cs.engine.StaticBody;
import game.GameLevel;
import org.jbox2d.common.Vec2;

public class Platform extends Block{
    private static final BodyImage platformBlockImage =
            new BodyImage("data/blocks/platform_block.png", 1);
    /**
     * Allows platforms to be setup.
     */
    public Platform(GameLevel w) {
        super(w);
    }

    /**
     * Allows a platform to be drawn
     * @param  len length of platform
     * @param  x x-position of platform
     * @param  y y-position of platform
     */
    public void draw(int len, float x, float y) {
        for (int i = 0; len > i; i++) {
            StaticBody platform = new StaticBody(world, getOneByOne());
            platform.setPosition(new Vec2(x, y));
            platform.addImage(platformBlockImage);
            x++;
        }
    }
}

package worldCreator;

import city.cs.engine.BodyImage;
import city.cs.engine.StaticBody;
import game.GameLevel;
import org.jbox2d.common.Vec2;

public class Stair extends Block{
    private static final BodyImage stairBlockImage =
            new BodyImage("data/blocks/stair_block.png", 1);

    /**
     * Allows stairs to be created.
     */
    public Stair(GameLevel w) {
        super(w);
    }

    /**
     * Allows a stair to be drawn
     * @param  len length of stair
     * @param  x x-position of stair
     * @param  y y-position of stair
     */
    public void draw(int len, float x, float y) {
        float originalX = x;
        for (int i = 0; len > i; i++) {
            for (int j = 0; len - i > j; j++) {
                StaticBody stair = new StaticBody(world, getOneByOne());
                stair.setPosition(new Vec2(x, y));
                stair.addImage(stairBlockImage);
                x++;
            }
            x = originalX + 1;
            originalX = x;
            y++;
        }
    }

    /**
     * Gets the stair block image as walls, use the same image.
     *
     * @return image of the stair block
     */
    public static BodyImage getStairBlockImage() {
        return stairBlockImage;
    }
}

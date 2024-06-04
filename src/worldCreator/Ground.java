package worldCreator;

import city.cs.engine.BodyImage;
import city.cs.engine.StaticBody;
import game.GameLevel;
import org.jbox2d.common.Vec2;

public class Ground extends Block{
    private static final BodyImage groundBlockImage =
            new BodyImage("data/blocks/ground_block.png", 1);

    /**
     * Allows grounds to be created.
     */
    public Ground(GameLevel w) {
        super(w);
    }


    /**
     * Draws the ground of a level.
     *
     * @param  row how many rows the ground consists of
     * @param  col how many columns the ground consists of
     * @param  x x-position of the start of the ground
     * @param  y y-position of the start of the ground
     */
    public void draw(int row, int col, float x, float y) {
        float originalX = x;
        for (int i = 0; row > i; i++) {
            for (int j = 0; col > j; j++) {
                StaticBody ground = new StaticBody(world, getOneByOne());
                ground.setPosition(new Vec2(x,y));
                ground.addImage(groundBlockImage);
                x++;
            }
            x = originalX;
            y--;
        }
    }


}

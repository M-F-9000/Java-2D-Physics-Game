package worldCreator;

import city.cs.engine.BodyImage;
import city.cs.engine.StaticBody;
import game.GameLevel;
import handler.CollisionHandler;
import handler.ItemCollisionHandler;
import org.jbox2d.common.Vec2;

public class ItemBox extends Block{
    GameLevel world;
    StaticBody itemBox;
    CollisionHandler collisionH;

    private static final BodyImage itemBlockImage =
            new BodyImage("data/blocks/item_block.gif", 1.25f);

    private static final BodyImage hitItemBlockImage =
            new BodyImage("data/blocks/hit_item_block.png", 1);

    /**
     * Allows item boxes to be setup.
     */
    public ItemBox(GameLevel w) {
        super(w);
        this.world = w;
    }

    /**
     * Creates a single item box, and specifies what item is inside it.
     *
     * @param  x x-position of the item box
     * @param  y y-position of the item box
     * @param  z specifies the type of item in the box
     */
    public void draw(float x, float y, int z) {
        itemBox = new StaticBody(world, getOneByOne());
        collisionH = new ItemCollisionHandler(world);
        itemBox.addCollisionListener(collisionH);
        itemBox.setPosition(new Vec2(x, y));
        itemBox.addImage(itemBlockImage);
        if (z == 1){
            itemBox.setName("Coin Box");
        } else {
            itemBox.setName("Mushroom Box");
        }
    }


    /**
     * Gets the image for a hit item box
     * <p>
     * @return image of a hit item box
     */
    public static BodyImage getHitItemBlockImage() {
        return hitItemBlockImage;
    }
}

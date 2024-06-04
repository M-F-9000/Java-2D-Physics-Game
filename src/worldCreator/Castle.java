package worldCreator;

import city.cs.engine.*;
import game.GameLevel;
import org.jbox2d.common.Vec2;

public class Castle {
    private static final BodyImage castle =
            new BodyImage("data/textures/castle.png",8);

    /**
     * Class instantiation creates a castle image at the end of the level
     */
    public Castle(GameLevel world, float x, float y) {
        StaticBody castleBody = new StaticBody(world);
        castleBody.setPosition(new Vec2(x,y));
        castleBody.addImage(castle);
    }
}

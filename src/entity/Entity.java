package entity;

import city.cs.engine.*;
import game.GameLevel;

public class Entity extends Walker {
    public Entity(GameLevel world, Shape characterShape, BodyImage image) {
        super(world,characterShape);
        // Adds the appropriate entity image
        addImage(image);
    }
}

package entity;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import game.GameLevel;

public class Goomba extends Enemy{
    private static final BoxShape characterShape = new BoxShape(0.5f, 0.5f);

    private static final BodyImage image =
            new BodyImage("data/enemies/goomba.gif", 1);


    public Goomba(GameLevel world, float x, float y) {
        super(world,characterShape,image,x,y);
        // Default goomba speed
        setSpeed(5);
    }
}

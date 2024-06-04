package worldCreator;

import city.cs.engine.*;
import game.GameLevel;
import org.jbox2d.common.Vec2;

public class Pipe extends Block {
    private static final String pipeBlockImage = ("data/blocks/pipe.png");

    /**
     * Allows pipes to be setup.
     */
    public Pipe(GameLevel w) {
        super(w);
    }

    /**
     * Creates a pipe.
     * @param  x x-position of the pipe
     * @param  y y-position of the pipe
     * @param  size size of the pipe
     */
    public void draw(float x, float y, int size) {
        BoxShape pipeShape = new BoxShape((float) size / 2, (float) size / 2);
        BodyImage pipeImage = new BodyImage(pipeBlockImage, size);
        StaticBody pipe = new StaticBody(world, pipeShape);
        pipe.setPosition(new Vec2(x, (float) (y + (size-1)*0.5)));
        pipe.addImage(pipeImage);
    }
}

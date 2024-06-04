package worldCreator;

import city.cs.engine.*;
import game.GameLevel;

public class Block {
    GameLevel world;

    private static final Shape oneByOne = new BoxShape(0.5f, 0.5f);

    public Block(GameLevel w) {
        this.world = w;
    }

    /**
     * Gets the 1 by 1 box shape used by all blocks in the game.
     *
     * @return 1 by 1 box shape
     */
    public Shape getOneByOne() {
        return oneByOne;
    }

}

package entity;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import game.GameLevel;

public class Koopa extends Enemy{
    private static final BoxShape characterShape = new BoxShape(0.5f, 0.99f);
    private boolean destroyed = false;

    private static final BodyImage imageRight =
            new BodyImage("data/enemies/koopa_right.gif", 2);

    private static final BodyImage imageLeft =
            new BodyImage("data/enemies/koopa_left.gif", 2);

    private static final BodyImage imageShell =
            new BodyImage("data/enemies/koopa_shell.png", 1.4f);

    public Koopa(GameLevel world, float x, float y) {
        super(world, characterShape, imageRight, x, y);
        // Default koopa speed
        setSpeed(10);
    }

    /**
     * Gets the left facing koopa image
     *
     * @return the image of left facing koopa
     */
    public static BodyImage getImageLeft() {
        return imageLeft;
    }

    /**
     * Gets the right facing koopa image
     *
     * @return the image of right facing koopa
     */
    public static BodyImage getImageRight() {
        return imageRight;
    }

    /**
     * Gets the shell image
     *
     * @return the image of koopa's shell
     */
    public static BodyImage getImageShell() {
        return imageShell;
    }

    /**
     * Checks if the koopa has been stomped on once
     *
     * @return whether the koopa has been stomped on
     */
    public boolean notDestroyed() {
        return !destroyed;
    }

    /**
     * Sets the koopa as destroyed
     */
    public void setDestroyed() {
        this.destroyed = true;
    }
}

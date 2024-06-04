package entity;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.SoundClip;
import city.cs.engine.StaticBody;
import game.Game;
import game.GameLevel;
import handler.ItemCollisionHandler;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Mushroom extends Item{
    ItemCollisionHandler collisionH;

    private static final BoxShape itemShape = new BoxShape(0.5f, 0.5f);

    private static final BodyImage image =
            new BodyImage("data/items/mushroom.png", 1);

    private static SoundClip mushroomSound;

    // Loads the mushroom collecting sound
    static {
        try {
            mushroomSound = new SoundClip("data/sound/smb_1-up.wav");
            mushroomSound.setVolume(Game.getVolume());
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }
    public Mushroom(GameLevel w) {
        super(w);
        // Creates a collision handler to attach to the mushroom
        collisionH = new ItemCollisionHandler(w);
    }

    /**
     * Draw a mushroom item.
     *
     * @param  x x position of the mushroom
     * @param  y x position of the mushroom
     */
    public void draw(float x, float y) {
        StaticBody mushroom = new StaticBody(world, itemShape);
        mushroom.setPosition(new Vec2(x, y));
        mushroom.addImage(image);
        // Name added to differentiate the mushroom itself from other static bodies
        mushroom.setName("mushroom");
        mushroom.addCollisionListener(collisionH);
    }

    /**
     * Plays the coin sound.
     */
    public static void playMushroomSound() {
        mushroomSound.play();
    }

    /**
     * Updates the volume of the mushroom collecting sound.
     * @param  volume volume to set the sound to
     */
    public static void updateVolume(double volume) {
        mushroomSound.setVolume(volume);
    }

}

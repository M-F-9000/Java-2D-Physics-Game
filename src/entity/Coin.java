package entity;

import city.cs.engine.*;
import game.Game;
import game.GameLevel;
import handler.ItemCollisionHandler;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Coin extends Item{
    ItemCollisionHandler collisionH;

    private static final BoxShape itemShape = new BoxShape(0.5f, 0.5f);

    private static final BodyImage image =
            new BodyImage("data/items/coin.png", 1);

    private static SoundClip coinSound;

    // Loads the coin collecting sound
    static {
        try {
            coinSound = new SoundClip("data/sound/smb_coin.wav");
            coinSound.setVolume(Game.getVolume());
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    public Coin(GameLevel w) {
        super(w);
        // Creates a collision handler to attach to the coin
        collisionH = new ItemCollisionHandler(w);
    }

    /**
     * Draws coins.
     * <p>
     * Draws a row of coins.
     *
     * @param  len how many coins to draw
     * @param  x x position of the first coin
     * @param  y x position of the first coin/s
     */
    public void draw(int len, float x, float y) {
        for (int i = 0; len > i; i++) {
            StaticBody coin = new StaticBody(world, itemShape);
            coin.setPosition(new Vec2(x, y));
            coin.addImage(image);
            // Name added to differentiate the coin itself from other static bodies
            coin.setName("coin");
            coin.addCollisionListener(collisionH);
            x += 2;
        }
    }

    /**
     * Plays the coin sound.
     */
    public static void playCoinSound() {
        coinSound.play();
    }

    /**
     * Updates the volume of the coin collecting sound.
     * @param  volume volume to set the sound to
     */
    public static void updateVolume(double volume) {
        coinSound.setVolume(volume);
    }
}

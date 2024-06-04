package handler;

import city.cs.engine.*;
import entity.*;
import game.GameLevel;
import org.jbox2d.common.Vec2;

public class PlayerCollisionHandler extends CollisionHandler {
    /**
     * Deals with player collision with enemies.
     */
    public PlayerCollisionHandler(GameLevel world) {
        super(world);
    }

    @Override
    public void collide(CollisionEvent e) {
        super.collide(e);

        Body other = e.getOtherBody();

        if (other instanceof Enemy) {
            // ensures that the player is correctly positioned over a goomba
            if (other instanceof Goomba && other.getPosition().y + 0.5 < player.getPosition().y) {
                other.destroy();
                player.setLinearVelocity(new Vec2(player.getLinearVelocity().x, 20));
                player.updateScore(250);
                player.playStompSound();
                // ensures that the player is correctly positioned over a koopa
            } else if (other instanceof Koopa && other.getPosition().y + 1 < player.getPosition().y) {
                player.setLinearVelocity(new Vec2(player.getLinearVelocity().x, 30));
                player.updateScore(250);
                player.playStompSound();

                // koopa enemies have two stages, if they have not been stomped on before this turns them into a shell
                if (((Koopa) other).notDestroyed()) {
                    ((Koopa) other).setDestroyed();
                    other.removeAllImages();
                    other.addImage(Koopa.getImageShell());
                    ((Koopa) other).setSpeed(((Koopa) other).getSpeed() * 2);
                    ((Koopa) other).startWalking(((Koopa) other).getSpeed());
                } else {
                    // destroy koopa if it is a shell
                    other.destroy();
                }

            } else {
                // enemy colliding with player causes loss of a life
                player.setPosition(Player.getLastCheckpoint());
                player.updateLives(-1);
                player.playDeathSound();
                world.respawnEnemies();
            }
        }
    }
}

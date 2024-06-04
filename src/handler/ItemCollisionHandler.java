package handler;

import city.cs.engine.Body;
import city.cs.engine.CollisionEvent;
import entity.Coin;
import entity.Mushroom;
import entity.Player;
import game.GameLevel;
import worldCreator.ItemBox;

import java.util.Objects;

public class ItemCollisionHandler extends CollisionHandler{
    Coin coin;
    Mushroom mushroom;

    /**
     * Handles collision between player and items.
     */
    public ItemCollisionHandler(GameLevel world) {
        super(world);
    }

    @Override
    public void collide(CollisionEvent e) {
        super.collide(e);

        Body other = e.getOtherBody();
        Body reporting = e.getReportingBody();
        // Uses item names to differentiate between bodies
        String name = reporting.getName();

        // Handling collision with item boxes
        if (Objects.equals(name, "Coin Box") || Objects.equals(name, "Mushroom Box") && other == player) {
            // Ensuring player is hitting box from the bottom.
            if (reporting.getPosition().y - 1 > player.getPosition().y) {
                // Adding new image to item box and preventing it from spawning new items.
                reporting.setName("null");
                reporting.removeAllImages();
                reporting.addImage(ItemBox.getHitItemBlockImage());
                reporting.removeAllCollisionListeners();

                player.updateScore(100);
                if (name.equals("Coin Box")) {
                    coin = new Coin(world);
                    coin.draw(1, reporting.getPosition().x, reporting.getPosition().y + 1);
                } else {
                    mushroom = new Mushroom(world);
                    mushroom.draw(reporting.getPosition().x, reporting.getPosition().y + 1);
                }
            }
            // Handling collision with coins
        } else if (Objects.equals(name, "coin")) {
            reporting.destroy();
            player.updateCoins();
            Coin.playCoinSound();
            // Handling collision with mushrooms
        } else if (Objects.equals(name, "mushroom")) {
            reporting.destroy();
            Mushroom.playMushroomSound();
            // Player's max lives is 5, the mushroom will increase the players unless they are at the max then it will provide points
            if (Player.getLives() == 5) {
                player.updateScore(250);
            } else {
                player.updateLives(1);
            }
        }
    }
}

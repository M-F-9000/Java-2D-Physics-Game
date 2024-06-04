package handler;

import city.cs.engine.*;
import entity.*;
import game.GameLevel;

public class CollisionHandler implements CollisionListener {
    GameLevel world;
    Player player;
    @Override
    public void collide(CollisionEvent e) {
    }

    public CollisionHandler(GameLevel world) {
        this.world = world;
        this.player = world.getPlayer();
    }
}

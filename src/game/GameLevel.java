package game;

import city.cs.engine.World;
import entity.Player;
import handler.CollisionHandler;
import handler.KeyHandler;
import handler.PlayerCollisionHandler;

public abstract class GameLevel extends World {
    private static final KeyHandler keyH = new KeyHandler();
    private final Player player;
    private GameView view;
    private final Game game;

    public GameLevel(Game game) {
        super(60);
        player = new Player(this,keyH);
        this.game = game;
    }

    /**
     * Returns the player in the level.
     *
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Returns the key handler used by the player.
     *
     * @return keyH
     */
    public KeyHandler getKeyH() {
        return keyH;
    }


    /**
     * Creates a collision handler for the current world and adds it to the player.
     */
    public void createPlayer() {
        CollisionHandler collisionH = new PlayerCollisionHandler(this);

        player.addCollisionListener(collisionH);
    }

    /**
     * Places main structures of the level.
     * <p>
     * Draws the ground, stairs, walls, platforms, pipes, checkpoints, flag and castle.
     */
    public abstract void buildWorld();

    /**
     * Places collectibles in the level
     * <p>
     * Draws item boxes, and coins
     */
    public abstract void spawnItems();

    /**
     * Spawns all the enemies in the level.
     * <p>
     * Creates enemies, starts their walking and adds sensors to all enemies.
     */
    public abstract void spawnEnemies();

    /**
     * Respawns enemies in the world.
     * <p>
     * Destroys all enemy bodies and spawns them in again. Used when the player dies.
     */
    public abstract void respawnEnemies();

    /**
     * Returns game created in the main method.
     *
     * @return game
     */
    public Game getGame() {
        return game;
    }

    /**
     * Sets the new view to the new level.
     */
    public void setView(GameView view) {
        this.view = view;
    }

    /**
     * Gets the view associated with the world. Used to pass the view into objects created in the world.
     */
    public GameView getView() {
        return view;
    }
}

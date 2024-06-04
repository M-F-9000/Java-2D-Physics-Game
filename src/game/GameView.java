package game;

import city.cs.engine.*;
import entity.Player;
import handler.TimerHandler;
import handler.resetTimerHandler;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameView extends UserView {
    private static GameLevel world;
    static Game game;
    private final Timer timer;
    private Image background;
    private final ArrayList<Image> backgrounds = new ArrayList<>(3);
    private final JButton settingsButton;
    private static final Font statusFont = new Font("SansSerif", Font.PLAIN, 20);

    private static Player player;
    private static int score;
    private static int coins;
    private static int lives;

    private int level = 1;
    private static int time = 180;
    private int cameraUpperBound = 228;
    private int cameraLowerBound = 8;
    private static String gameOver = "";
    private static String totalScore = "";


    public GameView(GameLevel world, int width, int height) {
        super(world, width, height);

        this.setZoom(20);

        // Loading all the backgrounds
        backgrounds.add(new ImageIcon("data/textures/level1_bg.gif").getImage());
        backgrounds.add(new ImageIcon("data/textures/level2_bg.gif").getImage());
        backgrounds.add(new ImageIcon("data/textures/level3_bg.gif").getImage());
        background = backgrounds.get(0);

        settingsButton = new JButton("Settings");
        settingsButton.setBounds(10,10,100,25);

        // Setting the classes variables to match the player's variables
        player = world.getPlayer();
        GameView.world = world;
        game = world.getGame();
        score = Player.getScore();
        lives = Player.getLives();
        coins = Player.getCoins();

        // Creates timer to track and display time left for the player
        timer = new Timer(1000, new TimerHandler(this));
        timer.setInitialDelay(100);
        timer.start();

        // Makes button open the in-game settings GUI
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InGameSettingsPanel settingsP = new InGameSettingsPanel();

            }
        });
    }

    /**
     * Adds background and settings button to the view
     */
    @Override
    protected void paintBackground(Graphics2D g) {
        g.drawImage(background,0,0,1000,675,this);

        this.add(settingsButton);
    }

    /**
     * Draws player and game statistics to the view.
     */
    @Override
    protected void paintForeground(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.setFont(statusFont);
        g.drawString("Score:" + score,50, 50);
        g.drawString("Coins:" + coins,250, 50);
        g.drawString("Level:" + level,450, 50);
        g.drawString("Time:" + time,650, 50);
        g.drawString("Lives:" + lives,850, 50);

        g.drawString(gameOver, 450, 300);
        g.drawString(totalScore,425,350);

    }

    /**
     * Updates the camera position and displayed game statistics.
     */
    public void update() {

        float x = player.getPosition().x;
        if (x < cameraLowerBound) {
            x = cameraLowerBound;
        } else if (x > cameraUpperBound) {
            x = cameraUpperBound;
        }

        this.setCentre(new Vec2(x,12.5f));

        if (Player.getLives() > 0) {
            score = Player.getScore();
            lives = Player.getLives();
            coins = Player.getCoins();
        } else {
            lives = Player.getLives();
            gameOver = "GAME OVER!";
            world.stop();
        }
    }

    /**
     * Updates the time displayed on the view.
     */
    public void updateTime() {
        if (time > 0 && world.isRunning()) {
            time -= 1;
        } else if (time <= 0) {
            gameOver = "GAME OVER!";
            world.stop();
        }
    }

    /**
     * Shows that the player has won.
     * <p>
     * Method displays the total level score to the screen and plays level end music.
     * Determines whether the player has beat a level or has beat the game.
     */
    public void winGame() {
        player.removeAllImages();
        player.addImage(Player.getRight4());

        gameOver = "YOU WIN!";
        // Calculates the total level score based on: score, amount of coins picked up, lives and time remaining.
        int intScore = (score + lives*100 + time*10 + coins*50);
        totalScore = ("Level Score:" + intScore);
        Player.addFinalLevelScore(intScore);

        game.playEndMusic();

        if (!(world instanceof Level3)) {
            // Starts next level if player has not beat the final level
            game.goToNextLevel();
            level++;
        } else {
            // Teleports player to the win screen location if they have beat the final level
            game.endGame();
            player.setPosition(new Vec2(450,1));
            cameraUpperBound = 450;
            cameraLowerBound = 450;
            this.setZoom(10);
            timer.stop();

            Timer resetTimer = new Timer(3000,new resetTimerHandler(this));
            resetTimer.setRepeats(false);
            resetTimer.start();
        }
    }

    /**
     * Updates the world to use the new level to be displayed. Also gets the player in the new level.
     *
     * @param world new world to be displayed by the view
     */
    public void updateWorld(GameLevel world) {
        this.setWorld(world);
        GameView.world = world;
        player = world.getPlayer();
    }

    /**
     * Resets the time and player stats.
     */
    public void resetValues() {
        time = 180;
        player.resetValues();
    }

    /**
     * Clears the "You win" message and displayed score
     */
    public void resetScreen() {
        gameOver = "";
        totalScore = "";
    }

    /**
     * Updates the camera bound preventing the player from looking outside the level.
     *
     * @param x the position to set the camera's upper bound to
     */
    public void updateCameraUpperBound(int x) {
        cameraUpperBound = x;
    }

    /**
     * Updates the level number displayed
     *
     * @param level level counter to be displayed
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Updates the background image to be displayed by the view
     *
     * @param x the backgrounds array index
     */
    public void changeBackground(int x) {
        background = backgrounds.get(x);
    }
}


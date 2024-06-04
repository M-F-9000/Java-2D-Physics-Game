package game;

import city.cs.engine.SoundClip;
import entity.Coin;
import entity.Mushroom;
import entity.Player;
import handler.*;
import org.jbox2d.common.Vec2;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javax.swing.*;

public class Game {
    private static JFrame frame;
    private static MainMenuPanel controlP;
    private static double volume;
    private static SoundClip level1Music, level2Music, level3Music, endMusic;
    private final String name;
    private GameLevel level;
    private final GameView view;
    private final StepHandler stepH;

    public static void main(String[] args) {
        // Creation of the main menu GUI
        frame = new JFrame();
        controlP = new MainMenuPanel();

        frame.setPreferredSize(new Dimension(240,400));
        frame.add(controlP.getMainPanel());
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public Game(int startLevel) {
        // Retrieving values from the main menu GUI
        volume = (double) controlP.getVolume() / 10;
        this.name = controlP.getPlayerName();

        // Loading all the levels music
        try {
            level1Music = new SoundClip("data/sound/level1_theme.wav");
            level1Music.setVolume(volume);
            level2Music = new SoundClip("data/sound/level2_theme.wav");
            level2Music.setVolume(volume);
            level3Music = new SoundClip("data/sound/level3_theme.wav");
            level3Music.setVolume(volume);
            endMusic = new SoundClip("data/sound/smb_stage_clear.wav");
            endMusic.setVolume(volume);
        } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
            System.out.println(e);
        }

        // Loads appropriate level and music based level selected in the main menu settings.
        switch (startLevel) {
            case 1:
                level = new Level1(this);
                level1Music.loop();
                break;
            case 2:
                level = new Level2(this);
                level2Music.loop();
                break;
            case 3:
                level = new Level3(this);
                level3Music.loop();
                break;
        }

        // Creating the view
        view = new GameView(level, 1000, 600);
        level.setView(view);

        // Modifying the background and camera bounds of the view based on the starting level selected
        // Also sets the players level score to 0 for skipping levels
        switch (startLevel) {
            case 1:
                break;
            case 2:
                Player.addFinalLevelScore(0);
                view.updateCameraUpperBound(199);
                view.changeBackground(1);
                view.setLevel(2);
                break;
            case 3:
                Player.addFinalLevelScore(0);
                Player.addFinalLevelScore(0);
                view.updateCameraUpperBound(234);
                view.changeBackground(2);
                view.setLevel(3);
                break;
        }

        frame.remove(controlP.getMainPanel());
        frame.setPreferredSize(new Dimension(1000,600));
        frame.add(view);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        frame.pack();

        // Creating and adding listeners
        stepH = new StepHandler(view,level);
        MouseHandler mouseH = new MouseHandler();

        view.addKeyListener(level.getKeyH());
        level.addStepListener(stepH);
        view.addMouseListener(mouseH);

        // Starting the game
        level.start();
    }

    /**
     * Method to go to the next level.
     * <p>
     * Based on the current level the next level's: world, music and camera bounds will change.
     */
    public void goToNextLevel(){
        GameLevel nextLevel;
        if (level instanceof Level1){
            nextLevel = new Level2(this);
            view.updateCameraUpperBound(199);
            view.changeBackground(1);
            level1Music.stop();
            level2Music.loop();
        } else {
            nextLevel = new Level3(this);
            view.updateCameraUpperBound(234);
            view.changeBackground(2);
            level2Music.stop();
            level3Music.loop();
        }

        // Setting up the view for the new level
        view.setWorld(nextLevel);
        nextLevel.setView(view);
        view.updateWorld(nextLevel);
        view.resetValues();
        Timer timer = new Timer(3000, new resetTimerHandler(view));
        timer.setRepeats(false);
        timer.start();

        level.removeStepListener(stepH);
        stepH.updateLevel(nextLevel,view);
        nextLevel.addStepListener(stepH);

        Player.setLastCheckpoint(new Vec2(-10,0));

        level.stop();

        // Next level starts
        level = nextLevel;
        level.start();
    }


    /**
     * Method to end the game.
     * <p>
     * Method that ends the game once the player beats the final level.
     * It inputs the players level scores and final score, and outputs the names and scores stored as the contents of scores.txt.
     */
    public void endGame() {
        HighScoreHandler scoreH = new HighScoreHandler();
        try {
            ArrayList<Integer> scores = Player.getFinalLevelScore();
            scoreH.writeHighScore(name, scores.get(0), scores.get(1), scores.get(2),
                    scores.get(0) + scores.get(1) + scores.get(2));
            scoreH.readScores();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method to get the volume.
     *
     * @return returns the volume as a double
     */
    public static double getVolume() {
        return volume;
    }

    /**
     * Method to update the volume.
     * <p>
     * Updates all sounds used in the game to match the new volume given.
     *
     * @param volume as double
     */
    public static void updateVolume(double volume) {
        Game.volume = volume/10;
        level1Music.setVolume(Game.volume);
        level2Music.setVolume(Game.volume);
        level3Music.setVolume(Game.volume);
        endMusic.setVolume(Game.volume);

        Player.updateSounds(Game.volume);
        Coin.updateVolume(Game.volume);
        Mushroom.updateVolume(Game.volume);
    }

    /**
     * Method to play the level complete music
     */
    public void playEndMusic() {
        endMusic.play();
    }
}

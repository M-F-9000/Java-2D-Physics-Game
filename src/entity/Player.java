package entity;
import city.cs.engine.*;
import game.Game;
import game.GameLevel;
import handler.KeyHandler;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;

public class Player extends Entity {
    KeyHandler keyH;
    GameLevel world;

    private static final Shape characterShape = new BoxShape(0.75f, 0.75f);
    private static final float height = 1.5f;

    private static final BodyImage right1 = new BodyImage("data/player/mario_idle_right.png", height);
    private static final BodyImage right2 = new BodyImage("data/player/mario_run1_right.png", height);
    private static final BodyImage right3 = new BodyImage("data/player/mario_run2_right.png", height);
    private static final BodyImage right4 = new BodyImage("data/player/mario_jump_right.png", height);

    private static final BodyImage left1 = new BodyImage("data/player/mario_idle_left.png", height);
    private static final BodyImage left2 = new BodyImage("data/player/mario_run1_left.png", height);
    private static final BodyImage left3 = new BodyImage("data/player/mario_run2_left.png", height);
    private static final BodyImage left4 = new BodyImage("data/player/mario_jump_left.png", height);

    private static BodyImage currentImage = right1;

    private static SoundClip jumpSound;
    private static SoundClip dieSound;
    private static SoundClip stompSound;

    static {
        try {
            jumpSound = new SoundClip("data/sound/smb_jump-super.wav");
            jumpSound.setVolume(Game.getVolume());
            dieSound = new SoundClip("data/sound/smb_mariodie.wav");
            dieSound.setVolume(Game.getVolume());
            stompSound = new SoundClip("data/sound/smb_stomp.wav");
            stompSound.setVolume(Game.getVolume());
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    private static String direction = "right";
    private static boolean inAir = false;
    private static boolean isWalking = false;
    private static int spriteNum = 1;
    private static int spriteCounter = 0;

    private static int score = 0;
    private static int lives = 5;
    private static int coins = 0;
    private static ArrayList<Integer> finalLevelScores = new ArrayList<>(3);

    private static Vec2 lastCheckpoint = new Vec2(-10,0);

    public Player(GameLevel world, KeyHandler keyH) {
        super(world,characterShape,right1);

        this.world = world;
        this.keyH = keyH;
        this.setPosition(new Vec2(-10,0));
        this.setGravityScale(10);
    }



    public void update() {
        if (this.getPosition().y < -3) {
            world.respawnEnemies();
            updateLives(-1);
            dieSound.play();
            this.setPosition(getLastCheckpoint());
        } else if (keyH.upPressed || keyH.leftPressed || keyH.rightPressed) {
            isWalking = true;
            if (keyH.upPressed && !inAir) {
                keyH.upPressed = false;
                this.jump(40);
                jumpSound.play();
            } else if (keyH.leftPressed) {
                this.startWalking(-20);
                direction = "left";
            } else if (keyH.rightPressed) {
                this.startWalking(20);
                direction = "right";
            }

            spriteCounter++;

            if (spriteCounter > 6) {
                if (spriteNum < 2) {
                    spriteNum++;
                } else {
                    spriteNum = 1;
                }

                spriteCounter = 0;
            }
        } else {
            this.stopWalking();
            this.setLinearVelocity(new Vec2(0,this.getLinearVelocity().y));
            isWalking = false;
        }

        inAir = (this.getLinearVelocity().y > 0.1 || this.getLinearVelocity().y < -0.1);
    }


    public void draw() {
        switch(direction) {
            case "right":
                if (inAir) {
                    currentImage = right4;
                } else if (isWalking) {
                    if (spriteNum == 1) {
                        currentImage = right2;
                    } else if (spriteNum == 2) {
                        currentImage = right3;
                    }
                } else {
                    currentImage = right1;
                }
                break;

            case "left":
                if (inAir) {
                    currentImage = left4;
                } else  if (isWalking) {
                    if (spriteNum == 1) {
                        currentImage = left2;
                    } else if (spriteNum == 2) {
                        currentImage = left3;
                    }
                } else {
                    currentImage = left1;
                }
                break;
        }
        this.removeAllImages();
        this.addImage(currentImage);
    }


    public void updateScore(int points) {
        score += points;
    }

    public void resetValues() {
        score = 0;
        coins = 0;
    }

    public static int getScore() {
        return score;
    }

    public void updateLives(int num) {
        lives += num;
    }

    public static int getLives() {
        return lives;
    }

    public void updateCoins() {
        coins++;
    }

    public static int getCoins() {
        return coins;
    }

    public static BodyImage getRight4() {
        return right4;
    }

    public void playDeathSound() {
        dieSound.play();
    }

    public void playStompSound() {
        stompSound.play();
    }

    public static Vec2 getLastCheckpoint() {
        return lastCheckpoint;
    }

    public static void setLastCheckpoint(Vec2 lastCheckpoint) {
        Player.lastCheckpoint = lastCheckpoint;
    }

    public static ArrayList<Integer> getFinalLevelScore() {
        return finalLevelScores;
    }

    public static void addFinalLevelScore(Integer score) {
        finalLevelScores.add(score);
    }

    public static void updateSounds(double volume) {
        jumpSound.setVolume(volume);
        dieSound.setVolume(volume);
        stompSound.setVolume(volume);
    }
}
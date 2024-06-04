package game;

import entity.*;
import worldCreator.*;

public class Level2 extends GameLevel{
    private Enemy enemy1, enemy2, enemy3, enemy4, enemy5, enemy6;
    public Level2(Game game) {
        super(game);

        createPlayer();
        spawnItems();
        buildWorld();
        spawnEnemies();
    }

    public void spawnItems() {
        ItemBox itemBox = new ItemBox(this);
        itemBox.draw(7.5f,18,1);
        itemBox.draw(26,15,1);
        itemBox.draw(39,15,1);
        itemBox.draw(32.5f,15,2);
        itemBox.draw(85,7,1);
        itemBox.draw(87.5f,13,1);
        itemBox.draw(90,7,2);
        itemBox.draw(157,15,1);

        Coin coin = new Coin(this);
        coin.draw(2,6.5f,14);
        coin.draw(3,85.5f,8);
        coin.draw(3,150,14);
        coin.draw(3,160,14);
    }

    public void buildWorld() {
        Ground ground = new Ground(this);
        ground.draw(3,80,-20,0);
        ground.draw(3,40,75,0);
        ground.draw(10,25,145,6);
        ground.draw(3,40,185,0);

        Platform platform = new Platform(this);
        platform.draw(8,4,7);
        platform.draw(4,6,13);
        platform.draw(14,26,10);
        platform.draw(5,85,7);
        platform.draw(5,150,13);
        platform.draw(5,160,13);

        Stair stair = new Stair(this);
        stair.draw(3,21,1);
        stair.draw(7,108,1);
        stair.draw(5,199,1);
        stair.draw(1,210,1);

        Wall wall = new Wall(this);
        wall.draw(-18,1,30);
        wall.draw(225,1,30);

        Pipe pipe = new Pipe(this);
        pipe.draw(42,1,3);
        pipe.draw(58,1,3);
        pipe.draw(130,-2,8);

        CheckpointCreator checkpoint = new CheckpointCreator(this);
        checkpoint.setup(46,1);
        checkpoint.setup(107,1);
        checkpoint.setup(146,7);
        checkpoint.setup(186,1);

        Flag flag = new Flag(this);
        flag.draw(210,2,14);

        Castle castle = new Castle(this,215,4);
    }

    public void spawnEnemies() {
        enemy1 = new Goomba(this,6,1);
        enemy1.startWalking(enemy1.getSpeed());

        enemy2 = new Koopa(this,28,1);
        enemy2.setSpeed(10.5f);
        enemy2.startWalking(enemy2.getSpeed());

        enemy3 = new Goomba(this,81,1);
        enemy3.startWalking(enemy3.getSpeed());

        enemy4 = new Goomba(this,86,1);
        enemy4.startWalking(enemy4.getSpeed());

        enemy5 = new Goomba(this,91,1);
        enemy5.startWalking(enemy5.getSpeed());

        enemy6 = new Koopa(this,149,7);
        enemy6.setSpeed(11.6f);
        enemy6.startWalking(enemy6.getSpeed());

        SensorCreator sensor = new SensorCreator(this);
        sensor.setup(3,1,12,1,enemy1);
        sensor.setup(23,1,41,1,enemy2);
        sensor.setup(79,1,85,1,enemy3);
        sensor.setup(84,1,90,1,enemy4);
        sensor.setup(89,1,95,1,enemy5);
        sensor.setup(144,8,170,8,enemy6);
    }

    public void respawnEnemies() {
        enemy1.destroy();
        enemy2.destroy();
        enemy3.destroy();
        enemy4.destroy();
        enemy5.destroy();
        enemy6.destroy();
        spawnEnemies();
    }
}

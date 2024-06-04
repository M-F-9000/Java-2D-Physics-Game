package game;

import entity.*;
import worldCreator.*;

public class Level1 extends GameLevel {
    private Enemy enemy1,enemy2,enemy3,enemy4,enemy5,enemy6,enemy7;

    public Level1(Game game) {
        super(game);

        createPlayer();
        spawnItems();
        buildWorld();
        spawnEnemies();

    }
    public void spawnItems() {
        ItemBox itemBox = new ItemBox(this);
        itemBox.draw(-1,7,2);
        itemBox.draw(12,7,1);
        itemBox.draw(42,7,1);
        itemBox.draw(74.5f,25,1);
        itemBox.draw(88,7,2);
        itemBox.draw(110,7,1);
        itemBox.draw(115,7,1);
        itemBox.draw(120,7,1);
        itemBox.draw(115,13,2);
        itemBox.draw(196.5f,14,1);
        itemBox.draw(212.5f,14,1);

        Coin coin = new Coin(this);
        coin.draw(3,3.5f,14);
        coin.draw(3,82.5f,8);
        coin.draw(2,83.5f,1);
        coin.draw(4,160,16);
        coin.draw(3,202.5f,16);

    }

    public void buildWorld() {
        Ground ground = new Ground(this);
        ground.draw(3,70,-20,0);
        ground.draw(3,70,60,0);
        ground.draw(2,5,143,-1);
        ground.draw(3,70,185,0);

        Stair stair = new Stair(this);
        stair.draw(5,20,1);
        stair.draw(5,125,1);
        stair.draw(5,230,1);
        stair.draw(1,241,1);

        Wall wall = new Wall(this);
        wall.draw(35,1,5);
        wall.draw(-18,1,30);
        wall.draw(82,1,6);
        wall.draw(254,1,30);

        Platform platform = new Platform(this);
        platform.draw(12,0,7);
        platform.draw(6,3,13);
        platform.draw(10,70,13);
        platform.draw(6,72,19);
        platform.draw(6,82,7);
        platform.draw(3,96,7);
        platform.draw(15,156,10);
        platform.draw(7,160,15);
        platform.draw(8,193,9);
        platform.draw(8,209,9);
        platform.draw(5,202.5f,15);

        Pipe pipe = new Pipe(this);
        pipe.draw(48,1,3);
        pipe.draw(61,1,3);
        pipe.draw(145,0,6);
        pipe.draw(190,1,3);
        pipe.draw(205,1,3);
        pipe.draw(220,1,3);

        CheckpointCreator checkpoint = new CheckpointCreator(this);
        checkpoint.setup(61,4);
        checkpoint.setup(124,1);
        checkpoint.setup(186,1);

        Flag flag = new Flag(this);
        flag.draw(241,2,14);

        Castle castle = new Castle(this,246,4);

    }


    public void spawnEnemies() {
        enemy1 = new Goomba(this,6,8);
        enemy1.startWalking(enemy1.getSpeed());

        enemy2 = new Goomba(this,30,1);
        enemy2.startWalking(enemy2.getSpeed());

        enemy3 = new Goomba(this,72,14);
        enemy3.setSpeed(7);
        enemy3.startWalking(enemy3.getSpeed());

        enemy4 = new Goomba(this,65,1);
        enemy4.setSpeed(10.5f);
        enemy4.startWalking(enemy4.getSpeed());

        enemy5 = new Goomba(this,158,11);
        enemy5.setSpeed(7);
        enemy5.startWalking(enemy5.getSpeed());

        enemy6 = new Goomba(this,193,1);
        enemy6.setSpeed(7);
        enemy6.startWalking(enemy6.getSpeed());

        enemy7 = new Goomba(this,208,1);
        enemy7.setSpeed(7);
        enemy7.startWalking(enemy7.getSpeed());

        SensorCreator sensor = new SensorCreator(this);
        sensor.setup(-1,8,12,8,enemy1);
        sensor.setup(24.2f,1,35,1,enemy2);
        sensor.setup(69,14,80,14,enemy3);
        sensor.setup(62.2f,1,81.5f,1,enemy4);
        sensor.setup(155,11,171,11,enemy5);
        sensor.setup(191,1,204,1,enemy6);
        sensor.setup(206,1,219,1,enemy7);
    }

    public void respawnEnemies() {
        enemy1.destroy();
        enemy2.destroy();
        enemy3.destroy();
        enemy4.destroy();
        enemy5.destroy();
        enemy6.destroy();
        enemy7.destroy();
        spawnEnemies();
    }

}

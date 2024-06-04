package game;

import entity.*;
import worldCreator.*;
public class Level3 extends GameLevel{
    private Enemy enemy1, enemy2, enemy3, enemy4, enemy5, enemy6, enemy7;
    public Level3(Game game) {
        super(game);

        createPlayer();
        spawnItems();
        buildWorld();
        spawnEnemies();
    }

    public void buildWorld() {
        Ground ground = new Ground(this);
        ground.draw(3,40,-20,0);
        ground.draw(6,40,35,3);
        ground.draw(6,46,87,3);
        ground.draw(3,40,90,27);
        ground.draw(18,3,90,24);
        ground.draw(18,3,127,24);
        ground.draw(3,12,93,9);
        ground.draw(3,12,115,9);
        ground.draw(3,63,145,0);
        ground.draw(3,40,220,0);

        Platform platform = new Platform(this);
        platform.draw(10,49,13);
        platform.draw(10,105,14);
        platform.draw(5,93,18);
        platform.draw(5,122,18);
        platform.draw(7,170,10);
        platform.draw(7,185,10);
        platform.draw(7,200,10);

        Stair stair = new Stair(this);
        stair.draw(5,15,1);
        stair.draw(3,40,4);
        stair.draw(5,155,1);
        stair.draw(5,234,1);
        stair.draw(1,245,1);

        Wall wall = new Wall(this);
        wall.draw(-18,1,30);
        wall.draw(207,1,10);
        wall.draw(260,1,30);

        Pipe pipe = new Pipe(this);
        pipe.draw(44,4,3);
        pipe.draw(64,4,3);

        CheckpointCreator checkpoint = new CheckpointCreator(this);
        checkpoint.setup(36,4);
        checkpoint.setup(88,4);
        checkpoint.setup(150,1);
        checkpoint.setup(206,11);

        Flag flag = new Flag(this);
        flag.draw(245,2,14);

        Castle castle = new Castle(this,250,4);


        // Below is the creation of the "You win!" sign at the end screen
        ground.draw(20,102,400,0);
        wall.draw(400,0,30);
        wall.draw(500,0,30);

        wall.draw(420,10,5);
        platform.draw(7,417,15);
        wall.draw(416,15,5);
        wall.draw(424,15,5);

        platform.draw(6,428,10);
        platform.draw(6,428,19);
        wall.draw(427,10,10);
        wall.draw(434,10,10);

        platform.draw(6,438,10);
        wall.draw(437,10,10);
        wall.draw(444,10,10);

        wall.draw(460,11,9);
        platform.draw(2,461,10);
        wall.draw(463,11,4);
        platform.draw(2,464,10);
        wall.draw(466,11,9);

        wall.draw(469,10,8);
        wall.draw(469,19,1);

        wall.draw(472,10,10);
        wall.draw(473,18,1);
        wall.draw(474,17,1);
        wall.draw(475,16,1);
        wall.draw(476,10,10);

        wall.draw(479,12,8);
        wall.draw(479,10,1);

    }

    public void spawnItems() {
        ItemBox itemBox = new ItemBox(this);
        itemBox.draw(49,13,1);
        itemBox.draw(58,13,1);
        itemBox.draw(97,18,2);
        itemBox.draw(122,18,1);
        itemBox.draw(173,16,2);
        itemBox.draw(188,16,1);
        itemBox.draw(203,16,1);

        Coin coin = new Coin(this);
        coin.draw(3,51.5f,14);
        coin.draw(3,107.5f,15);
        coin.draw(1,93,19);
        coin.draw(1,126,19);
    }

    public void spawnEnemies() {
        enemy1 = new Koopa(this,47,4);
        enemy1.setSpeed(10.6f);
        enemy1.startWalking(enemy1.getSpeed());

        enemy2 = new Goomba(this,94,10);
        enemy2.startWalking(enemy2.getSpeed());

        enemy3 = new Goomba(this,116,10);
        enemy3.startWalking(enemy3.getSpeed());

        enemy4 = new Goomba(this,171,11);
        enemy4.startWalking(enemy4.getSpeed());

        enemy5 = new Goomba(this,186,11);
        enemy5.startWalking(enemy5.getSpeed());

        enemy6 = new Goomba(this,201,11);
        enemy6.startWalking(enemy6.getSpeed());

        enemy7 = new Koopa(this,163,1);
        enemy7.setSpeed(10.6f);
        enemy7.startWalking(enemy7.getSpeed());

        SensorCreator sensor = new SensorCreator(this);
        sensor.setup(45,5,63,5,enemy1);
        sensor.setup(92,11,105,11,enemy2);
        sensor.setup(114,11,127,11,enemy3);
        sensor.setup(169,11,177,11,enemy4);
        sensor.setup(184,11,192,11,enemy5);
        sensor.setup(199,11,207,11,enemy6);
        sensor.setup(160,1,207,1,enemy7);
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

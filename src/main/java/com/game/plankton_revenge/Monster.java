package com.game.plankton_revenge;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Random;

public class Monster extends Sprite{
    public final static int MONSTER_WIDTH = 40;
    public final static int MONSTER_HEIGHT = 40;
    private static int counter = 0;
    private ArrayList<Image> monstersImages = new ArrayList<>();
    private boolean alive;
    private int direction;
    private double speed;
    private double movingTimeInSeconds;
    private long moveTimer;

    private boolean warning;
    private boolean danger;


    Monster(int x, int y){
        super(x,y);
        this.width = Monster.MONSTER_WIDTH;
        this.height = Monster.MONSTER_HEIGHT;
        this.alive = true;
        this.moveTimer = System.nanoTime();
        this.warning = false;
        this.danger = false;

        Image spongebob = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\spongebob.png");
        this.monstersImages.add(spongebob);
        Image patrick = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\patrick.png");
        this.monstersImages.add(patrick);
        Image sandy = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\sandy.png");
        this.monstersImages.add(sandy);
        Image squidward = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\squidward.png");
        this.monstersImages.add(squidward);
        Image crabs = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\crabs.png");
        this.monstersImages.add(crabs);
        Image hero = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\hero.png");
        this.monstersImages.add(hero);
        Image garry = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\garry.png");
        this.monstersImages.add(garry);
        Image whale = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\whale.png");
        this.monstersImages.add(whale);
        Image puff = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\puff.png");
        this.monstersImages.add(puff);
        Image jellyfish = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\jellyfish.png");
        this.monstersImages.add(jellyfish);

        this.loadImage(monstersImages.get(Monster.counter % GameTimer.MAX_NUM_MONSTERS));

        this.speed = 120.0/(this.width+this.collideTrue);

        setRandomDirection();
        Monster.counter += 1;
    }

    public void move(){
        switch (this.direction){
            case 1: // Right Bound +x
                if(this.movingTimeInSeconds >= (System.nanoTime() - this.moveTimer)/1000000000.0){
                    if(this.x <= GameStage.MAP_WIDTH - this.width + GameTimer.GAME_BOUND - GameTimer.getMoveX()){
                        this.x += this.speed;
                    }else{
                        this.moveTimer = System.nanoTime();
                        setRandomDirection();
                    }
                }else{
                    this.moveTimer = System.nanoTime();
                    setRandomDirection();
                }
                break;
            case 2: // Left Bound -x
                if(this.movingTimeInSeconds >= (System.nanoTime() - this.moveTimer)/1000000000.0){
                    if(this.x >= GameTimer.GAME_BOUND - GameTimer.getMoveX()){
                        this.x -= this.speed;
                    }else{
                        this.moveTimer = System.nanoTime();
                        setRandomDirection();
                    }
                }else{
                    this.moveTimer = System.nanoTime();
                    setRandomDirection();
                }
                break;
            case 3: // Top Bound +y
                if(this.movingTimeInSeconds >= (System.nanoTime() - this.moveTimer)/1000000000.0){
                    if(this.y >= GameTimer.GAME_BOUND - GameTimer.getMoveY()){
                        this.y -= this.speed;
                    }else{
                        this.moveTimer = System.nanoTime();
                        setRandomDirection();
                    }
                }else{
                    this.moveTimer = System.nanoTime();
                    setRandomDirection();
                }
                break;
            case 4: // Bottom Bound -y
                if(this.movingTimeInSeconds >= (System.nanoTime() - this.moveTimer)/1000000000.0){
                    if(this.y <= GameStage.MAP_HEIGHT - this.width + GameTimer.GAME_BOUND - GameTimer.getMoveY()){
                        this.y += this.speed;
                    }else{
                        this.moveTimer = System.nanoTime();
                        setRandomDirection();
                    }
                }else{
                    this.moveTimer = System.nanoTime();
                    setRandomDirection();
                }
                break;
            case 5: // Top Right Bound +x -y
                if(this.movingTimeInSeconds >= (System.nanoTime() - this.moveTimer)/1000000000.0){
                    if(this.x <= GameStage.MAP_WIDTH - this.width + GameTimer.GAME_BOUND - GameTimer.getMoveX() &&
                            this.y >= GameTimer.GAME_BOUND - GameTimer.getMoveY()){
                        this.x += this.speed;
                        this.y -= this.speed;
                    }else{
                        this.moveTimer = System.nanoTime();
                        setRandomDirection();
                    }
                }else{
                    this.moveTimer = System.nanoTime();
                    setRandomDirection();
                }
                break;
            case 6: // Top Left Bound -x -y
                if(this.movingTimeInSeconds >= (System.nanoTime() - this.moveTimer)/1000000000.0){
                    if(this.x >= GameTimer.GAME_BOUND - GameTimer.getMoveX()
                            && this.y >= GameTimer.GAME_BOUND - GameTimer.getMoveY()){
                        this.x -= this.speed;
                        this.y -= this.speed;
                    }else{
                        this.moveTimer = System.nanoTime();
                        setRandomDirection();
                    }
                }else{
                    this.moveTimer = System.nanoTime();
                    setRandomDirection();
                }
                break;
            case 7: // Lower Left Bound -x +y
                if(this.movingTimeInSeconds >= (System.nanoTime() - this.moveTimer)/1000000000.0){
                    if(this.x >= GameTimer.GAME_BOUND - GameTimer.getMoveX()
                            && this.y <= GameStage.MAP_HEIGHT - this.width + GameTimer.GAME_BOUND - GameTimer.getMoveY()){
                        this.x -= this.speed;
                        this.y += this.speed;
                    }else{
                        this.moveTimer = System.nanoTime();
                        setRandomDirection();
                    }
                }else{
                    this.moveTimer = System.nanoTime();
                    setRandomDirection();
                }
                break;
            case 8: // Lower Right Bound +x +y
                if(this.movingTimeInSeconds >= (System.nanoTime() - this.moveTimer)/1000000000.0){
                    if(this.x <= GameStage.MAP_WIDTH - this.width + GameTimer.GAME_BOUND - GameTimer.getMoveX()
                            && this.y <= GameStage.MAP_HEIGHT - this.height + GameTimer.GAME_BOUND - GameTimer.getMoveY()){
                        this.x += this.speed;
                        this.y += this.speed;
                    }else{
                        this.moveTimer = System.nanoTime();
                        setRandomDirection();
                    }
                }else{
                    this.moveTimer = System.nanoTime();
                    setRandomDirection();
                }
                break;
        }
    }

    public void checkPlanktonCollision(Plankton plankton){
        if (this.collidesWith(plankton) && plankton.isAlive()){
            if(this.width+this.collideTrue < plankton.width +plankton.collideTrue
                || plankton.isImmune()){

                if(plankton.isImmune()){
                    return;
                }

                double tempMonsterWidth = this.width;
                double tempCollideTrue = plankton.collideTrue;
                double currentCollideTrue = plankton.collideTrue;

                for(int i = 1; i <= this.width+this.collideTrue-20; i+=2){
                    this.collideTrue += (-1 * i);
                }

                this.die();

                for(int i = 1; i <= tempMonsterWidth; i++){
                    if(tempCollideTrue <= currentCollideTrue+tempMonsterWidth){
                        plankton.collideTrue += i;
                        tempCollideTrue += i;
                    }
                }

                plankton.addEnemyDefeated(1);
                GameTimer.planktonWidth = plankton.width+plankton.collideTrue;
            }else if(this.width+this.collideTrue >= plankton.width+plankton.collideTrue
                && !plankton.isImmune()){
                plankton.die();
                GameTimer.gameOverTime = System.nanoTime();
                this.collideTrue += plankton.width+plankton.collideTrue;
            }
        }else if(!plankton.isAlive()){
            return;
        }

        Rectangle2D warningRect1 = this.getWarningBounds();
        Rectangle2D warningRect2 = plankton.getWarningBounds();

        Rectangle2D dangerRect1 = this.getDangerBounds();
        Rectangle2D dangerRect2 = plankton.getDangerBounds();

        if(warningRect1.intersects(warningRect2) && !dangerRect1.intersects(dangerRect2)
            && this.width+this.collideTrue >= GameTimer.planktonWidth && !plankton.isImmune()){
            this.setWarning(true);
        }else if(dangerRect1.intersects(dangerRect2) && this.width+this.collideTrue >= GameTimer.planktonWidth
            && !plankton.isImmune()){
            this.warning = false;
            this.danger = true;
        }else if(!warningRect1.intersects(warningRect2) && !dangerRect1.intersects(dangerRect2)){
            this.warning = false;
            this.danger = false;
        }
    }

    public void checkMonsterCollision(ArrayList<Monster> monsters){
        if(!this.alive){
            return;
        }
        for(Monster m: monsters){
            if(this != m){
                if(this.alive && m.isAlive() && this.collidesWith(m)){
                    if(this.width+this.collideTrue <= m.width+m.collideTrue){

                        double tempMonsterWidth = this.width;
                        double tempCollideTrue = m.collideTrue;
                        double currentCollideTrue = m.collideTrue;

                        for(int i = 1; i <= this.width+this.collideTrue-20; i+=2){
                            this.collideTrue += (-1 * i);
                        }

                        this.die();

                        for(int i = 1; i <= tempMonsterWidth; i++){
                            if(tempCollideTrue <= currentCollideTrue+tempMonsterWidth){
                                m.collideTrue += i;
                                tempCollideTrue += i;
                            }
                        }
                    }else{
                        double tempMonsterWidth = m.width;
                        double tempCollideTrue = this.collideTrue;
                        double currentCollideTrue = this.collideTrue;

                        for(int i = 1; i <= m.width+m.collideTrue-20; i+=2){
                            m.collideTrue += (-1 * i);
                        }

                        m.die();

                        for(int i = 1; i <= tempMonsterWidth; i++){
                            if(tempCollideTrue <= currentCollideTrue+tempMonsterWidth){
                                this.collideTrue += i;
                                tempCollideTrue += i;
                            }
                        }
                    }
                }
            }
        }
    }

    private void setRandomDirection(){
        Random r = new Random();
        this.direction = r.nextInt(8)+1;
        this.movingTimeInSeconds = r.nextInt(10) + 1;
    }

    private void die(){
        this.alive = false;
        this.vanish();
    }

    public void setSpeed(double speed){
        this.speed = speed;
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean isWarning() {
        return warning;
    }

    public void setWarning(boolean warning) {
        this.warning = warning;
    }

    public boolean isDanger() {
        return danger;
    }
}

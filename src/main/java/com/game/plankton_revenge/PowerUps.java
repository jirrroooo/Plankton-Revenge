package com.game.plankton_revenge;

import javafx.scene.image.Image;

public class PowerUps extends Sprite{
    public final static int POWER_UPS_WIDTH = 20;
    public final static int POWER_UPS_HEIGHT = 20;
    public final static Image IMMUNITY = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\immunity.png");
    public final static Image SPEED_BOOSTER = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\booster.png");

    private static int counter = 0;
    private long spawnTime;
    private boolean alive;
    private boolean immunity = false;

    PowerUps(int x, int y, long time){
        super(x,y);
        this.width = PowerUps.POWER_UPS_WIDTH;
        this.height = PowerUps.POWER_UPS_HEIGHT;
        this.alive = true;
        this.spawnTime = time;
        if(PowerUps.counter % 2 == 0){
            this.immunity = true;
            this.loadImage(IMMUNITY);
        }else{
            this.loadImage(SPEED_BOOSTER);
        }
        PowerUps.counter++;
    }

    public void checkPlanktonCollision(Plankton plankton){
        if(!this.alive){
            return;
        }

        if (plankton.isAlive() && this.collidesWith(plankton)){
            if(this.immunity){
                plankton.setImmune(true);
                plankton.setPlanktonTypeSelector(Plankton.IMMUNE);
            }else{
                plankton.setBoosted(true);
                plankton.setPlanktonTypeSelector(Plankton.BOOSTED);
                GameTimer.setPlanktonBoosted(true);
            }
            plankton.setPowerUpSpawnTime(System.nanoTime());
            this.die();
        }
    }


    private void die(){
        this.alive = false;
    }

    public boolean isAlive() {
        return this.alive;
    }

    public long getSpawnTime(){
        return this.spawnTime;
    }

    public void setAlive(boolean condition){
        this.alive = condition;
    }
}

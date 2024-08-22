package com.game.plankton_revenge;

import javafx.scene.image.Image;
import java.util.ArrayList;


public class Food extends Sprite{
    private boolean alive;

    public final static int FOOD_WIDTH = 20;
    public final static int FOOD_HEIGHT = 20;
    public final static int ADDED_SIZE_FROM_FOOD = 10;
    public final static Image FOOD_IMAGE = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\krabypatty.png");

    Food(int x, int y){
        super(x,y);
        this.width = Food.FOOD_WIDTH;
        this.height = Food.FOOD_HEIGHT;
        this.alive = true;
        this.loadImage(Food.FOOD_IMAGE);
    }

    public void checkPlanktonCollision(Plankton plankton){
        if(!this.alive){
            return;
        }

        if (plankton.isAlive() && this.collidesWith(plankton)){
            this.die();
            plankton.collideTrue += Food.ADDED_SIZE_FROM_FOOD;
            GameTimer.planktonWidth = plankton.width+plankton.collideTrue;
            plankton.addFoodEaten(1);
        }
    }

    public void checkMonsterCollision(ArrayList<Monster> monsters){
        if(!this.alive){
            return;
        }
        for(Monster m: monsters){
            if(this.alive && m.isAlive() && this.collidesWith(m)){
                this.die();
                m.collideTrue += Food.ADDED_SIZE_FROM_FOOD;
            }
        }
    }

    private void die(){
        this.alive = false;
    }

    public boolean isAlive() {
        return this.alive;
    }
}

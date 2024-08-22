package com.game.plankton_revenge;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Plankton extends Sprite{
    private boolean alive;
    private final ArrayList<Image> planktonImages = new ArrayList<>();
    private int planktonTypeSelector = REGULAR;
    private int foodEaten = 0;
    private int enemyDefeated = 0;
    private long powerUpSpawnTime;
    private final long spawnTime;
    private boolean grouped = false;
    private int groupedNum = 0;
    private boolean boosted = false;
    private boolean immune = false;
    private int planktonWidth = 40;

    public final static int REGULAR = 0;
    public final static int BOOSTED = 1;
    public final static int IMMUNE = 2;

    public final static int REGULAR_1 = 3;
    public final static int REGULAR_2 = 4;
    public final static int REGULAR_3 = 5;
    public final static int REGULAR_4 = 6;
    public final static int REGULAR_5 = 7;
    public final static int REGULAR_6 = 8;
    public final static int REGULAR_7 = 9;
    public final static int REGULAR_8 = 10;
    public final static int REGULAR_9 = 11;
    public final static int REGULAR_10 = 12;
    public final static int REGULAR_11 = 13;
    public final static int REGULAR_12 = 14;

    public final static int IMMUNE_1 = 15;
    public final static int IMMUNE_2 = 16;
    public final static int IMMUNE_3 = 17;
    public final static int IMMUNE_4 = 18;
    public final static int IMMUNE_5 = 19;
    public final static int IMMUNE_6 = 20;
    public final static int IMMUNE_7 = 21;
    public final static int IMMUNE_8 = 22;
    public final static int IMMUNE_9 = 23;
    public final static int IMMUNE_10 = 24;
    public final static int IMMUNE_11 = 25;
    public final static int IMMUNE_12 = 26;

    public final static int BOOSTED_1 = 27;
    public final static int BOOSTED_2 = 28;
    public final static int BOOSTED_3 = 29;
    public final static int BOOSTED_4 = 30;
    public final static int BOOSTED_5 = 31;
    public final static int BOOSTED_6 = 32;
    public final static int BOOSTED_7 = 33;
    public final static int BOOSTED_8 = 34;
    public final static int BOOSTED_9 = 35;
    public final static int BOOSTED_10 = 36;
    public final static int BOOSTED_11 = 37;
    public final static int BOOSTED_12 = 38;

    public final static Image PLANKTON_REGULAR = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\plankton1.png");
    public final static Image PLANKTON_BOOSTED = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\planktonBoosted.png");
    public final static Image PLANKTON_IMMUNE = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\planktonImmune.png");

    public final static Image R1 = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\R1.png");
    public final static Image R2 = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\R2.png");
    public final static Image R3 = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\R3.png");
    public final static Image R4 = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\R4.png");
    public final static Image R5 = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\R5.png");
    public final static Image R6 = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\R6.png");
    public final static Image R7 = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\R7.png");
    public final static Image R8 = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\R8.png");
    public final static Image R9 = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\R9.png");
    public final static Image R10 = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\R10.png");
    public final static Image R11 = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\R11.png");
    public final static Image R12 = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\R12.png");

    public final static Image I1 = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\I1.png");
    public final static Image I2 = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\I2.png");
    public final static Image I3 = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\I3.png");
    public final static Image I4 = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\I4.png");
    public final static Image I5 = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\I5.png");
    public final static Image I6 = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\I6.png");
    public final static Image I7 = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\I7.png");
    public final static Image I8 = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\I8.png");
    public final static Image I9 = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\I9.png");
    public final static Image I10 = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\I10.png");
    public final static Image I11 = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\I11.png");
    public final static Image I12 = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\I12.png");

    public final static Image B1 = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\B1.png");
    public final static Image B2 = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\B2.png");
    public final static Image B3 = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\B3.png");
    public final static Image B4 = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\B4.png");
    public final static Image B5 = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\B5.png");
    public final static Image B6 = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\B6.png");
    public final static Image B7 = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\B7.png");
    public final static Image B8 = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\B8.png");
    public final static Image B9 = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\B9.png");
    public final static Image B10 = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\B10.png");
    public final static Image B11 = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\B11.png");
    public final static Image B12 = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\B12.png");


    Plankton(int x, int y, long spawnTime){
        super(x,y);
        this.alive = true;
        this.width = this.planktonWidth;
        this.height = this.planktonWidth;
        this.spawnTime = spawnTime;

        planktonImages.add(PLANKTON_REGULAR);
        planktonImages.add(PLANKTON_BOOSTED);
        planktonImages.add(PLANKTON_IMMUNE);

        planktonImages.add(R1);
        planktonImages.add(R2);
        planktonImages.add(R3);
        planktonImages.add(R4);
        planktonImages.add(R5);
        planktonImages.add(R6);
        planktonImages.add(R7);
        planktonImages.add(R8);
        planktonImages.add(R9);
        planktonImages.add(R10);
        planktonImages.add(R11);
        planktonImages.add(R12);

        planktonImages.add(I1);
        planktonImages.add(I2);
        planktonImages.add(I3);
        planktonImages.add(I4);
        planktonImages.add(I5);
        planktonImages.add(I6);
        planktonImages.add(I7);
        planktonImages.add(I8);
        planktonImages.add(I9);
        planktonImages.add(I10);
        planktonImages.add(I11);
        planktonImages.add(I12);

        planktonImages.add(B1);
        planktonImages.add(B2);
        planktonImages.add(B3);
        planktonImages.add(B4);
        planktonImages.add(B5);
        planktonImages.add(B6);
        planktonImages.add(B7);
        planktonImages.add(B8);
        planktonImages.add(B9);
        planktonImages.add(B10);
        planktonImages.add(B11);
        planktonImages.add(B12);

        this.loadImage(planktonImages.get(planktonTypeSelector));
    }

    public void move() {
        this.x = (GameStage.WINDOW_WIDTH/2.0)-((this.width+this.collideTrue)/2.0);
        this.y = (GameStage.WINDOW_HEIGHT/2.0)-((this.width+this.collideTrue)/2.0);
    }

    public void updatePlanktonImage(boolean isAlive){
        if(isAlive){
            this.loadImage(planktonImages.get(planktonTypeSelector));
        }else{
            this.loadImage(null);
        }

    }

    public void die(){
        this.alive = false;
    }

    public boolean isAlive(){
        return this.alive;
    }

    public void setPlanktonTypeSelector(int selector){
        if(!this.grouped){
            this.planktonTypeSelector = selector;
        }else if(selector == Plankton.REGULAR){
            switch (this.groupedNum){
                case 1:
                    this.planktonTypeSelector = Plankton.REGULAR_1;
                    break;
                case 2:
                    this.planktonTypeSelector = Plankton.REGULAR_2;
                    break;
                case 3:
                    this.planktonTypeSelector = Plankton.REGULAR_3;
                    break;
                case 4:
                    this.planktonTypeSelector = Plankton.REGULAR_4;
                    break;
                case 5:
                    this.planktonTypeSelector = Plankton.REGULAR_5;
                    break;
                case 6:
                    this.planktonTypeSelector = Plankton.REGULAR_6;
                    break;
                case 7:
                    this.planktonTypeSelector = Plankton.REGULAR_7;
                    break;
                case 8:
                    this.planktonTypeSelector = Plankton.REGULAR_8;
                    break;
                case 9:
                    this.planktonTypeSelector = Plankton.REGULAR_9;
                    break;
                case 10:
                    this.planktonTypeSelector = Plankton.REGULAR_10;
                    break;
                case 11:
                    this.planktonTypeSelector = Plankton.REGULAR_11;
                    break;
                case 12:
                    this.planktonTypeSelector = Plankton.REGULAR_12;
                    break;
            }
        }else if(selector == Plankton.IMMUNE){
            switch (this.groupedNum){
                case 1:
                    this.planktonTypeSelector = Plankton.IMMUNE_1;
                    break;
                case 2:
                    this.planktonTypeSelector = Plankton.IMMUNE_2;
                    break;
                case 3:
                    this.planktonTypeSelector = Plankton.IMMUNE_3;
                    break;
                case 4:
                    this.planktonTypeSelector = Plankton.IMMUNE_4;
                    break;
                case 5:
                    this.planktonTypeSelector = Plankton.IMMUNE_5;
                    break;
                case 6:
                    this.planktonTypeSelector = Plankton.IMMUNE_6;
                    break;
                case 7:
                    this.planktonTypeSelector = Plankton.IMMUNE_7;
                    break;
                case 8:
                    this.planktonTypeSelector = Plankton.IMMUNE_8;
                    break;
                case 9:
                    this.planktonTypeSelector = Plankton.IMMUNE_9;
                    break;
                case 10:
                    this.planktonTypeSelector = Plankton.IMMUNE_10;
                    break;
                case 11:
                    this.planktonTypeSelector = Plankton.IMMUNE_11;
                    break;
                case 12:
                    this.planktonTypeSelector = Plankton.IMMUNE_12;
                    break;
            }
        }else if(selector == Plankton.BOOSTED){
            switch (this.groupedNum){
                case 1:
                    this.planktonTypeSelector = Plankton.BOOSTED_1;
                    break;
                case 2:
                    this.planktonTypeSelector = Plankton.BOOSTED_2;
                    break;
                case 3:
                    this.planktonTypeSelector = Plankton.BOOSTED_3;
                    break;
                case 4:
                    this.planktonTypeSelector = Plankton.BOOSTED_4;
                    break;
                case 5:
                    this.planktonTypeSelector = Plankton.BOOSTED_5;
                    break;
                case 6:
                    this.planktonTypeSelector = Plankton.BOOSTED_6;
                    break;
                case 7:
                    this.planktonTypeSelector = Plankton.BOOSTED_7;
                    break;
                case 8:
                    this.planktonTypeSelector = Plankton.BOOSTED_8;
                    break;
                case 9:
                    this.planktonTypeSelector = Plankton.BOOSTED_9;
                    break;
                case 10:
                    this.planktonTypeSelector = Plankton.BOOSTED_10;
                    break;
                case 11:
                    this.planktonTypeSelector = Plankton.BOOSTED_11;
                    break;
                default: // Case 12
                    this.planktonTypeSelector = Plankton.BOOSTED_12;
                    break;
            }
        }
    }

    public long getPowerUpSpawnTime() {
        return powerUpSpawnTime;
    }

    public void setPowerUpSpawnTime(long powerUpSpawnTime) {
        this.powerUpSpawnTime = powerUpSpawnTime;
    }

    public int getFoodEaten() {
        return foodEaten;
    }

    public void addFoodEaten(int foodEaten) {
        this.foodEaten += foodEaten;
    }

    public int getEnemyDefeated() {
        return enemyDefeated;
    }

    public void addEnemyDefeated(int enemyDefeated) {
        this.enemyDefeated += enemyDefeated;
    }

    public int getPlanktonWidth() {
        return this.planktonWidth;
    }

    public long getSpawnTime(){
        return this.spawnTime;
    }

    public boolean isGrouped() {
        return grouped;
    }

    public void setGrouped(boolean grouped) {
        this.grouped = grouped;
    }

    public int getGroupedNum() {
        return groupedNum;
    }

    public void setGroupedNum(int groupedNum) {
        this.groupedNum = groupedNum;
    }

    public boolean isBoosted() {
        return boosted;
    }

    public void setBoosted(boolean boosted) {
        this.boosted = boosted;
    }

    public boolean isImmune() {
        return immune;
    }

    public void setImmune(boolean immune) {
        this.immune = immune;
    }
}

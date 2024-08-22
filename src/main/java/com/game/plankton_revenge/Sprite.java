package com.game.plankton_revenge;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Sprite {
    private final int warningRadius = 200;
    private final int terrorRadius = 80;
    protected Image img;
    protected double x, y, dx, dy;
    protected boolean visible;
    protected double width;
    protected double height;
    protected int collideTrue = 0;

    public Sprite(int xPos, int yPos){
        this.x = xPos;
        this.y = yPos;
        this.visible = true;
        this.dx = 0;
        this.dy = 0;
    }

    //method to set the object's image
    protected void loadImage(Image img){
        try{
            this.img = img;
            img.isSmooth();
            this.setSize();
        } catch(Exception e){}
    }

    //method to set the image to the image view node
    public void render(GraphicsContext gc){
        gc.drawImage(this.img, this.x+this.dx, this.y+this.dy, width+collideTrue, height+collideTrue);
    }

    //method to set the object's width and height properties
    private void setSize(){
        if(this instanceof Plankton && ((Plankton) this).isGrouped()){
            this.width = Monster.MONSTER_WIDTH + ((Plankton) this).getGroupedNum()*10;
            this.height = Monster.MONSTER_HEIGHT + ((Plankton) this).getGroupedNum()*10;
        }else if((this instanceof Plankton && !((Plankton) this).isGrouped()) || this instanceof Monster || this instanceof PowerUps){
            this.width = Monster.MONSTER_WIDTH;
            this.height = Monster.MONSTER_HEIGHT;
        }else if(this instanceof Food){
            this.width = Food.FOOD_WIDTH;
            this.height = Food.FOOD_HEIGHT;
        }

    }
    //method that will check for collision of two sprites
    public boolean collidesWith(Sprite rect2)	{
        Rectangle2D rectangle1 = this.getBounds();
        Rectangle2D rectangle2 = rect2.getBounds();

        return rectangle1.intersects(rectangle2);
    }

    //method that will return the bounds of an image
    private Rectangle2D getBounds(){
        return new Rectangle2D(this.x+this.dx, this.y+this.dy, this.width+this.collideTrue, this.height+this.collideTrue);
    }

    public Rectangle2D getWarningBounds(){
        return new Rectangle2D(this.x+this.dx+200, this.y+this.dy+200, this.width+this.collideTrue+this.warningRadius, this.height+this.collideTrue+this.warningRadius);
    }

    public Rectangle2D getDangerBounds(){
        return new Rectangle2D(this.x+this.dx+100, this.y+this.dy+100, this.width+this.collideTrue+this.terrorRadius, this.height + this.collideTrue+this.terrorRadius);
    }

    public void setDX(double dx){
        this.dx += dx;
    }

    public void setDY(double dy){
        this.dy += dy;
    }

    public void vanish(){
//        this.visible = false;
    }
}

package com.game.plankton_revenge;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.Random;

public class GameTimer extends AnimationTimer {
    private final GameStage gs;
    private final GraphicsContext gc;
    private final Scene scene;
    private final Plankton plankton;
    private double planktonSpeedController = GameTimer.NORMAL_SPEED;
    private long startPowerUpsSpawn;
    private double planktonSpeed;

    private static ArrayList<Monster> monsters;
    private static ArrayList<Food> burgers;
    private static ArrayList<PowerUps> powerUps;
    private static boolean planktonBoosted;
    private static double moveY;
    private static double moveX;
    static double planktonWidth;
    static long gameOverTime;

    public static final int MAX_NUM_FOOD = 50;
    public static final int MAX_NUM_MONSTERS = 10;
    public static final int MAX_NUM_POWER_UPS = 2;
    public static final int POWER_UPS_SPAWN_DELAY = 10;
    public static final int UNCOLLECTED_POWER_UPS_VANISH_TIMER = 5;
    public static final double NORMAL_SPEED = 120.0;
    public static final double CENTER_MAP = GameStage.MAP_WIDTH / 2.0;
    public static final double OVERLAY_MAP = 1200;
    public static final int GAME_BOUND = -800;


    private final Image background = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\bg.jpg");
    private final Image warningImg = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\warning.png", 50, 50, false, true);
    private final Image dangerImg = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\danger.png", 50, 50, false, true);

    GameTimer(GameStage gs, GraphicsContext gc, Scene scene){
        this.gc = gc;
        this.scene = scene;
        this.gs = gs;
        this.plankton = new Plankton((GameStage.WINDOW_WIDTH/2)-((int)GameTimer.planktonWidth/2),((GameStage.WINDOW_HEIGHT/2)-((int)GameTimer.planktonWidth/2)), System.nanoTime());
        GameTimer.moveX = 0;
        GameTimer.moveY = 0;

        GameTimer.planktonWidth = 40;

        this.planktonSpeed = 120.0/(plankton.getPlanktonWidth()+plankton.collideTrue);

        GameTimer.monsters = new ArrayList<>();
        GameTimer.burgers = new ArrayList<>();
        GameTimer.powerUps = new ArrayList<>();
        this.startPowerUpsSpawn = System.nanoTime();
        this.spawnMonsters();
        this.spawnFoods();

        this.handleKeyPressEvent();
    }

    @Override
    public void handle(long currentNanoTime) {
        this.gc.clearRect(0, 0, GameStage.MAP_WIDTH,GameStage.MAP_HEIGHT);
        this.gc.drawImage(background, GameTimer.GAME_BOUND*2 + (-1)*GameTimer.moveX, GameTimer.GAME_BOUND*2 + (-1)*GameTimer.moveY,
                GameStage.MAP_WIDTH+GameTimer.OVERLAY_MAP, GameStage.MAP_HEIGHT+GameTimer.OVERLAY_MAP);

        this.plankton.move();

        this.moveMonsters();
        this.checkFood();
        this.checkPowerUps();

        this.drawBoundsIndicator();
        this.updateGameStats();
        this.drawPowerUp();
        this.drawIndicator();

        if(GameTimer.monsters.size() < MAX_NUM_MONSTERS){
            spawnMonsters();
        }

        if(GameTimer.burgers.size() < MAX_NUM_FOOD){
            spawnFoods();
        }

        if(GameTimer.powerUps.size() < MAX_NUM_POWER_UPS){
            spawnPowerUps(currentNanoTime);
        }

        this.plankton.updatePlanktonImage(plankton.isAlive());
        this.plankton.render(this.gc);

        if(!plankton.isAlive()){
            this.plankton.updatePlanktonImage(plankton.isAlive());
            this.plankton.render(this.gc);
            double elapsedTime =  (currentNanoTime - GameTimer.gameOverTime) / 1000000000.0;
            if(elapsedTime >= 5.0){
                this.stop();
                this.drawGameOver();
            }
        }
        this.renderMonsters();
        this.renderFoods();
        this.renderPowerUps();
    }

    private void renderMonsters() {
        for (Monster m : GameTimer.monsters){
            m.render(this.gc);
        }
    }

    private void renderFoods() {
        for (Food f : GameTimer.burgers){
            f.render(this.gc);
        }
    }

    private void renderPowerUps() {
        for (PowerUps p : GameTimer.powerUps){
            p.render(this.gc);
        }
    }

    private void spawnPowerUps(long currentNanoTime){
        double spawnElapsedTime = (currentNanoTime - this.startPowerUpsSpawn) / 1000000000.0;

        Random r = new Random();
        int x = r.nextInt(GameStage.WINDOW_WIDTH);
        int y = r.nextInt(GameStage.WINDOW_HEIGHT)-PowerUps.POWER_UPS_WIDTH;

        if(spawnElapsedTime >= GameTimer.POWER_UPS_SPAWN_DELAY){
            GameTimer.powerUps.add(new PowerUps(x, y, System.nanoTime()));
            if(GameTimer.powerUps.size() < GameTimer.MAX_NUM_POWER_UPS){
                this.startPowerUpsSpawn = System.nanoTime();
            }
        }

        for(PowerUps p: GameTimer.powerUps){
            if(p.isAlive() && (System.nanoTime() - p.getSpawnTime())/1000000000.0 >= GameTimer.UNCOLLECTED_POWER_UPS_VANISH_TIMER){
                p.setAlive(false);
                p.vanish();
            }
        }
    }

    private void spawnMonsters(){
        Random r = new Random();
        for(int i=0;i<(GameTimer.MAX_NUM_MONSTERS - GameTimer.monsters.size());i++){
            int x = r.nextInt(GameStage.MAP_WIDTH) + GameTimer.GAME_BOUND - (int) GameTimer.moveX;
            int y = r.nextInt(GameStage.MAP_HEIGHT)-Monster.MONSTER_WIDTH + GameTimer.GAME_BOUND - (int) GameTimer.moveY;

            GameTimer.monsters.add(new Monster(x, y));
        }
    }

    private void spawnFoods(){
        Random r = new Random();
        for(int i=0;i<(GameTimer.MAX_NUM_FOOD - GameTimer.burgers.size());i++){
            int x = r.nextInt(GameStage.MAP_WIDTH) + GameTimer.GAME_BOUND - (int) GameTimer.getMoveX();
            int y = r.nextInt(GameStage.MAP_HEIGHT)-Food.FOOD_WIDTH + GameTimer.GAME_BOUND - (int) GameTimer.getMoveY();

            GameTimer.burgers.add(new Food(x, y));
        }
    }

    private void checkFood(){
        for(int i = 0; i < GameTimer.burgers.size(); i++){
            Food f = GameTimer.burgers.get(i);
            if(f.isAlive()){
                f.checkPlanktonCollision(this.plankton);
                f.checkMonsterCollision(GameTimer.monsters);
            }else{
                GameTimer.burgers.remove(i);
            }
        }
    }

    private void checkPowerUps(){
        for(int i = 0; i < GameTimer.powerUps.size(); i++){
            PowerUps p = GameTimer.powerUps.get(i);
            if(p.isAlive()){
                p.checkPlanktonCollision(this.plankton);
            }else{
                GameTimer.powerUps.remove(i);
            }
        }

        if(plankton.isBoosted() || plankton.isImmune()){
            if((System.nanoTime() - plankton.getPowerUpSpawnTime())/1000000000.0 >= GameTimer.UNCOLLECTED_POWER_UPS_VANISH_TIMER){
                if(plankton.isBoosted()){
                    this.planktonSpeedController = GameTimer.NORMAL_SPEED;
                    this.plankton.setBoosted(false);
                }
                this.plankton.setImmune(false);
                plankton.setPlanktonTypeSelector(Plankton.REGULAR);
            }
        }
    }

    private void moveMonsters(){
        for(int i = 0; i < GameTimer.monsters.size(); i++){
            Monster m = GameTimer.monsters.get(i);
            if(m.isAlive()){
                m.setSpeed(120/(m.width+m.collideTrue));
                m.move();
                m.checkPlanktonCollision(this.plankton);
                m.checkMonsterCollision(GameTimer.monsters);
            }else{
                GameTimer.monsters.remove(i);
            }
        }
    }

    private void handleKeyPressEvent() {
        this.scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
            public void handle(KeyEvent e){
                KeyCode code = e.getCode();
                movePlankton(code);
            }
        });

        this.scene.setOnKeyReleased(new EventHandler<KeyEvent>(){
            public void handle(KeyEvent e){
                KeyCode code = e.getCode();
                stopPlankton(code);
            }
        });
    }

    private void movePlankton(KeyCode ke) {
        if (GameTimer.planktonBoosted) {
            planktonSpeedController *= 2.0;
        }

        this.planktonSpeed = this.planktonSpeedController/(GameTimer.planktonWidth - plankton.getGroupedNum()*10);

        GameTimer.planktonBoosted = false;

        if(ke==KeyCode.W && GameTimer.moveY >= -CENTER_MAP && plankton.isAlive()){
            GameTimer.moveY -= planktonSpeed;
            for(Monster m: GameTimer.monsters){
                m.setDY(planktonSpeed);
            }
            for(Food f: GameTimer.burgers){
                f.setDY(planktonSpeed);
            }
            for(PowerUps p: GameTimer.powerUps){
                p.setDY(planktonSpeed);
            }
        }

        if(ke==KeyCode.A && GameTimer.moveX >= -CENTER_MAP && plankton.isAlive()){
            GameTimer.moveX -= planktonSpeed;
            for(Monster m: GameTimer.monsters){
                m.setDX(planktonSpeed);
            }
            for(Food f: GameTimer.burgers){
                f.setDX(planktonSpeed);
            }
            for(PowerUps p: GameTimer.powerUps){
                p.setDX(planktonSpeed);
            }
        }

        if(ke==KeyCode.S && GameTimer.moveY <= CENTER_MAP - GameTimer.planktonWidth && plankton.isAlive()){
            GameTimer.moveY += planktonSpeed;
            for(Monster m: GameTimer.monsters){
                m.setDY(-planktonSpeed);
            }
            for(Food f: GameTimer.burgers){
                f.setDY(-planktonSpeed);
            }
            for(PowerUps p: GameTimer.powerUps){
                p.setDY(-planktonSpeed);
            }
        }

        if(ke==KeyCode.D && GameTimer.moveX <= CENTER_MAP - GameTimer.planktonWidth && plankton.isAlive()){
            GameTimer.moveX += planktonSpeed;
            for(Monster m: GameTimer.monsters){
                m.setDX(-planktonSpeed);
            }
            for(Food f: GameTimer.burgers){
                f.setDX(-planktonSpeed);
            }
            for(PowerUps p: GameTimer.powerUps){
                p.setDX(-planktonSpeed);
            }
        }

        if(ke==KeyCode.SPACE && plankton.isAlive()){
            int groupedNum = ((int) GameTimer.planktonWidth/40)-1;

            if(groupedNum > 0 && plankton.getGroupedNum() != groupedNum &&
                    plankton.getGroupedNum() < groupedNum){
                plankton.setGroupedNum(groupedNum);
                plankton.setGrouped(true);
                plankton.setPlanktonTypeSelector(Plankton.REGULAR);
                plankton.collideTrue = 0;
                GameTimer.planktonWidth = 40 + groupedNum*10;
                plankton.move();
            }
        }
    }

    private void stopPlankton(KeyCode ke){
        for(Monster m: GameTimer.monsters){
            m.setDX(0);
            m.setDY(0);
        }
        for(Food f: GameTimer.burgers){
            f.setDX(0);
            f.setDY(0);
        }
        for(PowerUps p: GameTimer.powerUps){
            p.setDX(0);
            p.setDY(0);
        }
        this.plankton.setDX(0);
        this.plankton.setDY(0);
        GameTimer.moveX += 0;
        GameTimer.moveY += 0;
    }

    private void drawBoundsIndicator(){
        String text = "";

        if(GameTimer.moveY < -CENTER_MAP || GameTimer.moveX < -CENTER_MAP || GameTimer.moveY > CENTER_MAP - GameTimer.planktonWidth
                || GameTimer.moveX > CENTER_MAP - GameTimer.planktonWidth){
            text = "Reached the Map Boundary!";
        }else{
            text = "";
        }

        this.gc.setFont(Font.font("Courier", FontWeight.BOLD, 15));
        this.gc.setFill(Color.GOLD);
        this.gc.fillText(text, 15, 780);
    }

    private void updateGameStats(){
        this.gc.setFont(Font.font("Courier", FontWeight.BOLD, 15));
        this.gc.setFill(Color.GOLD);
        this.gc.fillText("Food Eaten:", 15, 20);
        this.gc.setFont(Font.font("Courier", FontWeight.BOLD, 15));
        this.gc.setFill(Color.WHITE);
        this.gc.fillText(plankton.getFoodEaten()+"", 115, 20);

        this.gc.setFont(Font.font("Courier", FontWeight.BOLD, 15));
        this.gc.setFill(Color.GOLD);
        this.gc.fillText("Enemy Defeated:", 165, 20);
        this.gc.setFont(Font.font("Courier", FontWeight.BOLD, 15));
        this.gc.setFill(Color.WHITE);
        this.gc.fillText(plankton.getEnemyDefeated()+"", 315, 20);

        this.gc.setFont(Font.font("Courier", FontWeight.BOLD, 15));
        this.gc.setFill(Color.GOLD);
        this.gc.fillText("Current Size:", 365, 20);
        this.gc.setFont(Font.font("Courier", FontWeight.BOLD, 15));
        this.gc.setFill(Color.WHITE);
        this.gc.fillText(GameTimer.planktonWidth + "", 465, 20);

        this.gc.setFont(Font.font("Courier", FontWeight.BOLD, 15));
        this.gc.setFill(Color.GOLD);
        this.gc.fillText("Time Alive:", 535, 20);
        this.gc.setFont(Font.font("Courier", FontWeight.BOLD, 15));
        this.gc.setFill(Color.WHITE);
        String elapsedTime = String.format("%.04f", ((System.nanoTime() - plankton.getSpawnTime())/1000000000.0));
        this.gc.fillText(elapsedTime + " s", 635, 20);
    }

    private void drawPowerUp() {
        String text = "";

        if (plankton.isImmune()) {
            text = "IMMUNE!";
        } else if (plankton.isBoosted()) {
            text = "BOOSTED!";
        }

        if (!plankton.isImmune() && !plankton.isBoosted()) {
            text = "";
        }

        this.gc.setFont(Font.font("Courier", FontWeight.BOLD, 15));
        this.gc.setFill(Color.GOLD);
        this.gc.fillText(text, 710, 780);
    }

    private void drawIndicator(){
        Image image = null;
        for(Monster m: monsters){
            if(m.isWarning() && !m.isDanger() && plankton.isAlive()){
                image = warningImg;
            }

            if(m.isDanger() && !m.isWarning() && plankton.isAlive()){
                image = dangerImg;
            }

            if((!m.isDanger() && !m.isWarning()) || !plankton.isAlive()){
                image = null;
            }

            this.gc.drawImage(image, (GameStage.WINDOW_WIDTH/4.0)-warningImg.getWidth()/2, (GameStage.WINDOW_HEIGHT/2.0)-warningImg.getHeight()/2);
            this.gc.drawImage(image, GameStage.WINDOW_WIDTH*0.75, (GameStage.WINDOW_HEIGHT/2.0)-warningImg.getHeight()/2);
        }
    }

    private void drawGameOver(){
        Group root = new Group();
        Canvas canvas = new Canvas(GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.drawImage(background, 0, 0, GameStage.MAP_WIDTH, GameStage.WINDOW_HEIGHT);

        gc.setFont(Font.font("Courier", FontWeight.BOLD, 50));
        gc.setFill(Color.WHITE);
        gc.fillText("GAME OVER!", 250, GameStage.WINDOW_HEIGHT*0.25);

        gc.setFont(Font.font("Courier", FontWeight.BOLD, 15));
        gc.setFill(Color.GOLD);
        gc.fillText("Food Eaten:", 265, 270);
        gc.setFont(Font.font("Courier", FontWeight.BOLD, 15));
        gc.setFill(Color.WHITE);
        gc.fillText(plankton.getFoodEaten()+"", 450, 270);

        gc.setFont(Font.font("Courier", FontWeight.BOLD, 15));
        gc.setFill(Color.GOLD);
        gc.fillText("Enemy Defeated:",265, 320);
        gc.setFont(Font.font("Courier", FontWeight.BOLD, 15));
        gc.setFill(Color.WHITE);
        gc.fillText(plankton.getEnemyDefeated()+"", 450, 320);

        gc.setFont(Font.font("Courier", FontWeight.BOLD, 15));
        gc.setFill(Color.GOLD);
        gc.fillText("Current Size:", 265, 370);
        gc.setFont(Font.font("Courier", FontWeight.BOLD, 15));
        gc.setFill(Color.WHITE);
        gc.fillText(GameTimer.planktonWidth + "", 450, 370);

        gc.setFont(Font.font("Courier", FontWeight.BOLD, 15));
        gc.setFill(Color.GOLD);
        gc.fillText("Time Alive:", 265, 420);
        gc.setFont(Font.font("Courier", FontWeight.BOLD, 15));
        gc.setFill(Color.WHITE);
        String elapsedTime = String.format("%.04f", ((System.nanoTime() - plankton.getSpawnTime())/1000000000.0));
        gc.fillText(elapsedTime + " s", 450, 420);

        StackPane stack = new StackPane();
        stack.setAlignment(Pos.CENTER);
        stack.setPadding(new Insets(470, 0, 0, 375));

        Button exitBtn = new Button("    Exit    ");

        stack.getChildren().addAll(exitBtn);

        exitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.exit(0);		// changes the scene into the game scene
            }
        });

        root.getChildren().addAll(canvas, stack);

        GameStage.gameOverScene = new Scene(root, GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT);
        gs.setGameOverScene();
    }

    public static void setPlanktonBoosted(boolean planktonBoosted) {
        GameTimer.planktonBoosted = planktonBoosted;
    }

    public static double getMoveX(){
        return GameTimer.moveX;
    }

    public static double getMoveY() {
        return GameTimer.moveY;
    }
}

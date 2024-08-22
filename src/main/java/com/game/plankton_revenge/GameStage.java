package com.game.plankton_revenge;

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
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GameStage {
    public static final int WINDOW_HEIGHT = 800;
    public static final int WINDOW_WIDTH = 800;
    public static final int MAP_WIDTH = 2400;
    public static final int MAP_HEIGHT = 2400;
    private Scene splashScene;
    private Stage stage;
    public static Scene gameOverScene;
    private Scene aboutScene;
    private Scene instructionsScene;
    private final Scene gameScene;
    private final Canvas canvas;
    private int selector = 0;
    private ArrayList<Image> instructions;

    public static final Image ABOUT_BG = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\about.jpg");

    //the class constructor
    public GameStage() {
        this.canvas = new Canvas(GameStage.MAP_WIDTH,GameStage.MAP_HEIGHT);
        Group root = new Group();
        root.getChildren().add(this.canvas);
        this.gameScene = new Scene(root, GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT);
        this.instructions = new ArrayList<>();

        Image s1 = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\Slide1.JPG");
        Image s2 = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\Slide2.JPG");
        Image s3 = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\Slide3.JPG");
        Image s4 = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\Slide4.JPG");
        Image s5 = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\Slide5.JPG");
        Image s6 = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\Slide6.JPG");
        Image s7 = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\Slide7.JPG");
        Image s8 = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\Slide8.JPG");
        Image s9 = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\Slide9.JPG");

        this.instructions.add(s1);
        this.instructions.add(s2);
        this.instructions.add(s3);
        this.instructions.add(s4);
        this.instructions.add(s5);
        this.instructions.add(s6);
        this.instructions.add(s7);
        this.instructions.add(s8);
        this.instructions.add(s9);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        this.stage.setTitle("Plankton Revenge");

        this.initSplash();

        this.stage.setScene(this.splashScene);
        this.stage.setResizable(false);

        this.stage.show();
    }

    private void initSplash() {
        StackPane root = new StackPane();
        root.getChildren().addAll(this.createCanvas(),this.createHBox());
        this.splashScene = new Scene(root, GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT);
    }

    private Canvas createCanvas() {
        Canvas canvas = new Canvas(GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Image bg = new Image("C:\\Users\\User\\Documents\\Programming Stuff\\plankton_revenge\\src\\main\\java\\com\\game\\plankton_revenge\\images\\bg-splash.jpg");
        gc.drawImage(bg, 0, 0, GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT);
        return canvas;
    }

    private HBox createHBox() {
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(0,10,80,0));
        hbox.setSpacing(8);

        Button newGame = new Button("New Game");
        Button instructions = new Button("Instructions");
        Button about = new Button("About");

        hbox.getChildren().addAll(newGame, instructions, about);

        newGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                setGame(stage);		// changes the scene into the game scene
            }
        });

        instructions.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                setInstructionsScene();		// changes the scene into the game scene
            }
        });

        about.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                setAboutScene(stage);		// changes the scene into the game scene
            }
        });

        return hbox;
    }

    private void setGame(Stage stage) {
        stage.setScene(this.gameScene);

        GraphicsContext gc = this.canvas.getGraphicsContext2D();	// we will pass this gc to be able to draw on this Game's canvas

        GameTimer gameTimer = new GameTimer(this, gc, gameScene);
        gameTimer.start();			// this internally calls the handle() method of our GameTimer
    }

    public void setGameOverScene(){
        this.stage.setScene(GameStage.gameOverScene);
        stage.show();
    }

    private void setAboutScene(Stage stage){
        Group root = new Group();
        Canvas canvas = new Canvas(GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(ABOUT_BG, 0, 0, GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT);

        StackPane stack = new StackPane();
        stack.setAlignment(Pos.CENTER);
        stack.setPadding(new Insets(700, 0, 0, 93));

        Button backBtn = new Button("    Go Back    ");

        stack.getChildren().addAll(backBtn);
        root.getChildren().addAll(canvas, stack);

        backBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                setStage(stage);
            }
        });

        this.aboutScene = new Scene(root, GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT);

        this.stage.setScene(this.aboutScene);
        stage.show();
    }

    private void setInstructionsScene(){
        Group root = new Group();
        Canvas canvas = new Canvas(GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        HBox hBox = setHBox();

        gc.drawImage(this.instructions.get(selector),0,0, GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT);

        root.getChildren().addAll(canvas, hBox);

        this.instructionsScene = new Scene(root, GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT);

        this.stage.setScene(this.instructionsScene);
        stage.show();
    }

    private HBox setHBox(){
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(760,0,0,330));
        hBox.setSpacing(10);

        Button nextBtn = new Button("Next");
        Button backBtn = new Button("Back");
        Button exitBtn = new Button("Exit");

        hBox.getChildren().addAll(backBtn,exitBtn,nextBtn);



        nextBtn.setOnMouseClicked(event -> {
            if(this.selector < 8){
                this.selector += 1;
            }else{
                this.selector = 0;
            }
            setInstructionsScene();
        });


        backBtn.setOnMouseClicked(event -> {
            if(this.selector > 0){
                this.selector -= 1;
            }else{
                this.selector = 8;
            }
            setInstructionsScene();
        });

        exitBtn.setOnMouseClicked(event -> {
            setStage(this.stage);
        });

        return hBox;
    }
}

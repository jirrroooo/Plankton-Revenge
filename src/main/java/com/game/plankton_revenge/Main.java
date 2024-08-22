/*
* This program is the Mini Project in CMSC 22
* Plankton Revenge is a Game from the idea of blob game
* Programmer: John Rommel B. Octavo
* Section: CMSC 22 | W4L
* Date Created: November 25, 2022
*/

package com.game.plankton_revenge;


import com.game.plankton_revenge.GameStage;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage){
        GameStage theGameStage = new GameStage();
        theGameStage.setStage(stage);
    }

}
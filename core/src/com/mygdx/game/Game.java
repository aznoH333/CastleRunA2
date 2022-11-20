package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.data.enums.GameState;
import com.mygdx.game.data.load.SoundLoadList;
import com.mygdx.game.logic.SoundManager;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.entities.EntityManager;
import com.mygdx.game.logic.player.InputManager;
import com.mygdx.game.logic.stage.LevelProgressionManager;
import com.mygdx.game.ui.UIManager;

import java.util.Random;

public class Game {

    private final static Random seededRandom = new Random(258);
    private final static Random generalRandom = new Random();
    private final static DrawingManager spr = DrawingManager.getINSTANCE();
    private final static UIManager ui = UIManager.getINSTANCE();
    private final static InputManager input = InputManager.getINSTANCE();
    private static long time = 1;
    private static long exitTime = 0;
    private final static LevelProgressionManager lvlMan = LevelProgressionManager.getINSTANCE();
    public final static float gameWorldWidth = (Gdx.graphics.getWidth() * (1280f/Gdx.graphics.getHeight()));
    private static GameState currentGameState = GameState.MainMenu;



    public Game() {
        SoundLoadList.loadAllSounds();
        changeState(GameState.MainMenu);
        ui.changeUI(GameState.MainMenu);

        ui.delayedOpening();

        // TODO: save and continue games
        //temporary music
        //SoundManager.getINSTANCE().playMusic("placeholder music",0.5f);
    }

    public static void changeState(GameState state){
        Game.currentGameState = state;
        ui.changeUI(state);
        ui.delayedOpening();
    }

    public void dispose() {
        spr.dispose();
        SoundManager.getINSTANCE().dispose();
        System.exit(0);
    }


    public static long Time() {
        return time;
    }

    public void render() {
        ScreenUtils.clear(0, 0, 0, 1);
        //main cycle

        // exit level
        if (time == exitTime){
            exitTime = 0;

            //lvlMan.progressLevel();
            ui.transition(GameState.StageMenu, lvlMan::progressLevel);
        }

        ui.updateUI();
        ui.drawUI();

        if (exitTime < 1)
            input.manageInput();
        currentGameState.state.update();


        input.resetInput();
        spr.render();

        //temp input
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) Gdx.app.exit();
        //pavliku tohle je validni a drz hubu
        time++;


    }


    public static void exitLevel(int time){
        if (exitTime == 0 && ui.isTransitioning()){
            ui.close();
            exitTime = Game.time + time;
            EntityManager.getINSTANCE().clearEnemyEntities();
        }
    }

    public static Random getSeededRandom(){
        return seededRandom;
    }

    public static Random getGeneralRandom(){
        return generalRandom;
    }

    public static void exitLevel(){
        exitLevel(120);
    }

    public static boolean isExitingLevel(){
        return exitTime > 0;
    }
}

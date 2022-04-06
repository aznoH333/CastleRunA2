package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.data.enums.GameState;
import com.mygdx.game.data.load.SoundLoadList;
import com.mygdx.game.logic.SoundManager;
import com.mygdx.game.logic.menus.GameOver;
import com.mygdx.game.logic.menus.MainMenu;
import com.mygdx.game.logic.menus.NewGameMenu;
import com.mygdx.game.ui.ItemViewer;
import com.mygdx.game.ui.MenuUIManager;
import com.mygdx.game.logic.shops.Shop;
import com.mygdx.game.logic.entities.EntityManager;
import com.mygdx.game.logic.entities.ParticleManager;
import com.mygdx.game.logic.level.LevelManager;
import com.mygdx.game.logic.player.InputManager;
import com.mygdx.game.logic.player.ItemManager;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.stage.StageManager;
import com.mygdx.game.logic.stage.StageMap;
import com.mygdx.game.ui.UIManager;

import java.util.Random;

public class Game extends ApplicationAdapter {

    public static final int androidHeight = 1920;
    public static final int androidWidth = 1080;
    public static final int pcHeight = 640;
    public static final int pcWidth = 360;

    private final static Random seededRandom = new Random(258);
    private final static Random generalRandom = new Random();
    private final static DrawingManager spr = DrawingManager.getINSTANCE();
    private final static LevelManager lvl = LevelManager.getINSTANCE();
    private final static ParticleManager part = ParticleManager.getINSTANCE();
    private final static UIManager ui = UIManager.getINSTANCE();
    private final static InputManager input = InputManager.getINSTANCE();
    private final static EntityManager ent = EntityManager.getINSTANCE();
    private static long time = 1;
    private static long exitTime = 0;
    private static GameState state = GameState.MainMenu;
    private final static MenuUIManager menuUI = MenuUIManager.getINSTANCE();
    private final static StageMap stageMap = StageMap.getINSTANCE();
    private final static StageManager stageManager = StageManager.getINSTANCE();
    private final static ItemViewer itemViewer = ItemViewer.getINSTANCE();
    private final static Shop shop = Shop.getINSTANCE();
    private final static GameOver gameOver = GameOver.getINSTANCE();
    private final static ItemManager itemManager = ItemManager.getINSTANCE();
    private final static MainMenu mainMenu = MainMenu.getINSTANCE();
    private final static NewGameMenu newGameMenu = NewGameMenu.getINSTANCE();


    @Override
    public void create() {
        DrawingManager.getINSTANCE().wtfKterejDebilTohleNapsal();
        SoundLoadList.loadAllSounds();

        //init stuff
        //stageManager.startLevel();

        //temporary
        ui.changeUI(GameState.MainMenu);
        ui.delayedOpening();
        ItemViewer.getINSTANCE().changeDimensions(500,500);

        // TODO: save and continue games
        //temporary music
        //SoundManager.getINSTANCE().playMusic("placeholder music",0.5f);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1);
        //main cycle

        // exit level
        if (time == exitTime){
            exitTime = 0;

            StageManager.getINSTANCE().advanceInStage();
        }

        ui.drawUI();
        ui.updateUI();
        if (exitTime < 1)
            input.manageInput();
        switch (state){
            case Game:
                game();
                break;
            case StageMenu:
                stageMenu();
                break;
            case Shop:
                shop();
                break;
            case GameOver:
                gameOver();
                break;
            case MainMenu:
                mainMenu();
                break;
            case EquipMenu:
                equipMenu();
                break;
            case NewGameMenu:
                newGameMenu();
                break;

        }

        input.resetInput();
        spr.render();

        //temp input
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) Gdx.app.exit();

        //pavliku tohle je validni a drz hubu
        time++;


    }



    @Override
    public void dispose() {
        spr.dispose();
        SoundManager.getINSTANCE().dispose();
        System.exit(0);

    }


    public static long Time() {
        return time;
    }

    public static void changeState(GameState state){
        Game.state = state;
        ui.changeUI(state);
        ui.delayedOpening();
        // TODO : rework shops && itemviewer to use the new ui system
        itemViewer.updateValues();

    }

    //status functions
    private void game(){
        lvl.update();
        part.update();
        part.draw(spr);
        ent.update();
    }

    private void stageMenu() {
        stageMap.update();
    }

    private void shop(){
        shop.draw();
    }

    private void gameOver(){
        gameOver.render();
    }

    private void mainMenu(){
        mainMenu.draw();
    }

    private void equipMenu(){
        itemViewer.update();
    }

    private void newGameMenu(){
        newGameMenu.draw();
    }

    public static void exitLevel(int time){
        if (exitTime == 0 && !ui.isTransitioning()){
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
}

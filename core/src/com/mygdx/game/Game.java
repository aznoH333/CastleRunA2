package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.data.enums.GameState;
import com.mygdx.game.data.load.SoundLoadList;
import com.mygdx.game.logic.SoundManager;
import com.mygdx.game.logic.UI.ItemViewer;
import com.mygdx.game.logic.UI.MenuUIManager;
import com.mygdx.game.logic.entities.EntityManager;
import com.mygdx.game.logic.entities.ParticleManager;
import com.mygdx.game.logic.level.LevelManager;
import com.mygdx.game.logic.player.InputManager;
import com.mygdx.game.logic.UI.GameUIManager;
import com.mygdx.game.logic.sprites.SpriteManager;
import com.mygdx.game.logic.stage.StageManager;
import com.mygdx.game.logic.stage.StageMap;

import java.util.Random;

public class Game extends ApplicationAdapter {

    public static final int androidHeight = 1920;
    public static final int androidWidth = 1080;
    public static final int pcHeight = 640;
    public static final int pcWidth = 360;

    private SpriteManager spr;
    private LevelManager lvl;
    private Random r;
    private EntityManager e;
    private ParticleManager part = ParticleManager.getINSTANCE();
    private GameUIManager ui;
    private InputManager input = InputManager.getINSTANCE();
    private static long time = 1;
    private static GameState state = GameState.Game;
    private MenuUIManager menuUI;
    private StageMap stageMap;
    private ItemViewer itemViewer;


    @Override
    public void create() {
        r = new Random(258);
        spr = SpriteManager.getINSTANCE();
        LevelManager.setUpINSTANCE(spr,r);
        lvl = LevelManager.getINSTANCE();
        // very bad but functional
        EntityManager.createINSTANCE(lvl, r, spr);
        e = EntityManager.getINSTANCE();
        lvl.setE(e);
        SoundLoadList.loadAllSounds();
        ui = GameUIManager.getINSTANCE();
        menuUI = MenuUIManager.getINSTANCE();
        stageMap = StageMap.getINSTANCE();
        itemViewer = ItemViewer.getINSTANCE();

        //init stuff
        StageManager.getINSTANCE().startLevel();

        //temporary music
        //SoundManager.getINSTANCE().playMusic("placeholder music",0.5f);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1);
        //main cycle

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
        if (state != GameState.Game)
            MenuUIManager.getINSTANCE().changeButtonSet(state);

        switch(state){
            case Shop:
                ItemViewer.getINSTANCE().changeDimensions(0,0);
                break;
            case EquipMenu:
                ItemViewer.getINSTANCE().changeDimensions(500,500);
        }
    }

    //status functions
    private void game(){
        lvl.update();
        part.update();
        part.draw(spr);
        e.update();
        ui.drawGameUI();
    }

    private void stageMenu() {
        stageMap.update();
        menuUI.update();

    }

    private void shop(){
        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) changeState(GameState.StageMenu);
        itemViewer.update();
    }

    // TODO : add perks (kind of like items in tboi but not really)

    private void gameOver(){
        // TODO : this
    }
    private void mainMenu(){
        // TODO : this
    }

    private void equipMenu(){
        menuUI.update();
        itemViewer.update();
    }
}

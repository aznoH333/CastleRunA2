package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.data.load.SoundLoadList;
import com.mygdx.game.entities.player.Player;
import com.mygdx.game.logic.SoundManager;
import com.mygdx.game.logic.entities.EntityManager;
import com.mygdx.game.logic.entities.ParticleManager;
import com.mygdx.game.logic.level.LevelBuilder;
import com.mygdx.game.logic.level.LevelManager;
import com.mygdx.game.logic.player.InputManager;
import com.mygdx.game.logic.player.UIManager;
import com.mygdx.game.logic.sprites.SpriteManager;

import java.util.Random;

public class Game extends ApplicationAdapter {


    private SpriteManager spr;
    private LevelManager lvl;
    private Random r;
    private EntityManager e;
    private ParticleManager part = ParticleManager.getINSTANCE();
    private UIManager ui = UIManager.getINSTANCE();
    private InputManager input = InputManager.getINSTANCE();
    private static long time = 0;

    @Override
    public void create() {
        r = new Random(258);
        spr = SpriteManager.getINSTANCE();
        lvl = new LevelManager(spr, r);
        // very bad but functional
        EntityManager.createINSTANCE(lvl, r, spr);
        e = EntityManager.getINSTANCE();
        lvl.setE(e);
        SoundLoadList.loadAllSounds();

        //init stuff

        lvl.loadLevel(LevelBuilder.getINSTANCE().getByName("1-2"));
        e.addEntity(new Player(64, 0, 64, 64));

        //temporary music
        SoundManager.getINSTANCE().playMusic("placeholder music",0.5f);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1);
        //main cycle

        input.manageInput();
        lvl.update();
        part.update();
        part.draw(spr);
        e.update();
        ui.draw();
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
}

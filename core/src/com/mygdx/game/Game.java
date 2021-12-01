package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.entities.player.Player;
import com.mygdx.game.logic.*;
import com.mygdx.game.logic.entities.EntityManager;
import com.mygdx.game.logic.entities.ParticleManager;
import com.mygdx.game.logic.level.LevelBuilder;
import com.mygdx.game.logic.level.LevelManager;
import com.mygdx.game.logic.player.UIManager;

import java.util.Random;

public class Game extends ApplicationAdapter {


	private SpriteManager spr;
	private LevelManager lvl;
	private Random r;
	private EntityManager e;
	private ParticleManager part = ParticleManager.getINSTANCE();
	private UIManager ui = UIManager.getINSTANCE();

	@Override
	public void create () {
        r = new Random(258);
		spr = SpriteManager.getINSTANCE();
		lvl = new LevelManager(spr,r);
		// very bad but functional
		EntityManager.createINSTANCE(lvl, r, spr);
		e = EntityManager.getINSTANCE();
		lvl.setE(e);

		//init stuff
		lvl.loadLevel(LevelBuilder.getINSTANCE().getByName("1-1"));
		e.addEntity(new Player(0,0, 64, 64));
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		//main cycle
        spr.begin();
			lvl.update();
			e.update();
			part.update();
			part.draw(spr);
			ui.draw();
		spr.end();

		//temp input
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) Gdx.app.exit();
	}
	
	@Override
	public void dispose () {
		spr.dispose();
		System.exit(0);
	}

}

package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.data.levels.CastleLevel;
import com.mygdx.game.entities.Player;
import com.mygdx.game.managers.EntityManager;
import com.mygdx.game.managers.Level;
import com.mygdx.game.managers.SpriteManager;

import java.util.Random;

public class Game extends ApplicationAdapter {


	private SpriteManager spr;
	private Level lvl;
	private Random r;
	private EntityManager e;
	
	@Override
	public void create () {
        r = new Random(68);
		spr = new SpriteManager();
		lvl = new Level(spr,r);
		e = new EntityManager(lvl, r, spr);

		lvl.setE(e);
		//init stuff
		lvl.loadLevel(new CastleLevel());
		e.addEntity(new Player(0,64, 64, 64));
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		//main cycle
        spr.begin();
			lvl.update();
			e.update();
		spr.end();

		//temp input
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) Gdx.app.exit();
	}
	
	@Override
	public void dispose () {
		spr.dispose();
	}
}

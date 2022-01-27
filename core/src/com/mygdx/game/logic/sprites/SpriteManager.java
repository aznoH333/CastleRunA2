package com.mygdx.game.logic.sprites;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.data.load.SpriteLoadList;
import com.mygdx.game.logic.level.LevelManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class SpriteManager {

    private static SpriteManager INSTANCE;
    public static SpriteManager getINSTANCE(){
        if (INSTANCE == null) INSTANCE = new SpriteManager();
        return INSTANCE;
    }

    private final SpriteBatch batch;
    private final HashMap<String, Texture> sprs;
    private static final int pixelScale = 4;
    public static final int gamePosition = 576;
    private final HashMap<Integer,ArrayList<SpriteData>> spriteDraw = new HashMap<>();

    public SpriteManager(){
        batch = new SpriteBatch();
        sprs = new HashMap<>();
        OrthographicCamera cam = new OrthographicCamera();
        cam.setToOrtho(false, 720,1280);
        batch.setProjectionMatrix(cam.combined);
        SpriteLoadList.loadAllSprites(this);
    }


    public void dispose(){
        batch.dispose();
        //dispose of sprites
        try{
            for (Texture t: sprs.values()) {
                t.dispose();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        sprs.clear();

    }


    /**
        Z Index Listings
     ---------------------
       -1 : background
        0 : level
        1 : entities
        2 : gameplay affecting entities
        3 : particles
        4 - 6 : hud

     ---------------------
     */

    // TODO : rewrite draw to use sperate arrays for layering instead of ordered sprites

    public void render(){
        batch.begin();
        for (ArrayList<SpriteData> layer : spriteDraw.values()) {
            for (SpriteData s: layer) {
                batch.draw(s.getTexture(), s.getX(), s.getY(), s.getTexture().getWidth()*pixelScale, s.getTexture().getHeight()*pixelScale);
            }
            layer.clear();
        }
        batch.end();
    }

    // draws sprite with the default sprite index
    // default sprite index is 0
    // use to draw only in game graphics
    public void drawGame(String textureName, float x, float y){
        draw(textureName, x, y + gamePosition - LevelManager.tileScale,0);
    }

    // draws with set index
    // use to draw only in game graphics
    public void drawGame(String textureName, float x, float y, int zIndex){
        draw(textureName, x, y + gamePosition - LevelManager.tileScale, (byte) zIndex);
    }

    // use to draw ui stuff
    public void draw(String textureName, float x, float y, int zIndex){
        Texture text = sprs.get(textureName);
        if (!spriteDraw.containsKey(zIndex))
            spriteDraw.put(zIndex, new ArrayList<>());
        spriteDraw.get(zIndex).add(new SpriteData(text, x, y));
    }


    public void loadSprites(String path, String name, int amount){
        for (int i = 0; i <= amount; i++) {

            //dumb piskel stuff
            StringBuilder adder = new StringBuilder();
            int dumb = 9;
            while (dumb <= amount){
                if (i <= dumb) adder.append("0");
                dumb = (dumb * 10) + 9;
            }

            sprs.put(name + i,new Texture("sprites/" + path + adder + i + ".png"));
        }
    }

}

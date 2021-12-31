package com.mygdx.game.logic.sprites;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.data.load.SpriteLoadList;

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
    private final OrthographicCamera cam;
    private static final int pixelScale = 4;
    private final ArrayList<SpriteData> spriteDraw = new ArrayList();

    public SpriteManager(){
        batch = new SpriteBatch();
        sprs = new HashMap<>();
        cam = new OrthographicCamera();
        cam.setToOrtho(false, 1280,720);
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

     ---------------------
     */

    public void render(){
        Collections.sort(spriteDraw);
        batch.begin();
        for (SpriteData s : spriteDraw) {
            batch.draw(s.getTexture(), s.getX(), s.getY(), s.getTexture().getWidth()*pixelScale, s.getTexture().getHeight()*pixelScale);
        }
        batch.end();
        spriteDraw.clear();
    }

    // draws sprite with the default sprite index
    // default sprite index is 0
    public void draw(String textureName, float x, float y){
        Texture text = sprs.get(textureName);
        spriteDraw.add(new SpriteData(text, x, y, (byte) 0));
    }

    // draws with set index
    public void draw(String textureName, float x, float y, int zIndex){
        Texture text = sprs.get(textureName);
        spriteDraw.add(new SpriteData(text, x, y, (byte) zIndex));
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

            sprs.put(name + i,new Texture(path + adder + i + ".png"));
        }
    }

}

package com.mygdx.game.logic;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.data.load.SpriteLoadList;


import java.util.HashMap;

public class SpriteManager {

    private static SpriteManager INSTANCE;
    public static SpriteManager getINSTANCE(){
        if (INSTANCE == null) INSTANCE = new SpriteManager();
        return INSTANCE;
    }

    private SpriteBatch batch;
    private HashMap<String, Texture> sprs;
    private OrthographicCamera cam;

    private final int pixelScale = 4;

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

    public void begin(){
        batch.begin();

    }
    public void end(){
        batch.end();
    }

    // TODO: draw priority or z indexes
    public void draw(String textureName, float x, float y){
        Texture text = sprs.get(textureName);
        batch.draw(text, x, y, text.getWidth()*pixelScale, text.getHeight()*pixelScale);
    }

    public void loadSprites(String path, String name, int amount){
        for (int i = 0; i <= amount; i++) {

            //dumb piskel stuff
            StringBuilder adder = new StringBuilder();
            int dumb = 10;
            while (dumb < amount){
                if (i < dumb) adder.append("0");
                dumb *= 10;
            }

            sprs.put(name + i,new Texture(path + adder + i + ".png"));
        }
    }
}

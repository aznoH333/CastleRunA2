package com.mygdx.game.managers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.data.TileLoadList;


import java.util.HashMap;

public class SpriteManager {

    private SpriteBatch batch;
    private HashMap<String, Texture> sprs;
    private OrthographicCamera cam;

    private int textureScale = 64;

    public SpriteManager(){
        batch = new SpriteBatch();
        sprs = new HashMap<>();
        cam = new OrthographicCamera();
        cam.setToOrtho(false, 1280,720);
        batch.setProjectionMatrix(cam.combined);
        TileLoadList.loadAllSprites(this);
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

    public void draw(String textureName, float x, float y){
        batch.draw(sprs.get(textureName), x, y, textureScale, textureScale);
    }

    public void loadSprites(String path, String name, int amount){


        for (int i = 0; i <= amount; i++) {

            //dumb piskel stuff
            String adder = "";
            int dumb = 10;
            while (dumb < amount){
                if (i < dumb) adder += "0";
                dumb *= 10;
            }

            sprs.put(name + i,new Texture(path + adder + i + ".png"));
        }
    }
}

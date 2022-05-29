package com.mygdx.game.logic.drawing;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.game.data.enums.DrawingDataType;
import com.mygdx.game.data.load.SpriteLoadList;
import com.mygdx.game.logic.level.LevelManager;

import java.util.ArrayList;
import java.util.HashMap;

public class DrawingManager {

    private static DrawingManager INSTANCE;
    public static DrawingManager getINSTANCE(){
        if (INSTANCE == null) INSTANCE = new DrawingManager();
        return INSTANCE;
    }

    private SpriteBatch batch;
    private final HashMap<String, Texture> sprs;
    private static final int pixelScale = 4;

    private final HashMap<Integer,ArrayList<DrawingData>> drawQueue = new HashMap<>();


    private BitmapFont font;

    public DrawingManager(){
        sprs = new HashMap<>();
    }

    public void wtfKterejDebilTohleNapsal(){
        //font stuff
        // TODO : get a custom font (required for commercial usage)
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.getFileHandle("fonts/AGoblinAppears-o2aV.ttf", Files.FileType.Internal));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        batch = new SpriteBatch();
        parameter.size = 25;
        font = generator.generateFont(parameter);
        OrthographicCamera cam = new OrthographicCamera();
        cam.setToOrtho(false, 720,1280);
        batch.setProjectionMatrix(cam.combined);
        SpriteLoadList.loadAllSprites(this);
    }


    public void dispose(){
        batch.dispose();
        font.dispose();
        //dispose of sprites
        try{
            for (Texture t: sprs.values()) {
                t.dispose();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        sprs.clear();
        drawQueue.clear();
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


    public void render(){
        batch.begin();
        for (ArrayList<DrawingData> layer : drawQueue.values()) {
            for (DrawingData data: layer) {
                if (data.getType() == DrawingDataType.Sprite)
                    batch.draw(data.getTexture(), data.getX(), data.getY(), data.getTexture().getWidth()*pixelScale, data.getTexture().getHeight()*pixelScale);
                else
                    font.draw(batch, data.getText(), data.getX(), data.getY());

            }
            layer.clear();
        }

        batch.end();
    }

    public void drawText(String text, float x, float y, int zIndex){
        if (!drawQueue.containsKey(zIndex))
            drawQueue.put(zIndex, new ArrayList<>());
        drawQueue.get(zIndex).add(new TextData(text, x, y));
    }

    public void drawText(String text, float x, float y){
        drawText(text, x, y, 5);
    }

    // draws sprite with the default sprite index
    // default sprite index is 0
    // use to draw only in game graphics
    public void drawGame(String textureName, float x, float y){
        draw(textureName, x, y ,0);
    }

    // draws with set index
    // use to draw only in game graphics
    public void drawGame(String textureName, float x, float y, int zIndex){
        draw(textureName, x, y, (byte) zIndex);
    }

    // use to draw ui stuff
    public void draw(String textureName, float x, float y, int zIndex){
        if (textureName != null){
            Texture text = sprs.get(textureName);

            if (!drawQueue.containsKey(zIndex))
                drawQueue.put(zIndex, new ArrayList<>());
            drawQueue.get(zIndex).add(new SpriteData(text, x, y));
        }

    }


    public void loadSprites(String path, String name, int amount){
        for (int i = 0; i <= amount; i++) {

            //dumb piskel stuff
            StringBuilder adder = new StringBuilder();
            int dumb = 9;
            while (dumb < amount){
                if (i <= dumb) adder.append("0");
                dumb = (dumb * 10) + 9;
            }

            sprs.put(name + i,new Texture("sprites/" + path + adder + i + ".png"));
        }
    }

}

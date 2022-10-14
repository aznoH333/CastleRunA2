package com.mygdx.game.logic.drawing;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.game.data.enums.DrawingDataType;
import com.mygdx.game.data.load.SpriteLoadList;

import java.util.ArrayList;
import java.util.HashMap;

public class DrawingManager {

    private static DrawingManager INSTANCE;
    public static DrawingManager getINSTANCE(){
        if (INSTANCE == null) INSTANCE = new DrawingManager();
        return INSTANCE;
    }

    private final SpriteBatch batch;
    private final HashMap<String, Texture> sprs;
    private final float pixelScale;


    private final HashMap<Integer,ArrayList<DrawingData>> drawQueue = new HashMap<>();


    private final BitmapFont font;

    // screen shake stuff
    private int screenShake = 0;
    private float screenShakeOffset = 0f;
    private static final int maximumScreenShake = 40;

    public DrawingManager(){
        sprs = new HashMap<>();
        SpriteLoadList.loadAllSprites(this);
        //font stuff
        // TODO : get a custom font (required for commercial usage)
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.getFileHandle("fonts/AGoblinAppears-o2aV.ttf", Files.FileType.Internal));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        batch = new SpriteBatch();
        parameter.size = 25;
        font = generator.generateFont(parameter);


        OrthographicCamera cam = new OrthographicCamera();
        cam.setToOrtho(false, (float)Gdx.graphics.getWidth()*2, (float)Gdx.graphics.getHeight()*2);
        cam.update();
        pixelScale = Gdx.graphics.getHeight() / 160f;
        batch.setProjectionMatrix(cam.combined);
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
        // TODO : somehow do ui scaling
        // screen scaling

        // screen shake update
        if (screenShake > 0){
            screenShake--;
            if (screenShake > maximumScreenShake) screenShake -= screenShake>>1;
            screenShakeOffset = Math.min(screenShake,maximumScreenShake);
            if (screenShake%2 == 0) screenShakeOffset *= -1;
        }

        batch.begin();
        for (ArrayList<DrawingData> layer : drawQueue.values()) {
            for (DrawingData data: layer) {
                if (data.getType() == DrawingDataType.Sprite)
                    if (data.affectedByScreenShake())
                        batch.draw(data.getTexture(), data.getX(), data.getY() + screenShakeOffset * 3.5f, data.getTexture().getWidth()*pixelScale * data.getScale(), data.getTexture().getHeight()*pixelScale * data.getScale());
                    else
                        batch.draw(data.getTexture(), data.getX(), data.getY(), data.getTexture().getWidth()*pixelScale * data.getScale(), data.getTexture().getHeight()*pixelScale * data.getScale());
                else
                    font.draw(batch, data.getText(), data.getX(), data.getY()); // TODO : different font sizes

            }
            layer.clear();
        }

        batch.end();
    }

    public void drawText(String text, float x, float y, int zIndex, float scale){
        if (!drawQueue.containsKey(zIndex))
            drawQueue.put(zIndex, new ArrayList<>());
        drawQueue.get(zIndex).add(new TextData(text, x/4*pixelScale, y/4*pixelScale, scale));
    }

    public void drawText(String text, float x, float y){
        drawText(text, x, y, 5, 1f);
    }
    public void drawText(String text, float x, float y, int zIndex) {
        drawText(text, x, y, zIndex, 1f);
    }

    // draws sprite with the default sprite index
    // default sprite index is 0
    // use to draw only in game graphics
    public void drawGame(String textureName, float x, float y){
        draw(textureName, x, y ,0, true);
    }

    // draws with set index
    // use to draw only in game graphics
    public void drawGame(String textureName, float x, float y, int zIndex){
        draw(textureName, x, y, (byte) zIndex, true);
    }
    // use to draw ui stuff
    public void draw(String textureName, float x, float y, int zIndex, boolean affectedByScreenShake){
        draw(textureName, x, y, zIndex, affectedByScreenShake, 1);
    }

    public void draw(String textureName, float x, float y, int zIndex, boolean affectedByScreenShake, float scale){
        if (textureName != null){
            Texture text = sprs.get(textureName);

            if (!drawQueue.containsKey(zIndex))
                drawQueue.put(zIndex, new ArrayList<>());
            drawQueue.get(zIndex).add(new SpriteData(text, x/4*pixelScale, y/4*pixelScale, affectedByScreenShake, scale));
        }
    }

    public void draw(String textureName, float x, float y, int zIndex){
        draw(textureName, x, y, zIndex, true);
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

    public float getPixelScale(){
        return pixelScale;
    }


    public void addScreenShake(int number){
        this.screenShake += number;
    }
}

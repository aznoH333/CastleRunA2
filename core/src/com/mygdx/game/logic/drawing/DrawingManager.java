package com.mygdx.game.logic.drawing;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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


    private final ArrayList<DrawingData>[] drawQueue = new ArrayList[8];


    private final BitmapFont font;
    private final BitmapFont fontShadow;

    // screen shake stuff
    private int screenShake = 0;
    private float screenShakeOffset = 0f;
    private static final int maximumScreenShake = 40;

    public DrawingManager(){
        pixelScale = Gdx.graphics.getHeight() / 160f;

        sprs = new HashMap<>();
        SpriteLoadList.loadAllSprites(this);
        //font stuff
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.getFileHandle("fonts/PixeloidSansBold-RpeJo.ttf", Files.FileType.Internal));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();


        // generate font
        batch = new SpriteBatch();
        parameter.size = (int) (10 * pixelScale);
        // main font
        parameter.color.r = 0.76f;
        parameter.color.g = 0.74f;
        parameter.color.b = 0.79f;
        font = generator.generateFont(parameter);
        // shadow font
        parameter.color.r = 0.33f;
        parameter.color.g = 0.31f;
        parameter.color.b = 0.35f;
        fontShadow = generator.generateFont(parameter);


        for (int i = 0; i < drawQueue.length; i++) {
            drawQueue[i] = new ArrayList<>();
        }

        OrthographicCamera cam = new OrthographicCamera();
        cam.setToOrtho(false, (float)Gdx.graphics.getWidth()*2, (float)Gdx.graphics.getHeight()*2);
        cam.update();
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
        // screen shake update
        if (screenShake > 0){
            screenShake--;
            if (screenShake > maximumScreenShake) screenShake -= screenShake>>1;
            screenShakeOffset = Math.min(screenShake,maximumScreenShake);
            if (screenShake%2 == 0) screenShakeOffset *= -1;
        }

        batch.begin();

        for (ArrayList<DrawingData> layer : drawQueue) {
            for (DrawingData data: layer) {
                // set color
                batch.setColor(data.getColor());

                if (data.getType() == DrawingDataType.Sprite)
                    batch.draw(
                            data.getTexture(),
                            data.getX(),
                            data.getY() + (data.affectedByScreenShake() ? screenShakeOffset * 3.5f : 0),
                            data.getTexture().getWidth() * pixelScale * data.getXScale(),
                            data.getTexture().getHeight() * pixelScale * data.getYScale());
                else{
                    fontShadow.draw(batch, data.getText(), data.getX() + pixelScale, data.getY() - pixelScale);
                    font.draw(batch, data.getText(), data.getX(), data.getY());
                }


            }
            layer.clear();
        }

        batch.end();
    }

    public void drawText(String text, float x, float y, int zIndex, float scale){ // TODO : text coloring
        drawQueue[zIndex+1].add(new TextData(text, x/4*pixelScale, y/4*pixelScale, scale, new Color(1,1,1,1)));
    }

    public void drawText(String text, float x, float y){
        drawText(text, x, y, 5, 1f);
    }
    public void drawText(String text, float x, float y, int zIndex) {
        drawText(text, x, y, zIndex, 1f);
    }

    // draws with set index
    // use to draw only in game graphics
    public void drawGame(String textureName, float x, float y, int zIndex){
        draw(textureName, x, y, (byte) zIndex, true);
    }
    // use to draw ui stuff
    public void draw(String textureName, float x, float y, int zIndex, boolean affectedByScreenShake){
        draw(textureName, x, y, zIndex, affectedByScreenShake, 1, 1, false);
    }

    public void draw(String textureName, float x, float y, int zIndex, boolean affectedByScreenShake, float scale){
        draw(textureName, x, y, zIndex, affectedByScreenShake, scale, 1, false);
    }

    public void draw(String textureName, float x, float y, int zIndex, boolean affectedByScreenShake, float scale, float opacity){
        draw(textureName, x, y, zIndex, affectedByScreenShake, scale, opacity, false);
    }
    // this is a bad workaround (._.)
    public void draw(String textureName, float x, float y, int zIndex, boolean affectedByScreenShake, float scale, float opacity, boolean stretch){
        /*
        if (sprs.get(textureName) == null) {
            System.out.println(textureName);
            System.out.println(x);
            System.out.println(y);
        }else
            drawQueue[zIndex+1].add(new SpriteData(sprs.get(textureName), x/4*pixelScale, y/4*pixelScale, affectedByScreenShake, scale, opacity));
        */
    }



    // TODO : remove all other drawing functions, use only this one
    public void draw(String textureName, float x, float y, int zIndex, float xScale, float yScale, Color color, boolean affectedByScreenShake){
        if (sprs.get(textureName) == null) {
            System.out.println(textureName);
            System.out.println(x);
            System.out.println(y);
        }else
            drawQueue[zIndex+1].add(new SpriteData(sprs.get(textureName), x/4*pixelScale, y/4*pixelScale, affectedByScreenShake, xScale, yScale, color));
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

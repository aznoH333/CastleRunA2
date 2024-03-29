package com.mygdx.game.logic.drawing;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
    private final HashMap<String, TextureRegion> sprs;
    private final HashMap<String, TextureLoadingHelperObject> textures;
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
        textures = new HashMap<>();
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


        /*
            I don't know why, but...
            for whatever reason

            changing parameter.color
            changes Colors.WHITE ????

            took me a while to figure this shit out >:(
         */
        parameter.color.r = 1f;
        parameter.color.g = 1f;
        parameter.color.b = 1f;



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
            for (TextureLoadingHelperObject t: textures.values()) {
                t.getTexture().dispose();
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
                //batch.setColor(new Color(1f, 1f, 1f, 1f));

                if (data.getType() == DrawingDataType.Sprite)
                    batch.draw(
                            data.getSprite(),
                            data.getX(),
                            data.getY() + (data.affectedByScreenShake() ? screenShakeOffset * 3.5f : 0),
                            data.getSprite().getRegionWidth() * pixelScale * data.getXScale(),
                            data.getSprite().getRegionHeight() * pixelScale * data.getYScale());
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
        drawQueue[zIndex+1].add(new TextData(text, x/4*pixelScale, y/4*pixelScale, scale, Color.WHITE));
    }

    public void drawText(String text, float x, float y){
        drawText(text, x, y, 5, 1f);
    }
    public void drawText(String text, float x, float y, int zIndex) {
        drawText(text, x, y, zIndex, 1f);
    }


    // use to draw ui stuff
    public void draw(String textureName, float x, float y, int zIndex, boolean affectedByScreenShake){
        draw(textureName, x, y, zIndex, 1, 1, Color.WHITE, affectedByScreenShake);
    }
    // full drawing method
    // TODO : remove null check
    public void draw(String textureName, float x, float y, int zIndex, float xScale, float yScale, Color color, boolean affectedByScreenShake){
        if (sprs.get(textureName) == null) {
            System.out.println("failed to find texture : " + textureName);

        }

        else
            drawQueue[zIndex+1].add(new SpriteData(sprs.get(textureName), x/4*pixelScale, y/4*pixelScale, affectedByScreenShake, xScale, yScale, color));
    }

    public void draw(String textureName, float x, float y, int zIndex){
        draw(textureName, x, y, zIndex, 1, 1, Color.WHITE, true);
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
            loadTextureAndSprite(name + i, path + adder + i + ".png");
        }
    }

    public void loadTextureAndSprite(String name, String path){
        textures.put(name,new TextureLoadingHelperObject(new Texture("sprites/" + path),0,0));
        sprs.put(name, new Sprite(textures.get(name).getTexture()));
    }

    public void loadTexture(String name, String path, int spriteWidth, int spriteHeight){
        textures.put(name, new TextureLoadingHelperObject(new Texture("sprites/" + path), spriteWidth, spriteHeight));
    }

    public void loadSpriteFromTexture(String spriteName, String textureName, int startX, int startY, int width, int height){
        sprs.put(spriteName, new TextureRegion(textures.get(textureName).getTexture(), startX, startY, width, height));
    }



    public float getPixelScale(){
        return pixelScale;
    }


    public void addScreenShake(int number){
        this.screenShake += number;
    }

    public void loadSpriteCollectionFromHelper(String spriteNames, String textureName, int count){
        for (int i = 0; i < count; i++){
            sprs.put(spriteNames + i, textures.get(textureName).getNext());
        }
    }

    public void loadSpriteFromHelper(String spriteName, String textureName){
        sprs.put(spriteName, textures.get(textureName).getNext());
    }
}

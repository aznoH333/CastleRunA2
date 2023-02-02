package com.mygdx.game.logic.drawing;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureLoadingHelperObject {

    private final Texture texture;
    private final int spriteWidth;
    private final int spriteHeight;
    private int currentX = 0;
    private int currentY = 0;

    public TextureLoadingHelperObject(Texture texture, int spriteWidth, int spriteHeight){
        this.texture = texture;
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
    }

    public Texture getTexture(){
        return texture;
    }

    public TextureRegion getNext(){
        System.out.println(currentX);
        System.out.println((float) currentX);
        TextureRegion output = new TextureRegion(texture, currentX, currentY, spriteWidth, spriteHeight);
        currentX += spriteWidth + 1;
        if (currentX >= texture.getWidth()){
            currentX = 0;
            currentY += spriteHeight + 1;
        }
        return output;
    }
}

package com.mygdx.game.data;

public class BackgroundLayer {

    private final String sprite;
    private final float parralax;
    private final float windSpeed;

    public BackgroundLayer(String sprite, float parallax, float windSpeed){
        this.parralax = parallax;
        this.sprite = sprite;
        this.windSpeed = windSpeed;
    }

    public BackgroundLayer(String sprite, float parralax){
        this.sprite = sprite;
        this.parralax = parralax;
        this.windSpeed = 0;
    }

    public float getParralax() {
        return parralax;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public String getSprite(){
        return sprite;
    }
}

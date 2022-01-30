package com.mygdx.game.data;

public class Background {
    private final String[] backgrounds;
    private final float[] parallaxes;

    public Background(String[] backgrounds, float[] parallaxes){
        this.backgrounds = backgrounds;
        this.parallaxes = parallaxes;
    }

    public String[] getBackgrounds(){
        return backgrounds;
    }

    public float[] getParallaxes() {
        return parallaxes;
    }
}

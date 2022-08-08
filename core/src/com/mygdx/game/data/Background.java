package com.mygdx.game.data;

public class Background {

    private final BackgroundLayer[] layers;

    public Background(BackgroundLayer[] layers){
        this.layers = layers;
    }

    public BackgroundLayer[] getLayers() {
        return layers;
    }
}

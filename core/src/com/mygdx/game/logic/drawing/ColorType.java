package com.mygdx.game.logic.drawing;

public enum ColorType {
    Normal(1,1,1,1),
    Opacity25(1,1,1,0.25f),
    Opacity50(1,1,1,0.5f),
    Opacity75(1,1,1,0.75f);

    public final float r;
    public final float g;
    public final float b;
    public final float a;

    ColorType(float r, float g, float b, float a){
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }
}

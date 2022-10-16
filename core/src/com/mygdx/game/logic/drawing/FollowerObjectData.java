package com.mygdx.game.logic.drawing;

public class FollowerObjectData {
    private float x;
    private final float y;
    private final String sprite;
    private final long creationTime;

    public FollowerObjectData(float x, float y, String sprite, long creationTime) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.creationTime = creationTime;
    }

    public float getX() {
        return x;
    }

    public void shiftX(float x){
        this.x -= x;
    }

    public float getY() {
        return y;
    }

    public String getSprite() {
        return sprite;
    }

    public long getCreationTime(){
        return creationTime;
    }

}

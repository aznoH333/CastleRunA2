package com.mygdx.game.data;

public class Particle {
    private final int frameRate;
    private final String[] sprs;
    private int lifeTime;
    private String currentSpr;
    private boolean destroy = false;
    private int nextFrame = 0;

    private float x;
    private float y;
    private float xM;
    private float yM;
    private float gravity;
    private int currentIndex = 0;

    public Particle(String[] sprs, int lifeTime, int frameRate) {
        this.frameRate = frameRate;
        this.sprs = sprs;
        // if lifetime == 0 set lifetime to the length of the animation
        if (lifeTime == 0){this.lifeTime = frameRate * sprs.length;}
        else this.lifeTime = lifeTime;
        currentSpr = sprs[0];
    }

    private void setPosition(float x, float y, float xM, float yM, float gravity) {
        this.x = x;
        this.y = y;
        this.xM = xM;
        this.yM = yM;
        this.gravity = gravity;
        nextFrame = frameRate;
    }

    public void setLifeTime(int lifeTime){
        this.lifeTime = lifeTime;
    }

    public void update() {
        x += xM;
        y += yM;
        yM -= gravity;
        lifeTime--;

        // sprite changes
        // happens only if the particle has a nonzero frame rate
        if (frameRate != 0) {
            if (nextFrame <= 0){
                nextFrame = frameRate;
                currentIndex++;
                if (currentIndex >= sprs.length) currentIndex = 0;
                currentSpr = sprs[currentIndex];
            }
            nextFrame--;
        }

        if (lifeTime <= 0) {
            destroy = true;
        }
    }

    public String getSpr(){
        return currentSpr;
    }
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public boolean isMarked(){
        return destroy;
    }

    public void shiftX(float shiftBy){
        x += shiftBy;
    }

    public Particle clone(float x, float y, float xM, float yM, float gravity) {
        Particle temp = new Particle(sprs, lifeTime, frameRate);
        temp.setPosition(x, y, xM, yM, gravity);
        return temp;
    }
}

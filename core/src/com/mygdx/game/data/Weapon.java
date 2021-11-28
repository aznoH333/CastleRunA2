package com.mygdx.game.data;

public abstract class Weapon {
    protected final String sprite;

    public Weapon(String sprite){
        this.sprite = sprite;
    }

    public String getSprite(){
        return sprite;
    }

    // attack functions
    public abstract void attack();
    public abstract void chargedAttack();
}

package com.mygdx.game.logic.player;

import com.mygdx.game.logic.entities.EntityManager;

public abstract class Weapon {
    protected final String sprite;
    protected final EntityManager ent;

    public Weapon(String sprite){
        this.sprite = sprite;
        this.ent = EntityManager.getINSTANCE();
    }

    public String getSprite(){
        return sprite;
    }

    // attack functions
    public abstract void attack(float x, float y);
    public abstract void chargedAttack(float x, float y);
}

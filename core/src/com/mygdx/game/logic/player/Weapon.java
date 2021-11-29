package com.mygdx.game.logic.player;

import com.mygdx.game.logic.entities.EntityManager;

public abstract class Weapon {
    protected final String sprite;
    protected final EntityManager ent;
    private final int attackCost;
    private final int chargedAttackCost;

    public Weapon(String sprite, int attackCost, int chargedAttackCost){
        this.sprite = sprite;
        this.ent = EntityManager.getINSTANCE();
        this.attackCost = attackCost;
        this.chargedAttackCost = chargedAttackCost;
    }

    public String getSprite(){
        return sprite;
    }

    // attack functions
    public abstract void attack(float x, float y);
    public abstract void chargedAttack(float x, float y);
    //costs
    public int getAttackCost(){
        return attackCost;
    }
    public int getChargedAttackCost(){
        return chargedAttackCost;
    }
}

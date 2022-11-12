package com.mygdx.game.logic.player;

import com.mygdx.game.logic.entities.EntityManager;

public abstract class Weapon {
    protected final String sprite;
    protected final EntityManager ent;
    private final int attackCost;
    private final int chargedAttackCost;
    private final String useSound;
    private final String chargedUseSound;
    private final String name;
    private final String wIcon;

    public Weapon(String name, String sprite, String wIcon, int attackCost, int chargedAttackCost){
        this.sprite = sprite;
        this.ent = EntityManager.getINSTANCE();
        this.attackCost = attackCost;
        this.chargedAttackCost = chargedAttackCost;
        useSound = null;
        chargedUseSound = null;
        this.name = name;
        this.wIcon = wIcon;
    }

    public Weapon(String name, String sprite, String wIcon,  int attackCost, int chargedAttackCost, String useSound, String chargedUseSound){
        this.sprite = sprite;
        this.ent = EntityManager.getINSTANCE();
        this.attackCost = attackCost;
        this.chargedAttackCost = chargedAttackCost;
        this.useSound = useSound;
        this.chargedUseSound = chargedUseSound;
        this.name = name;
        this.wIcon = wIcon;
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

    public String getUseSound() {
        return useSound;
    }

    public String getChargedUseSound() {
        return chargedUseSound;
    }

    public String getName(){
        return name;
    }

    public String getWeaponIcon(){
        return wIcon;
    }
}

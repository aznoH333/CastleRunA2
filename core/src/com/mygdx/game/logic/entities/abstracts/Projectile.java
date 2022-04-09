package com.mygdx.game.logic.entities.abstracts;

import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.entities.effects.IStatusEffect;

import java.util.ArrayList;


public abstract class Projectile extends Entity{

    private final ArrayList<IStatusEffect> effects = new ArrayList<>();

    public Projectile(float x, float y, float xSize, float ySize, int hp, Team team) {
        super(x, y, xSize, ySize, hp, team);
    }

    public void addStatusEffect(IStatusEffect effect){
        effects.add(effect);
    }

    protected void applyStatusEffect(Enemy other){
        for (IStatusEffect effect: effects) {
            other.applyEffect(effect);
        }
    }
}

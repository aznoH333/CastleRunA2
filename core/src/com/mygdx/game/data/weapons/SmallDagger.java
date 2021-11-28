package com.mygdx.game.data.weapons;

import com.mygdx.game.data.Weapon;

public class SmallDagger extends Weapon {


    public SmallDagger(String sprite) {
        super(sprite);
    }

    @Override
    public void attack(float x, float y){
        ent.spawnEntity("dagger",x,y);
    }

    @Override
    public void chargedAttack(float x, float y) {
        ent.spawnEntity("charged dagger",x,y);
    }
}

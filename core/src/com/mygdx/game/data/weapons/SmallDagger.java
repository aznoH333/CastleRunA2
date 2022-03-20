package com.mygdx.game.data.weapons;

import com.mygdx.game.logic.player.Weapon;

public class SmallDagger extends Weapon {


    public SmallDagger() {
        super("dagger0",1,1);
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

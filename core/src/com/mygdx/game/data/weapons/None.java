package com.mygdx.game.data.weapons;

import com.mygdx.game.logic.player.Weapon;

public class None extends Weapon {
    public None() {
        super("Nothing", "wIcon0",null,999,999);
    }

    @Override
    public void attack(float x, float y) {
        // do nothing lmao
    }
    @Override
    public void chargedAttack(float x, float y) {

    }
}

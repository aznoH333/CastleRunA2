package com.mygdx.game.data.weapons;

import com.mygdx.game.logic.player.Weapon;

public class None extends Weapon {
    public None(String sprite) {
        super(sprite);
    }

    @Override
    public void attack(float x, float y) {
        // do nothing lmao
    }
    @Override
    public void chargedAttack(float x, float y) {

    }
}

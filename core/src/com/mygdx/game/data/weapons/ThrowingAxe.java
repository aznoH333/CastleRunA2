package com.mygdx.game.data.weapons;

import com.mygdx.game.logic.player.Weapon;

public class ThrowingAxe extends Weapon {

    public ThrowingAxe() { super("axe0", 0, 1); }

    @Override
    public void attack(float x, float y) {
        ent.spawnEntity("sword swipe",x + 48,y);
    }

    @Override
    public void chargedAttack(float x, float y) {

    }
}

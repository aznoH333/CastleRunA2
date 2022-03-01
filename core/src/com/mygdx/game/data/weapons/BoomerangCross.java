package com.mygdx.game.data.weapons;

import com.mygdx.game.logic.player.Weapon;

public class BoomerangCross extends Weapon {
    public BoomerangCross() {
        super("cross0", 1, 2);
    }

    @Override
    public void attack(float x, float y) {
        ent.spawnEntity("cross",x,y);
    }

    @Override
    public void chargedAttack(float x, float y) {
        ent.spawnEntity("charged cross",x,y);
    }
}

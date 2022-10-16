package com.mygdx.game.data.weapons;

import com.mygdx.game.logic.player.Weapon;

public class BoomerangCross extends Weapon {
    public BoomerangCross() {
        super("cross0", 1, 3);
    }

    @Override
    public void attack(float x, float y) {
        ent.spawnEntity("cross",x,y);
    }

    @Override
    public void chargedAttack(float x, float y) {
        ent.spawnEntity("charged cross",x-16,y-32,1);
        ent.spawnEntity("charged cross",x-16,y-32,2);
        ent.spawnEntity("charged cross",x-16,y-32,3);
        ent.spawnEntity("charged cross",x-16,y-32,4);
    }
}

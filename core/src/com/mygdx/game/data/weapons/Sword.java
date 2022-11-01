package com.mygdx.game.data.weapons;

import com.mygdx.game.logic.player.Weapon;

public class Sword extends Weapon {

    public Sword() {
        super("Sword","sword0", 0, 1, "weapon1", "weapon1");
    }

    @Override
    public void attack(float x, float y) {
        ent.spawnEntity("sword swipe",x + 48,y);
    }

    @Override
    public void chargedAttack(float x, float y) {
        ent.spawnEntity("charged sword swipe",x + 48,y);
    }
}

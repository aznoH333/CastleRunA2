package com.mygdx.game.data.weapons;

import com.mygdx.game.logic.player.Weapon;

public class ThrowingAxe extends Weapon {

    public ThrowingAxe() { super("Axe", "wIcon0", "axe0", 1, 1); }

    @Override
    public void attack(float x, float y) {
        ent.spawnEntity("throwing axe",x + 48,y);
    }

    @Override
    public void chargedAttack(float x, float y) {
        ent.spawnEntity("charged throwing axe",x + 48,y);
    }
}

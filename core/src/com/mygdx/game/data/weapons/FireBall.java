package com.mygdx.game.data.weapons;

import com.mygdx.game.logic.entities.EntityManager;
import com.mygdx.game.logic.player.Weapon;

public class FireBall extends Weapon {
    private final static EntityManager ent = EntityManager.getINSTANCE();

    public FireBall() {
        super("fireball0", 2, 4);
    }

    @Override
    public void attack(float x, float y) {

        ent.spawnEntity("fireball", x, y+16);
    }
    // FIXME : screen fallback is dumb and needs to be fixed

    @Override
    public void chargedAttack(float x, float y) {

    }
}

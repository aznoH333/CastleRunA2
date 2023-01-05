package com.mygdx.game.data.weapons;

import com.mygdx.game.logic.entities.EntityManager;
import com.mygdx.game.logic.player.Weapon;

public class Bomb extends Weapon {



    private final static EntityManager ent = EntityManager.getINSTANCE();


    public Bomb() {
        super("Bomb", "wIcon4","bomb4", 2, 4);

    }

    @Override
    public void attack(float x, float y) {
        ent.spawnEntity("bomb",x + 32,y + 32);
        // TODO : sound
        // FIXME : collisions with multiple enemies
    }

    @Override
    public void chargedAttack(float x, float y) {
        ent.spawnEntity("bomb",x + 16,y + 16);
        ent.spawnEntity("bomb",x + 32,y + 32);
        ent.spawnEntity("bomb",x,y + 20);


    }
}

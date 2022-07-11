package com.mygdx.game.data.weapons;

import com.mygdx.game.Game;
import com.mygdx.game.logic.entities.EntityManager;
import com.mygdx.game.logic.player.Weapon;

public class Bubble extends Weapon {

    private final static EntityManager ent = EntityManager.getINSTANCE();
    public Bubble() {
        super("bubble4", 1, 2);
    }

    @Override
    public void attack(float x, float y) {
        for (int i = 0; i < Game.getGeneralRandom().nextInt(3)+3; i++) {
            ent.spawnEntity("bubble",x+32,y+16);
        }
        // TODO : sound
        // TODO : a reason for the player to move faster and not just wait for energy regen
    }

    @Override
    public void chargedAttack(float x, float y) {
        for (int i = 0; i < Game.getGeneralRandom().nextInt(4)+6; i++) {
            ent.spawnEntity("bubble",x+32,y+16);
        }
    }
}

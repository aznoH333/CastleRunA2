package com.mygdx.game.data.weapons;

import com.mygdx.game.Game;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.entities.EntityManager;
import com.mygdx.game.logic.entities.ParticleManager;
import com.mygdx.game.logic.player.Weapon;

import java.util.Random;

public class Rocks extends Weapon {
    private final static EntityManager ent = EntityManager.getINSTANCE();
    private final static ParticleManager part = ParticleManager.getINSTANCE();
    private final static Random r = Game.getGeneralRandom();
    public Rocks() {
        super("Rock tome", "wIcon3","rock0", 3, 5);
    }

    @Override
    public void attack(float x, float y) {
        // TODO : sound
        DrawingManager.getINSTANCE().addScreenShake(20);
        for (int i = 0; i < 3 + r.nextInt(2); i++){
            ent.spawnEntity("rock", x + 64 + (r.nextFloat()*256), 1280 + r.nextInt(128));
        }
        // spawn small rocks
        for (int i = 0; i < 6 + Game.getGeneralRandom().nextInt(2); i++){
            part.addParticle("smallRock" + r.nextInt(2), x + 64 + r.nextInt(256) ,1280 + r.nextInt(128), 0, -(r.nextFloat() * 5 + 20), 0, 400);
        }
    }

    @Override
    public void chargedAttack(float x, float y) {
        DrawingManager.getINSTANCE().addScreenShake(30);
        for (int i = 0; i < 20 + r.nextInt(10); i++){
            ent.spawnEntity("rock",  (r.nextFloat()*Game.gameWorldWidth) - 64, 1280 + r.nextInt(128));
        }
        // spawn small rocks
        for (int i = 0; i < 6 + Game.getGeneralRandom().nextInt(2); i++){
            part.addParticle("smallRock" + r.nextInt(2), (r.nextFloat()*Game.gameWorldWidth) - 64 ,1280 + r.nextInt(128), 0, -(r.nextFloat() * 5 + 20), 0, 400);
        }
    }
}

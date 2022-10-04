package com.mygdx.game.entities.environment;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.SoundManager;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.entities.EntityManager;
import com.mygdx.game.logic.entities.ParticleManager;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.level.LevelManager;

import java.util.Random;

public class Furniture extends Entity {

    private final String sprite;
    private final static float coinSpawnChance = 0.15f;

    public Furniture(float x, float y) {
        super(x, y, 64, 64, 1, Team.Environment);
        sprite = "furniture" + Game.getSeededRandom().nextInt(6);

    }

    @Override
    public void update(LevelManager lvl, Random r) {
        if (!lvl.collidesWithLevel(this)) destroy();
    }

    @Override
    public void draw(DrawingManager spr) {
        spr.draw(sprite,x, y, 0);
    }

    @Override
    public void onCollide(Entity other) {
        if (other.getTeam() == Team.PlayerProjectiles){
            destroy();
        }
    }

    @Override
    public Entity getCopy(float x, float y) {
        return new Furniture(x, y + 64);
    }

    @Override
    public void onDestroy() {
        final Random r = Game.getGeneralRandom();
        final ParticleManager part = ParticleManager.getINSTANCE();
        // spawn particles
        // TODO : sound
        SoundManager.getINSTANCE().playSound("chest");

        for (int i = 0; i < r.nextInt(5) + 5; i++)
            part.addParticle("furniture" + r.nextInt(4),x,y,r.nextInt(10)-5,r.nextInt(10),0.5f,r.nextInt(30) + 30);

        DrawingManager.getINSTANCE().addScreenShake(3);

        // spawn coins
        if (r.nextFloat() < coinSpawnChance) {
            do{
                EntityManager.getINSTANCE().spawnEntity("coin pickup",x+((int)xSize>>2),y+((int)xSize>>2));
            } while (r.nextBoolean());
        }
    }
}

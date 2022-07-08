package com.mygdx.game.entities.enemies;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.SoundManager;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.entities.ParticleManager;
import com.mygdx.game.logic.entities.abstracts.Enemy;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.level.LevelManager;

import java.util.Random;

public class GhostSkull extends Enemy {

    private final static float horizontalSpeed = 2.5f;

    public GhostSkull(float x, float y) {
        super(x, y, 48, 48, 1, Team.Enemies);
    }

    @Override
    public void update(LevelManager lvl, Random r) {
        x -= horizontalSpeed;
        y += Math.sin((lvl.getDistance() + x) / 40) * 4;
    }

    @Override
    public void draw(DrawingManager spr) {
        spr.draw("ghost_skull" + ((Game.Time()/24)%2),x-8,y-8,1);
    }

    @Override
    public void onCollide(Entity other) {

    }

    @Override
    public Entity getCopy(float x, float y) {
        return new GhostSkull(x, y + 64);
    }

    @Override
    public void onDestroy() {
        // TODO: more enemy sounds
        Random r = Game.getGeneralRandom();
        ParticleManager part = ParticleManager.getINSTANCE();
        SoundManager.getINSTANCE().playSound("enemyDeath1");
        DrawingManager.getINSTANCE().addScreenShake(7);
        // spawn 10 - 20 gore particles
        for (int i = 0; i < r.nextInt(5) + 5; i++)
            part.addParticle("boneGore" + r.nextInt(3),x+(xSize/2),y+(ySize/2),r.nextInt(6)-3,r.nextInt(20)-5,0.7f,r.nextInt(20) + 20);
    }
}

package com.mygdx.game.entities.player.Projectiles;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.entities.Entity;
import com.mygdx.game.logic.entities.ParticleManager;
import com.mygdx.game.logic.level.LevelManager;

import java.util.Random;


public class OrbAttack extends Entity {

    private static final ParticleManager part = ParticleManager.getINSTANCE();
    private static final float xM = 5.5f;
    private static final float gravity = 0.04f;
    private float yM = 0;

    public OrbAttack(float x, float y, float xSize, float ySize, int hp) {
        super(x, y, xSize, ySize, hp, Team.PlayerProjectiles);
    }

    @Override
    public void update(LevelManager lvl, Random r) {
        x += xM;
        y += yM;
        yM -= gravity;

        if (Game.Time() % 6 == 0){
            part.addParticle("electricity",x,y,0,0,0.1f);
        }
    }

    @Override
    public void draw(DrawingManager spr) {

    }

    @Override
    public void onCollide(Entity other) {
        if (other.getTeam() == Team.Enemies){
            other.takeDamage(1);
            destroy();
        }
    }

    @Override
    public Entity getCopy(float x, float y) {
        return new OrbAttack(x,y, xSize, ySize, hp);
    }

    @Override
    public void onDestroy() {

    }
}

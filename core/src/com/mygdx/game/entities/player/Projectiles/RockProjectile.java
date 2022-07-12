package com.mygdx.game.entities.player.Projectiles;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.entities.ParticleManager;
import com.mygdx.game.logic.entities.abstracts.Enemy;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.entities.abstracts.Projectile;
import com.mygdx.game.logic.level.LevelManager;

import java.util.Random;

public class RockProjectile extends Projectile {

    private final int rockSpr;
    private final float yM;

    public RockProjectile(float x, float y) {
        super(x, y, 64, 64, 1, Team.PlayerProjectiles, 0);
        rockSpr = Game.getGeneralRandom().nextInt(2) + 3;
        yM = -(Game.getGeneralRandom().nextFloat() * 5 + 20);
    }

    @Override
    public void draw(DrawingManager spr) {
        spr.draw("rock" + rockSpr, x, y, 1);
    }

    @Override
    public Entity getCopy(float x, float y) {
        return new RockProjectile(x, y);
    }

    @Override
    public void onDestroy() {
        DrawingManager.getINSTANCE().addScreenShake(7);
        ParticleManager p = ParticleManager.getINSTANCE();
        Random r = Game.getGeneralRandom();
        for (int i = 0; i < r.nextInt(8)+8; i++) {
            if (r.nextFloat() < 0.75f){
                // spawn dust
                p.addParticle("smoke", x + r.nextInt((int)xSize) ,y + r.nextInt((int)ySize), r.nextFloat() * 0.5f - 0.25f, r.nextFloat() * 0.5f - 0.25f , 0, r.nextInt(20) + 10);

            }else{
                //spawn rock
                p.addParticle("smallRock" + r.nextInt(2), x + r.nextInt((int)xSize) ,y + r.nextInt((int)ySize), r.nextFloat() * 6 - 3, -r.nextFloat() * 5, 0.5f, 420);
            }
        }
        // TODO : sound

    }

    @Override
    protected void onEnemyHit(Enemy other) {
        other.takeDamage(1);
        destroy();
    }

    @Override
    protected void projectileUpdate(LevelManager lvl, Random r) {
        this.y += yM;
        if (lvl.collidesWithLevel(this)) destroy();
    }
}

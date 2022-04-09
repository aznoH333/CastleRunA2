package com.mygdx.game.entities.player.Projectiles;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.entities.abstracts.Enemy;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.entities.abstracts.Projectile;
import com.mygdx.game.logic.level.LevelManager;
import com.mygdx.game.logic.drawing.DrawingManager;

import java.util.Random;

public class Cross extends Projectile {

    private int animationIndex = 0;
    private float xM = 10f;
    private int thrownTimer = 32;
    private final static float returnForce = 0.25f;
    public Cross(float x, float y, float xSize, float ySize, int hp) {
        super(x, y, xSize, ySize, hp, Team.PlayerProjectiles,24);
    }

    @Override
    protected void onEnemyHit(Enemy other) {
        other.takeDamage(1);
    }

    @Override
    protected void projectileUpdate(LevelManager lvl, Random r) {
        x += xM;
        xM -= returnForce;
        if (Game.Time() % 4 == 0) animationIndex = (animationIndex + 1) % 3;
        if (thrownTimer > 0) thrownTimer--;
    }


    @Override
    public void draw(DrawingManager spr) {
        spr.drawGame("cross" + (animationIndex+1),x-16,y,2);
    }

    @Override
    public void onCollide(Entity other) {
        if (other.getTeam() == Team.Player && thrownTimer == 0){
            destroy();
        }
        super.onCollide(other);
    }



    @Override
    public Entity getCopy(float x, float y) {
        return new Cross(x,y,xSize,ySize,hp);
    }

    @Override
    public void onDestroy() {

    }
}

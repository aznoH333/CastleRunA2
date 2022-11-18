package com.mygdx.game.entities.player.Projectiles.cross;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.drawing.FollowerObject;
import com.mygdx.game.logic.entities.abstracts.Enemy;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.entities.abstracts.Projectile;
import com.mygdx.game.logic.level.LevelManager;

import java.util.Random;

public class Cross extends Projectile {

    private int animationIndex = 0;
    private float xM = 10f;
    private int thrownTimer = 32;
    private final static float returnForce = 0.25f;
    private final FollowerObject follower1 = new FollowerObject(1, 1, 0.75f);
    private final FollowerObject follower2 = new FollowerObject(1, 2, 0.5f);
    private final FollowerObject follower3 = new FollowerObject(1, 3, 0.25f);
    public Cross(float x, float y) {
        super(x, y,48,48,1, Team.PlayerProjectiles,24);
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

        String sprite = "cross" + (animationIndex+1);
        spr.drawGame(sprite,x-16,y,2);
        follower1.addCoordinate(x - 16, y ,sprite);
        follower2.addCoordinate(x - 16, y ,sprite);
        follower3.addCoordinate(x - 16, y ,sprite);
        follower1.draw();
        follower2.draw();
        follower3.draw();
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
        return new Cross(x,y);
    }

    @Override
    public void onDestroy() {

    }
}

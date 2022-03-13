package com.mygdx.game.entities.player.Projectiles;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.entities.Entity;
import com.mygdx.game.logic.level.LevelManager;
import com.mygdx.game.logic.drawing.DrawingManager;

import java.util.Random;

public class Cross extends Entity {

    private int animationIndex = 0;
    private float xM = 10f;
    private int thrownTimer = 32;
    private int pierceTimer = 0;
    private final static float returnForce = 0.25f;
    public Cross(float x, float y, float xSize, float ySize, int hp) {
        super(x, y, xSize, ySize, hp, Team.PlayerProjectiles);
    }

    @Override
    public void update(LevelManager lvl, Random r) {
        x += xM;
        xM -= returnForce;
        if (Game.Time() % 4 == 0) animationIndex = (animationIndex + 1) % 3;
        if (thrownTimer > 0) thrownTimer--;
        if (pierceTimer > 0) pierceTimer--;
    }

    @Override
    public void draw(DrawingManager spr) {
        spr.drawGame("cross" + (animationIndex+1),x-16,y,2);
    }

    @Override
    public void onCollide(Entity other) {
        if (other.getTeam() == Team.Enemies && pierceTimer == 0){
            other.takeDamage(1);
            pierceTimer = 24;
        }
        else if (other.getTeam() == Team.Player && thrownTimer == 0){
            destroy();
        }
        // TODO: hit priority
    }



    @Override
    public Entity getCopy(float x, float y) {
        return new Cross(x,y,xSize,ySize,hp);
    }

    @Override
    public void onDestroy() {

    }
}

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

public class ChargedCross extends Projectile {

    private float startPositionX;
    private final float startPositionY;
    private final static float rotationTime = 0.1f;
    private final static float speed = 1.5f;
    private final float offset;
    private float radius = 10;
    private int animationIndex = 0;
    //private final static ParticleManager part = ParticleManager.getINSTANCE();

    private final FollowerObject[] followers = new FollowerObject[]{
            new FollowerObject(1, 1, 0.7f),
            new FollowerObject(1, 2, 0.6f),
            new FollowerObject(1, 3, 0.5f),
            new FollowerObject(1, 4, 0.3f),
            new FollowerObject(1, 5, 0.2f)
    };



    public ChargedCross(float x, float y) {
        super(x, y,48,48,1,Team.PlayerProjectiles,32);
        startPositionX = x - 32;
        startPositionY = y + 32;
        offset = 0;
    }

    public ChargedCross(float x, float y, int param){
        super(x,y,48,48,1, Team.PlayerProjectiles,32);
        startPositionX = x + 32;
        startPositionY = y + 32;
        offset = param*15;
    }

    @Override
    protected void onEnemyHit(Enemy other) {
        other.takeDamage(1);
    }

    @Override
    protected void projectileUpdate(LevelManager lvl, Random r) {
        x = (float) (startPositionX + ((Math.sin((Game.Time() - offset) * rotationTime) ) * radius));
        y = (float) (startPositionY + ((Math.cos((Game.Time() - offset) * rotationTime)) * radius));

        radius += speed;

        if (Game.Time() % 4 == 0){
            animationIndex = (animationIndex + 1) % 3;
        //    if (r.nextBoolean())
        //        part.addParticle("miniSparkle",x + r.nextInt((int) xSize) - 16,y + r.nextInt((int) ySize) - 16,0,0,0, r.nextInt(30) + 30);
        }
    }


    @Override
    public void draw(DrawingManager spr) {
        String sprite = "cross" + (animationIndex+1);
        spr.draw(sprite,x-16,y,2);
        for (FollowerObject f: followers) {
            f.addCoordinate(x - 16, y, sprite);
            f.draw();
        }
    }

    @Override
    public void shiftX(float shiftBy){
        startPositionX += shiftBy;
        super.shiftX(shiftBy);
    }



    @Override
    public Entity getCopy(float x, float y) {
        return new ChargedCross(x,y);
    }

    @Override
    public Entity getCopy(float x, float y, int specialParam) {
        return new ChargedCross(x,y, specialParam);
    }



    @Override
    public void onDestroy() {

    }

}

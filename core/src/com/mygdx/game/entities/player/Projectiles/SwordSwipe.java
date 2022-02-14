package com.mygdx.game.entities.player.Projectiles;

import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.SoundManager;
import com.mygdx.game.logic.sprites.SpriteManager;
import com.mygdx.game.logic.entities.Entity;
import com.mygdx.game.logic.level.LevelManager;

import java.util.Random;

public class SwordSwipe extends Entity {

    protected int lifeTime = 12;
    protected boolean hurts = true;
    public SwordSwipe(float x, float y, float xSize, float ySize, int hp) {
        super(x, y, xSize, ySize, hp, Team.PlayerProjectiles);
        SoundManager.getINSTANCE().playSound("weapon1");
    }

    @Override
    public void update(LevelManager lvl, Random r) {
        lifeTime--;
        if (lifeTime == 0) destroy();
    }

    @Override
    public void draw(SpriteManager spr) {
        // me when bruh

        if (lifeTime>0)
            spr.drawGame("sword" + (int) Math.ceil((13 - lifeTime) >> 1),x,y,1);

    }

    @Override
    public void onCollide(Entity other) {
        if (other.getTeam() == Team.Enemies && hurts){
            other.takeDamage(1);
            hurts = false;
        }
    }

    @Override
    public Entity getCopy(float x, float y) {
        return new SwordSwipe(x,y,64,64,1);
    }

    @Override
    public void onDestroy() {

    }
}

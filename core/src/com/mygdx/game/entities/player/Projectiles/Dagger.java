package com.mygdx.game.entities.player.Projectiles;

import com.mygdx.game.logic.entities.Entity;
import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.level.LevelManager;
import com.mygdx.game.logic.sprites.SpriteManager;

import java.util.Random;

public class Dagger extends Entity {
    private float yM = 2;

    public Dagger(float x, float y, float xSize, float ySize, int hp) {
        super(x, y, xSize, ySize, hp, Team.PlayerProjectiles);
    }

    @Override
    public void update(LevelManager lvl, Random r) {
        float speed = 12f;
        float fallSpeed = 0.5f;
        float lvlY = lvl.getOnPos(x + (lvl.getTileScale() - 1)).getY() + lvl.getTileScale();

        x += speed;
        y += yM;

        yM -= fallSpeed;
        //delete if out of bounds
        if (x >= (lvl.getMapWidth()-1) * lvl.getTileScale()) destroy();
        else{

            //detect ground collision
            if (lvlY - lvl.getTileScale() > y - ySize) destroy();
        }

    }

    @Override
    public void draw(SpriteManager spr) {
        spr.drawGame("dagger1",x,y,1);
    }

    @Override
    public void onCollide(Entity other) {
        if (other.getTeam() != Team.Player){
            destroy();
            other.takeDamage(1);
        }
    }

    @Override
    public Entity getCopy(float x, float y) {
        return new Dagger(x,y,xSize,ySize,hp);
    }

    @Override
    public void onDestroy() {

    }
}

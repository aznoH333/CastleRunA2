package com.mygdx.game.entities.player.Projectiles;

import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.entities.Entity;
import com.mygdx.game.logic.level.LevelManager;
import com.mygdx.game.logic.sprites.SpriteManager;

import java.util.Random;

public class Axe extends Entity {

    private int pierceTimer = 0;
    private static final int pierceTimerMax = 16;

    public Axe(float x, float y, float xSize, float ySize, int hp) {
        super(x, y, xSize, ySize, hp, Team.PlayerProjectiles);
    }

    @Override
    public void update(LevelManager lvl, Random r) {
        pierceTimer--;
        // TODO : this
    }

    @Override
    public void draw(SpriteManager spr) {

    }

    @Override
    public void onCollide(Entity other) {
        if (other.getTeam() == Team.Enemies && pierceTimer == 0){
            other.takeDamage(1);
            pierceTimer = pierceTimerMax;
        }

    }

    @Override
    public Entity getCopy(float x, float y) {
        return new Axe(x, y, xSize, ySize, hp);
    }

    @Override
    public void onDestroy() {

    }
}

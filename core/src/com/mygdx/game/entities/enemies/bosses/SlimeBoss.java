package com.mygdx.game.entities.enemies.bosses;

import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.entities.Entity;
import com.mygdx.game.logic.level.LevelManager;
import com.mygdx.game.logic.sprites.SpriteManager;
import com.mygdx.game.logic.stage.StageManager;

import java.util.Random;

public class SlimeBoss extends Entity {

    public SlimeBoss(float x, float y, float xSize, float ySize, int hp) {
        super(x, y, xSize, ySize, hp, Team.Enemies);
    }

    @Override
    public void update(LevelManager lvl, Random r) {

    }

    @Override
    public void draw(SpriteManager spr) {
        spr.drawGame("player0",x,y,1);
        spr.drawGame("player0",x,y+64,1);
    }

    @Override
    public void onCollide(Entity other) {
        // TODO : this
        // position on screen related to boss hp
        // hp-- = x--
        // spawns red slimes (or some slime minion?)
    }

    @Override
    public Entity getCopy(float x, float y) {
        return new SlimeBoss(x,y+LevelManager.tileScale,xSize,ySize,hp);
    }

    @Override
    public void onDestroy() {
        StageManager.getINSTANCE().advanceInStage();
        // TODO : some sort of exit level after few seconds function
    }
}

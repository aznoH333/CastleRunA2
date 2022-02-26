package com.mygdx.game.entities.environment;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.SoundManager;
import com.mygdx.game.logic.entities.Entity;
import com.mygdx.game.logic.level.LevelManager;
import com.mygdx.game.logic.sprites.SpriteManager;
import com.mygdx.game.logic.stage.StageManager;

import java.util.Random;

public class ExitDoor extends Entity {

    private boolean isOpen = false;

    public ExitDoor(float x, float y, float xSize, float ySize, int hp) {
        super(x, y, xSize, ySize, hp, Team.Environment);
    }

    @Override
    public void update(LevelManager lvl, Random r) {

    }

    @Override
    public void draw(SpriteManager spr) {
        if (isOpen)     spr.drawGame("door1",x,y,1);
        else            spr.drawGame("door0",x,y,1);
    }

    @Override
    public void onCollide(Entity other) {
        if (other.getTeam() == Team.PlayerProjectiles && !isOpen){
            // TODO : door opening sound
            // TODO : player hurt sound
            // TODO : game over sound
            SoundManager.getINSTANCE().playSound("chest");
            isOpen = true;
        }

        if (other.getTeam() == Team.Player && isOpen){
            // TODO : some kind of an exit level animation
            Game.exitLevel();
        }
    }

    @Override
    public Entity getCopy(float x, float y) {
        return new ExitDoor(x,y + LevelManager.tileScale,xSize,ySize,hp);
    }

    @Override
    public void onDestroy() {

    }
}

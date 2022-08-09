package com.mygdx.game.entities.enemies.bosses.mechboss;

import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.level.LevelManager;

import java.util.Random;

public class MechBossSpawner extends Entity {
    private int timer = 90;
    private boolean mech = false;
    private float yM = 0;
    private final static float mechOffset = -48;
    public MechBossSpawner(float x, float y) {
        super(x, y, 64, 64, 1, Team.Environment);
    }

    @Override
    public void update(LevelManager lvl, Random r) {
        timer--;
        if (timer == 0) { yM =  5;}
        if(timer < 0) {
            y += yM;
            yM -= 0.5f;
            if (y < 20){
                yM = 25;
                mech = true;
            }
            if (mech && yM < 0 && lvl.collidesWithLevel(this)){
                destroy();
            }
        }

    }

    @Override
    public void draw(DrawingManager spr) {
        if (mech){
            spr.draw("mech3", x + mechOffset, y, -1);
            spr.draw("mech1", x + mechOffset ,y , -1);
            spr.draw("mech4", x + mechOffset, y, -1);
            spr.draw("mech2", x + mechOffset, y, -1);
            spr.draw("mech0", x + mechOffset, y, -1);
        }
        else if (timer > 0)
            spr.draw("goblin0", x, y, 0);
        else{
            spr.draw("goblin1", x, y, -1);
        }
    }

    @Override
    public void onCollide(Entity other) {

    }

    @Override
    public Entity getCopy(float x, float y) {
        return new MechBossSpawner(x, y + 64);
    }

    @Override
    public void onDestroy() {
        // TODO : sound
        // shitty hack to make the boss entity appear on the same frame the spawner dies
        DrawingManager spr = DrawingManager.getINSTANCE();
        spr.draw("mech3", x + mechOffset, y, -1);
        spr.draw("mech1", x + mechOffset ,y , -1);
        spr.draw("mech4", x + mechOffset, y, -1);
        spr.draw("mech2", x + mechOffset, y, -1);
        spr.draw("mech0", x + mechOffset, y, -1);


        e.spawnEntity("mech boss actual", x + mechOffset, y);
    }
}

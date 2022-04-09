package com.mygdx.game.entities.enemies.bosses.slimeboss;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.SoundManager;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.entities.ParticleManager;
import com.mygdx.game.logic.level.LevelManager;
import com.mygdx.game.logic.drawing.DrawingManager;

import java.util.Random;

public class SlimeBossDeathAnimation extends Entity {

    private float yM = 0;
    private final static float gravity = 0.5f;
    private final Random random = new Random();
    private final ParticleManager part;
    private final SoundManager sound;
    private int animationSpeed = 10;

    public SlimeBossDeathAnimation(float x, float y, float xSize, float ySize, int hp) {
        super(x, y, xSize, ySize, hp, Team.Neutral);
        part = ParticleManager.getINSTANCE();
        sound = SoundManager.getINSTANCE();
    }

    @Override
    public void update(LevelManager lvl, Random r) {
        float lvlY = lvl.getLevelY(this);

        //land
        boolean landed = y <= lvlY - yM && yM <= 0;
        if (landed){
            yM = 0;
            y = lvlY;
        }else{
            yM -= gravity;
        }

        //spawn gore particle
        if (Game.Time() % 2 == 0){
            part.addParticle("fleshGore" + random.nextInt(3),x + random.nextInt((int) xSize),y + random.nextInt((int) ySize) - 32,random.nextInt(10)-5,5-random.nextInt(10),0.5f,random.nextInt(10) + 10);
        }

        if (Game.Time() % animationSpeed == 0){
            part.addParticle("slimeBossDeath",x + random.nextInt((int) xSize),y + random.nextInt((int) ySize) - 64,0,0,0);
            sound.playSound("weapon1");
        }
        if (Game.Time() % 32 == 0){
            animationSpeed--;
            if (animationSpeed == 2) destroy();
        }
    }

    @Override
    public void draw(DrawingManager spr) {
        if (Game.Time()%4 > 2){
            spr.drawGame("slime_boss0",x,y,1);
        } else{
            spr.drawGame("slime_boss1",x,y,1);
        }
    }

    @Override
    public void onCollide(Entity other) {

    }

    @Override
    public Entity getCopy(float x, float y) {
        return new SlimeBossDeathAnimation(x,y,xSize,ySize,hp);
    }

    @Override
    public void onDestroy() {
        for (int i = 0; i < random.nextInt(30)+30; i++) {
            part.addParticle("fleshGore" + random.nextInt(3),x + random.nextInt((int) xSize),y + random.nextInt((int) ySize) - 32,random.nextInt(10)-5,5-random.nextInt(10),0.5f,random.nextInt(10) + 10);
        }

    }
}

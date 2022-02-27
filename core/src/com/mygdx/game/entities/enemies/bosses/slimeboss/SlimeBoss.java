package com.mygdx.game.entities.enemies.bosses.slimeboss;

import com.mygdx.game.Game;
import com.mygdx.game.entities.enemies.Slime;
import com.mygdx.game.logic.SoundManager;
import com.mygdx.game.logic.entities.Entity;
import com.mygdx.game.logic.entities.ParticleManager;
import com.mygdx.game.logic.level.LevelManager;
import com.mygdx.game.logic.sprites.SpriteManager;

import java.util.Random;

public class SlimeBoss extends Slime {

    protected final static int animationSpeed = 64;
    protected final static float gravity = 0.5f;
    protected final static float hopStrength = 8.5f;
    protected final static int jumpTime = 120;
    protected final static float moveSpeed = 4f;
    private final static int slimeTimerMax = 240;
    private final static int slimeTimerAnimation = 60;
    private int slimeTimer = slimeTimerMax;
    private final Random random = new Random();
    private final int hpMax;
    private float targetX;

    public SlimeBoss(float x, float y, float xSize, float ySize, int hp) {
        super(x, y, xSize, ySize, hp);
        hpMax = hp;
        targetX = x;
    }

    @Override
    public void update(LevelManager lvl, Random r) {
        if (landed) jumpTimer++;
        float lvlY = lvl.getOnPos(x + (lvl.getTileScale() - 1)).getY() + lvl.getTileScale();

        //land
        landed = y <= lvlY - yM && yM <= 0;
        if (landed){
            yM = 0;
            y = lvlY;
            slimeTimer--;
        }else{
            yM -= gravity;
        }
        //hop
        if (jumpTimer > jumpTime && slimeTimer > slimeTimerAnimation){
            jumpTimer = 0;
            yM = hopStrength;
            if (moveTo != targetX)  moveTo = targetX;
            else                    moveTo = Math.max(targetX - (lvl.getTileScale()<<1),x - (lvl.getTileScale()<<1));
            direction = !direction;
            SoundManager.getINSTANCE().playSound("slimeJump");
        }


        //update positions
        if (moveTo > x) x += moveSpeed;
        if (moveTo < x) x -= moveSpeed;
        targetX = (float) (Math.ceil(((float)hp/(float)hpMax) * (LevelManager.mapWidth - 5))+1) * LevelManager.tileScale;
        y += yM;

        // spawn slimes
        if (slimeTimer < 1 && landed){
            slimeTimer = slimeTimerMax;
            ParticleManager part = ParticleManager.getINSTANCE();
            SoundManager.getINSTANCE().playSound("enemyDeath1");
            e.spawnEntity("red slime", x-64,752);

            // spawn 5 - 10 gore particles
            for (int i = 0; i < random.nextInt(5) + 5; i++)
                part.addParticle("fleshGore" + random.nextInt(3),x-64,752,random.nextInt(10)-5,-random.nextInt(5),0.5f,random.nextInt(10) + 10);

        }
    }

    @Override
    public void draw(SpriteManager spr) {
        if (landed)
            if (jumpTimer%animationSpeed > animationSpeed>>1 && slimeTimer > slimeTimerAnimation || slimeTimer <= slimeTimerAnimation && Game.Time()%4 > 2){
                spr.drawGame("slime_boss0",x,y,1);
            } else{
                spr.drawGame("slime_boss1",x,y,1);
            }

        else{
            spr.drawGame("slime_boss2",x,y,1);
        }
        // TODO : boss sprites
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
        e.spawnEntity("slime boss death",x,y);
        Game.exitLevel(600);
        // TODO : some sort of exit level after few seconds function
    }
}

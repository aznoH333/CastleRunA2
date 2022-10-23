package com.mygdx.game.data.tilesets.traps;

import com.mygdx.game.Game;
import com.mygdx.game.logic.level.tileCollums.IOnPlayerStep;
import com.mygdx.game.logic.level.tileCollums.ISpecialTile;
import com.mygdx.game.logic.level.tileCollums.TileCollum;
import com.mygdx.game.data.enums.TileCollumSpecial;
import com.mygdx.game.logic.entities.ParticleManager;
import com.mygdx.game.logic.drawing.DrawingManager;

import java.util.Random;

public class BreakingPlatform extends TileCollum implements ISpecialTile, IOnPlayerStep {

    private static final String[] sprites = {};
    private static final String repeated = null;
    private static final TileCollumSpecial special = TileCollumSpecial.DisappearingPlatform;
    private static final boolean grace = false;
    private boolean isOn = true;
    private boolean isInAStateOfCollapse = false;
    private int timer = 0;
    private final static int respawnTime = 120;
    private final static int fallTime = 50;
    private int flickerTimer = 0;
    private static ParticleManager part = ParticleManager.getINSTANCE();
    private Random r = new Random();
    public BreakingPlatform() {
        super(sprites, repeated, grace, special);
    }
    public BreakingPlatform(int y) {
        super(y, sprites, repeated, grace, special);
    }

    @Override
    public TileCollum getNew(int y) {
        return new BreakingPlatform(y);
    }

    @Override
    public void update() {
        if (isInAStateOfCollapse || !isOn){
            timer--;
            if (isInAStateOfCollapse && timer == 0){
                isOn = false;
                isInAStateOfCollapse = false;
                timer = respawnTime;
            }else if (!isOn && timer == 0){
                isOn = true;
                isInAStateOfCollapse = false;
                flickerTimer = 20;
            }
        }
        if (flickerTimer > 0) flickerTimer--;
    }

    @Override
    public void draw(float x, float y) {
        if(isOn && flickerTimer % 4 < 2){
            if (!isInAStateOfCollapse)
                DrawingManager.getINSTANCE().drawGame("platform0",x,y,1);
            else{

                if (Game.Time() % 4 == 0){
                    part.addParticle("smoke", x + r.nextInt(48), y + 32 + r.nextInt(16),0,-r.nextFloat(),0.2f);
                }

                if (timer < 10)     DrawingManager.getINSTANCE().drawGame("platform5",x,y,1);
                else if (timer < 20) DrawingManager.getINSTANCE().drawGame("platform4",x,y,1);
                else if (timer < 30) DrawingManager.getINSTANCE().drawGame("platform3",x,y,1);
                else if (timer < 40) DrawingManager.getINSTANCE().drawGame("platform2",x,y,1);
                else if (timer < 50) DrawingManager.getINSTANCE().drawGame("platform1",x,y,1);
            }
        }
    }


    @Override
    public void onPlayerStep(){
        if (!isInAStateOfCollapse){
            isInAStateOfCollapse = true;
            timer = fallTime;
        }

    }

    @Override
    public int getY(){
        if (isOn)
            return super.getY();
        return -999;
    }
}

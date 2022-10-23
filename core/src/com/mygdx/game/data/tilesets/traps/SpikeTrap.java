package com.mygdx.game.data.tilesets.traps;

import com.mygdx.game.logic.level.TileActivator;
import com.mygdx.game.logic.level.tileCollums.ICollumnActivatavle;
import com.mygdx.game.logic.level.tileCollums.ISpecialTile;
import com.mygdx.game.logic.level.tileCollums.TileCollum;
import com.mygdx.game.data.enums.TileCollumSpecial;
import com.mygdx.game.logic.SoundManager;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.level.LevelManager;

public class SpikeTrap extends TileCollum implements ISpecialTile, ICollumnActivatavle {

    private static final String[] sprites = {"spikeTrap1", "spikeTrap2"};
    private static final String repeated = "spikeTrap3";
    private static final TileCollumSpecial special = TileCollumSpecial.SpikeTrap;
    private int timer = 0;
    private static final boolean grace = false;
    private static final int activeTime = 60;
    

    public SpikeTrap(){
        super(sprites, repeated, grace, special);
    }
    public SpikeTrap(int y){
        super(y,sprites, repeated, grace, special);
    }

    @Override
    public TileCollum getNew(int y) {
        return new SpikeTrap(y);
    }

    @Override
    public void update() {
        if (hurts){
            timer--;
            if (timer == 0){
                hurts = false;
                SoundManager.getINSTANCE().playSound("spike2");

            }
        }
    }
    @Override
    public void draw(float x, float y) {
        if (hurts) DrawingManager.getINSTANCE().drawGame("spikeTrap0",x, y + LevelManager.tileScale,2);
    }



    @Override
    public void activate(TileActivator activator) {
        if (!hurts){
            hurts = true;
            timer = activeTime;
            SoundManager.getINSTANCE().playSound("spike1");

        }
    }
}

package com.mygdx.game.data.tilesets.traps;

import com.mygdx.game.data.TileCollum;
import com.mygdx.game.data.enums.TileCollumSpecial;
import com.mygdx.game.logic.SoundManager;
import com.mygdx.game.logic.sprites.SpriteManager;
import com.mygdx.game.logic.level.LevelManager;

public class SpikeTrap extends TileCollum {

    private static final String[] sprites = {"spikeTrap0", "spikeTrap1"};
    private static final String repeated = "spikeTrap2";
    private static final TileCollumSpecial special = TileCollumSpecial.SpikeTrap;
    private static final boolean grace = false;
    private int timer = 0;
    private static final int timerMax = 120;
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
        if (timer <= 0){
            if (hurts){
                timer = timerMax;
                SoundManager.getINSTANCE().playSound("spike1");
            }else {
                timer = activeTime;
                SoundManager.getINSTANCE().playSound("spike2");
            }
            hurts = !hurts;
        }else timer--;
    }
    // FIXME: calculated offsets
    @Override
    public void draw(float x, float y) {
        if (hurts) SpriteManager.getINSTANCE().draw("spikeTrap3",x, y + LevelManager.tileScale,2);
    }
}
package com.mygdx.game.data.tilesets.traps;

import com.mygdx.game.Game;
import com.mygdx.game.data.TileCollum;
import com.mygdx.game.data.enums.TileCollumSpecial;
import com.mygdx.game.logic.drawing.DrawingManager;

public class GhostPlatform extends TileCollum {

    private static final String[] sprites = {};
    private static final String repeated = null;
    private static final TileCollumSpecial special = TileCollumSpecial.DisappearingPlatform;
    private static final boolean grace = true;
    private boolean isOn = true;
    private int timer = onTime;
    private final static int offTime = 120;
    private final static int onTime = 240;

    public GhostPlatform(){
        super(sprites, repeated, grace, special);
    }
    public GhostPlatform(int y){
        super(y,sprites, repeated, grace, special);
    }

    @Override
    public TileCollum getNew(int y) {
        return new GhostPlatform(y);
    }

    @Override
    public void update() {
        timer--;

        if (timer == 0){
            if (isOn)   timer = offTime;
            else        timer = onTime;
            isOn = !isOn;
        }
    }

    @Override
    public void draw(float x, float y) {
        if (isOn && timer >= 60 && timer <= 180 || isOn && Game.Time() % 4 >> 1 == 0)
            DrawingManager.getINSTANCE().drawGame("ghost_platform0",x,y);
    }

    @Override
    public int getY(){
        if (isOn)
            return super.getY();
        return -999;
    }
    // FIXME : player teleports to the platform if he is bellow it and it respawns
}

package com.mygdx.game.data.tilesets.traps;

import com.mygdx.game.data.TileCollum;
import com.mygdx.game.data.enums.TileCollumSpecial;
import com.mygdx.game.logic.sprites.SpriteManager;

public class BreakingPlatform extends TileCollum {

    private static final String[] sprites = {};
    private static final String repeated = null;
    private static final TileCollumSpecial special = TileCollumSpecial.DisappearingPlatform;
    private static final boolean grace = false;
    private boolean isOn = true;
    private boolean isInAStateOfCollapse = false;
    private int timer = 0;
    private final static int respawnTime = 120;
    private final static int fallTime = 30;
    private int flickerTimer = 0;
    // FIXME : when a platform breaks under and object like chest | skeleton they float in the air
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
        // TODO : add some collapse particles here

        if(isOn && flickerTimer % 4 < 2){
            if (!isInAStateOfCollapse)
                SpriteManager.getINSTANCE().drawGame("platform0",x,y,1);
            else{
                if (timer < 6)     SpriteManager.getINSTANCE().drawGame("platform5",x,y,1);
                else if (timer < 12)SpriteManager.getINSTANCE().drawGame("platform4",x,y,1);
                else if (timer < 18)SpriteManager.getINSTANCE().drawGame("platform3",x,y,1);
                else if (timer < 24)SpriteManager.getINSTANCE().drawGame("platform2",x,y,1);
                else if (timer < 30)SpriteManager.getINSTANCE().drawGame("platform1",x,y,1);
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

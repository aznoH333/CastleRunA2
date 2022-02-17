package com.mygdx.game.logic.level;

import com.mygdx.game.data.Background;
import com.mygdx.game.data.TileCollum;

public class BossLevel extends Level{
    public BossLevel(LevelBuilder builder){
        super(builder);
    }


    //height
    @Override
    public int maxHeight(){return defaultH;}
    @Override
    public int minHeight(){return defaultH;}

    //height change
    @Override
    public float changeChance(){return 0;}
    @Override
    public int changeLengthMax(){return 0;}
    @Override
    public int changeLengthMin(){return 0;}

    @Override
    public boolean isBossLevel(){
        return true;
    }
}

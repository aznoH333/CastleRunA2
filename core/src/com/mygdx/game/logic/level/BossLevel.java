package com.mygdx.game.logic.level;


public class BossLevel extends Level{

    private final String boss;
    public BossLevel(LevelBuilder builder, String boss){
        super(builder);
        this.boss = boss;
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
    public boolean isBossLevel(){
        return true;
    }

    @Override
    public String getBoss(){ return boss; }


}

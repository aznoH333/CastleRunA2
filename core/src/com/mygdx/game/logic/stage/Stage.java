package com.mygdx.game.logic.stage;

public class Stage {
    private final String[] levels;
    private byte currentStageIndex = 0;

    public Stage(String[] levels){
        this.levels = levels;
    }

    public void advanceInStage(){
        currentStageIndex++;
    }

    public String getCurrentLevel(){
        return levels[currentStageIndex];
    }
}

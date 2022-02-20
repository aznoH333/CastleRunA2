package com.mygdx.game.logic.stage;

public class Stage {
    private final String[] levels;
    private byte currentStageIndex = 0;
    private final String background;
    private final Decorator[] decorators;
    private final int additionalLevelOffset;

    public Stage(String[] levels, String background, Decorator[] decorators, int additionalLevelOffset){
        this.levels = levels;
        this.background = background;
        this.decorators = decorators;
        this.additionalLevelOffset = additionalLevelOffset;
    }

    public void advanceInStage(){
        currentStageIndex++;
    }

    public String getCurrentLevel(){
        return levels[currentStageIndex];
    }

    public byte getCurrentStageIndex(){
        return currentStageIndex;
    }

    public String[] getLevels(){
        return levels;
    }

    public String getBackground(){
        return background;
    }

    public Decorator[] getDecorators(){
        return decorators;
    }

    public int getAdditionalLevelOffset() {
        return additionalLevelOffset;
    }
}

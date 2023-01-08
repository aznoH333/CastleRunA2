package com.mygdx.game.logic.level;

public class EntityDistributionObject {
    private final int earliestAppearance;
    private final int latestAppearance;
    private final String entity;

    public EntityDistributionObject(String entity, int earliestAppearance, int latestAppearance) {
        this.earliestAppearance = earliestAppearance;
        this.latestAppearance = latestAppearance;
        this.entity = entity;
    }

    public boolean canEnemyAppear(int level){
        return level >= earliestAppearance && level <= latestAppearance;
    }

    public String getEntity() {
        return entity;
    }
}

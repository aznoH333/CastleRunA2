package com.mygdx.game.logic.level;

public class EntityDistributionObject {
    private final int earliestAppearance;
    private final int latestAppearance;
    private final float distributionModifier;
    private final String entity;

    public EntityDistributionObject(String entity, int earliestAppearance, int latestAppearance, float distributionModifier) {
        this.earliestAppearance = earliestAppearance;
        this.latestAppearance = latestAppearance;
        this.distributionModifier = distributionModifier;
        this.entity = entity;
    }

    public float getDistributionForLevel(int level){
        return Math.max(Math.min(level-earliestAppearance+1, latestAppearance-level+1) *
                (distributionModifier / ((float) (latestAppearance - earliestAppearance)/2)), 0);
    }

    public String getEntity() {
        return entity;
    }
}

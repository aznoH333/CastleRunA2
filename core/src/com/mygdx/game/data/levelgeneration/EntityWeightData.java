package com.mygdx.game.data.levelgeneration;

public class EntityWeightData {
    private final float weight;
    private final String entity;

    public EntityWeightData(float weight, String entity) {
        this.weight = weight;
        this.entity = entity;
    }

    public float getWeight() {
        return weight;
    }

    public String getEntity() {
        return entity;
    }
}

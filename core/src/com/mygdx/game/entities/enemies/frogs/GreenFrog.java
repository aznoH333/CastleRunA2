package com.mygdx.game.entities.enemies.frogs;

import com.mygdx.game.logic.entities.abstracts.Entity;

public class GreenFrog extends Frog{
    public GreenFrog(float x, float y) {
        super(x, y, "frog_green", 1);
    }

    @Override
    public Entity getCopy(float x, float y) {
        return new GreenFrog(x, y);
    }
}

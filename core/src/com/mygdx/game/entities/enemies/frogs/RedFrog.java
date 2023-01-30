package com.mygdx.game.entities.enemies.frogs;

import com.mygdx.game.logic.entities.abstracts.Entity;

public class RedFrog extends Frog{
    public RedFrog(float x, float y) {
        super(x, y, "frog_red", 2);
    }

    @Override
    public Entity getCopy(float x, float y) {
        return new RedFrog(x, y);
    }
}

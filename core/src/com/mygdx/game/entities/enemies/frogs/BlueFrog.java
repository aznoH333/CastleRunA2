package com.mygdx.game.entities.enemies.frogs;

import com.mygdx.game.logic.entities.abstracts.Entity;

public class BlueFrog extends Frog{
    public BlueFrog(float x, float y) {
        super(x, y, "frog_blue", 3);
    }

    @Override
    public Entity getCopy(float x, float y) {
        return new BlueFrog(x, y);
    }
}

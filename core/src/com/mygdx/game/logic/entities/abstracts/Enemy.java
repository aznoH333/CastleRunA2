package com.mygdx.game.logic.entities.abstracts;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.EntityTags;
import com.mygdx.game.data.enums.Team;
import com.mygdx.game.items.interfaces.IStatusEffect;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Enemy extends Entity {

    public Enemy(float x, float y, float xSize, float ySize, int hp, Team team) {
        super(x, y, xSize, ySize, hp, team);
    }

    public void updateEffects(){
        for (IStatusEffect effect: effects.values()) {
            effect.update(this);
        }

        // je hodne dumb dumb a snazi se pouzit neco na co nema pravo
        //noinspection Java8CollectionRemoveIf
        for (IStatusEffect effect: effects.values()) {
            if (effect.getDuration() < Game.Time()){
                effects.remove(effect.getName());
            }
        }
    }

    private final HashMap<String, IStatusEffect> effects = new HashMap<>();
    protected EntityTags[] tags = new EntityTags[]{};

    public void applyEffect(IStatusEffect effect){
        if (effect.canBeApplied(tags) && !effects.containsKey(effect.getName()))
            effects.put(effect.getName(), effect);
    }

    public EntityTags[] getTags(){
        return tags;
    }

}

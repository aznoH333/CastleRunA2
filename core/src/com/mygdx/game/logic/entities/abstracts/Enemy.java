package com.mygdx.game.logic.entities.abstracts;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.EntityTags;
import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.entities.effects.IStatusEffect;

import java.util.ArrayList;

public abstract class Enemy extends Entity {

    public Enemy(float x, float y, float xSize, float ySize, int hp, Team team) {
        super(x, y, xSize, ySize, hp, team);
    }

    public void updateEffects(){
        for (IStatusEffect effect: effects) {
            effect.update(this);
        }

        for (IStatusEffect effect: effects) {
            if (effect.getDuration() < Game.Time()){
                effects.remove(effect);
            }
        }
    }

    private final ArrayList<IStatusEffect> effects = new ArrayList<>();
    protected EntityTags[] tags = new EntityTags[]{};

    public void applyEffect(IStatusEffect effect){
        if (effect.canBeApplied(tags))
            effects.add(effect);
    }

    public EntityTags[] getTags(){
        return tags;
    }

}

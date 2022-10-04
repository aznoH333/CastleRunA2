package com.mygdx.game.logic.entities.abstracts;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.EntityTags;
import com.mygdx.game.data.enums.Team;
import com.mygdx.game.items.interfaces.IStatusEffect;
import com.mygdx.game.logic.entities.ParticleManager;
import com.mygdx.game.logic.player.ProgressManager;

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

    @Override
    public void takeDamage(int damage){
        if (ProgressManager.getINSTANCE().getBonus("critChance") > Game.getGeneralRandom().nextFloat()){
            ParticleManager.getINSTANCE().addParticle("crit",x + xSize/2 - 32,y + ySize/2, 0, 0.75f,0);
            hp -= damage;
        }
        hp -= damage;
        if (hp > 0) onTakeDamage();
    }


    protected void onTakeDamage(){}

    abstract public int getXpOnKill();

}

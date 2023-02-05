package com.mygdx.game.items.effects;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.EntityTags;
import com.mygdx.game.items.interfaces.IStatusEffect;
import com.mygdx.game.logic.entities.ParticleManager;
import com.mygdx.game.logic.entities.abstracts.Entity;

import java.util.Random;

public class OnFire implements IStatusEffect {

    private final static ParticleManager part = ParticleManager.getINSTANCE();
    private final static Random r = Game.getGeneralRandom();
    private final long duration;
    public OnFire(int duration){
        this.duration = Game.Time() + duration;
    }

    @Override
    public void update(Entity affectedEntity) {
        if ((duration - Game.Time()) % 30 == 0){
            affectedEntity.takeDamage(1);
        }
        // spawn particle
        if (Game.Time() % 4 == 0){
            part.addParticle("fire",affectedEntity.getX() + r.nextInt((int) affectedEntity.getXSize())-8, affectedEntity.getY() + r.nextInt((int) affectedEntity.getYSize())-8,0,0,-0.05f,r.nextInt(30)+30);
        }

    }

    @Override
    public void onDestroy() { }

    @Override
    public long getDuration() {
        return duration;
    }

    @Override
    public boolean canBeApplied(EntityTags[] tags) {
        for (EntityTags tag: tags) {
            if (tag == EntityTags.ImmuneToFire) return false;
        }
        return true;
    }

    @Override
    public String getName() {
        return "On fire";
    }
}

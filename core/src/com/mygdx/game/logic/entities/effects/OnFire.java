package com.mygdx.game.logic.entities.effects;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.EntityTags;
import com.mygdx.game.logic.entities.abstracts.Entity;

public class OnFire implements IStatusEffect{

    private final long duration;
    public OnFire(int duration){
        this.duration = Game.Time() + duration;
    }

    @Override
    public void update(Entity affectedEntity) {
        if (Game.Time() % 20 == 0) affectedEntity.takeDamage(1);
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
}

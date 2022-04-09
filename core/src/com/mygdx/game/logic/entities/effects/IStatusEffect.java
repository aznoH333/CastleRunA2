package com.mygdx.game.logic.entities.effects;

import com.mygdx.game.data.enums.EntityTags;
import com.mygdx.game.logic.entities.abstracts.Entity;

public interface IStatusEffect {
    void update(Entity affectedEntity);
    void onDestroy();
    long getDuration();
    boolean canBeApplied(EntityTags[] tags);
}

package com.mygdx.game.items.interfaces;

import com.mygdx.game.data.enums.EntityTags;
import com.mygdx.game.logic.entities.abstracts.Entity;

public interface IStatusEffect {
    void update(Entity affectedEntity);
    void onDestroy();
    long getDuration();
    boolean canBeApplied(EntityTags[] tags);
    String getName();
}

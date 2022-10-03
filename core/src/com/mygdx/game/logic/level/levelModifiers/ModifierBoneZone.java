package com.mygdx.game.logic.level.levelModifiers;

import com.mygdx.game.Game;
import com.mygdx.game.logic.entities.EntityManager;
import com.mygdx.game.logic.level.LevelManager;
import com.mygdx.game.logic.level.tileCollums.TileCollum;

import java.util.Random;

public class ModifierBoneZone implements ILevelModifier{
    @Override
    public String getIntroMessage() {
        return "You have entered the bone zone!";
    }

    @Override
    public void levelModifierTick() {

    }

    @Override
    public void onLevelStart() {

    }
    private static final EntityManager ent = EntityManager.getINSTANCE();
    private static final Random r = Game.getGeneralRandom();
    @Override
    public void onTileGenerate(TileCollum collum, float x) {
        if (!collum.grace() && r.nextFloat() < 0.15f)
            ent.spawnEntity("skeleton", x, collum.getY());
    }
}

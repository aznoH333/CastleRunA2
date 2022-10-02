package com.mygdx.game.logic.level.levelModifiers;

import com.mygdx.game.Game;
import com.mygdx.game.logic.entities.EntityManager;

import java.util.Random;

public class ModifierUnstable implements ILevelModifier {

    private final static Random r = Game.getGeneralRandom();
    private final static EntityManager e = EntityManager.getINSTANCE();
    @Override
    public String getIntroMessage() {
        return "Rocks are falling from the sky!";
    }

    @Override
    public void levelModifierTick() {
        // test
        if (r.nextFloat() < 0.5f){
            e.spawnEntity("slime", r.nextInt((int)Game.gameWorldWidth >> 6) << 6, 1279);
        }
    }

    @Override
    public void onLevelStart() {

    }

    @Override
    public void onTileGenerate() {

    }
}

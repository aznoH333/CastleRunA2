package com.mygdx.game.logic.level.levelModifiers;

import com.mygdx.game.Game;
import com.mygdx.game.logic.entities.EntityManager;
import com.mygdx.game.logic.level.LevelManager;

import java.util.Random;

public class ModifierSlimeRain implements ILevelModifier{

    private final static Random r = Game.getGeneralRandom();
    private final static EntityManager e = EntityManager.getINSTANCE();
    private final static LevelManager lvl = LevelManager.getINSTANCE();

    @Override
    public String getIntroMessage() {
        return "Slime is falling from the sky!";
    }

    @Override
    public void levelModifierTick() {
        if (r.nextFloat() < 0.15f){
            String slime;
            if (r.nextFloat() < 0.35f) slime = "red slime";
            else if (r.nextFloat() < 0.15f) slime = "purple slime";
            else slime = "slime";
            e.spawnEntity(slime, lvl.getAlignedX(r.nextInt(lvl.getMapWidth())*64), 1279);
        }
    }

    @Override
    public void onLevelStart() {

    }

    @Override
    public void onTileGenerate() {

    }
}

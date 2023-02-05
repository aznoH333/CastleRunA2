package com.mygdx.game.logic.ProgressPersistence;

import com.mygdx.game.data.PlayerClass;

import java.io.Serializable;
import java.util.HashMap;

public class PersistenceData implements Serializable {
    private final int currentXp;
    private final int lvl;

    private final HashMap<String, Integer> bonuses;
    private final HashMap<PlayerClass, Boolean> classes;


    public PersistenceData(int currentXp, int lvl, HashMap<String, Integer> bonuses, HashMap<PlayerClass, Boolean> classes){
        this.currentXp = currentXp;
        this.lvl = lvl;
        this.bonuses = bonuses;
        this.classes = classes;
    }

    public int getCurrentXp() {
        return currentXp;
    }

    public int getLvl() {
        return lvl;
    }

    public HashMap<String, Integer> getBonuses() {
        return bonuses;
    }

    public HashMap<PlayerClass, Boolean> getClasses() {
        return classes;
    }

    // TODO : load and save progress
    // TODO : save current progress
}

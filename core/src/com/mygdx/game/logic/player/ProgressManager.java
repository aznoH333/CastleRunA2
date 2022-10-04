package com.mygdx.game.logic.player;

import com.mygdx.game.logic.entities.ParticleManager;
import com.mygdx.game.ui.UIManager;

import java.util.HashMap;

public class ProgressManager {
    private static ProgressManager INSTANCE;

    public static ProgressManager getINSTANCE(){
        if (INSTANCE == null) INSTANCE = new ProgressManager();
        return INSTANCE;
    }

    private final HashMap<String, Integer> bonuses = new HashMap<>();
    private final HashMap<String, Boolean> classes = new HashMap<>();
    private int totalMasteryPoints = 0;
    private int availableMasteryPoints = 0;
    private final int maxPoints = 40;
    private final int maxPointsPerCategory = 10;
    private int nextLevelXpRequirement = 150;
    private int currentXp = 0;
    private int level = 0;

    private ProgressManager(){
        // TODO : load and save progress
        // TODO : save current progress


        bonuses.put("health", 0);
        bonuses.put("energy", 0);
        bonuses.put("coinBonus", 0);
        bonuses.put("critChance", 0);


        classes.put("knight", true);
        classes.put("mage", false);
        classes.put("midas", false);
        classes.put("summoner", false);
        classes.put("haunted knight", false);
    }

    public float getBonus(String bonusName){
        float multiplier;
        switch(bonusName){
            default: multiplier = 1; break;
            case "health": multiplier = 0.5f; break;
            case "energy": multiplier = 0.75f; break;
            case "coinBonus": multiplier = 0.02f; break;
            case "critChance": multiplier = 0.03f; break;
        }
        return (int) (bonuses.get(bonusName) * multiplier);
    }

    public void levelUp(){
        if (totalMasteryPoints < maxPoints){
            level++;
            totalMasteryPoints++;
            availableMasteryPoints++;
            currentXp -= nextLevelXpRequirement;
            nextLevelXpRequirement = (int) (150 + level * 30 + Math.pow(level,3));
            System.out.println(level);
            UIManager.getINSTANCE().displayMessage("Level UP!");
        }
    }

    public void spendPoint(String category){
        if (availableMasteryPoints > 0 && bonuses.get(category) < maxPointsPerCategory){
            availableMasteryPoints--;
            bonuses.put(category,bonuses.get(category)+1);
        }
    }

    public void refundPoint(String category){
        if (bonuses.get(category) > 0){
            availableMasteryPoints++;
            bonuses.put(category,bonuses.get(category)-1);
        }
    }

    public void gainXp(int xpCount){
        currentXp += xpCount;
        if (currentXp > nextLevelXpRequirement){
            levelUp();
        }
    }

    public void unlockClass(String className){
        if (level >= 10){
            level -= 10;
            classes.put(className, true);
        }
    }
}

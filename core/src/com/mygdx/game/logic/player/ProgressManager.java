package com.mygdx.game.logic.player;

import com.mygdx.game.data.PlayerClass;
import com.mygdx.game.ui.UIManager;

import java.util.HashMap;

public class ProgressManager {
    private static ProgressManager INSTANCE;

    public static ProgressManager getINSTANCE(){
        if (INSTANCE == null) INSTANCE = new ProgressManager();
        return INSTANCE;
    }

    private final HashMap<String, Integer> bonuses = new HashMap<>();
    private final HashMap<PlayerClass, Boolean> classes = new HashMap<>();
    private int totalMasteryPoints = 0;
    private int availableMasteryPoints = 0;
    private final static int maxPoints = 40;
    private final static int maxPointsPerCategory = 10;
    private int nextLevelXpRequirement = 150;
    private int currentXp = 0;
    private int level = 0;

    private ProgressManager(){
        // TODO : load and save progress
        // TODO : save current progress

        bonuses.put("Health", 0);
        bonuses.put("Energy", 0);
        bonuses.put("Double coin chance", 0);
        bonuses.put("Critical chance", 0);


        classes.put(PlayerClass.Knight, true);
        classes.put(PlayerClass.Mage, true);
        classes.put(PlayerClass.Midas, false);
        classes.put(PlayerClass.Summoner, false);
        classes.put(PlayerClass.Haunted, false);
    }

    public float getBonus(String bonusName){
        float multiplier;
        switch(bonusName){
            default: multiplier = 1; break;
            case "Health": multiplier = 0.5f; break;
            case "Energy": multiplier = 0.75f; break;
            case "Double coin chance": multiplier = 0.02f; break;
            case "Critical chance": multiplier = 0.03f; break;
        }
        return (int) (bonuses.get(bonusName) * multiplier);
    }

    public HashMap<String, Integer> getBonuses(){
        return bonuses;
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

    public void unlockClass(PlayerClass className){
        if (level >= 10){
            level -= 10;
            classes.put(className, true);
        }
    }

    public HashMap<PlayerClass, Boolean> getClasses() {
        return classes;
    }

    public int getXp(){
        return currentXp;
    }

    public int getNextLevelXpRequirement(){
        return nextLevelXpRequirement;
    }
}

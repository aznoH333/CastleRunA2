package com.mygdx.game.logic.player;

import com.mygdx.game.Game;
import com.mygdx.game.data.weapons.*;

import java.util.ArrayList;
import java.util.HashMap;

public class InventoryManager {

    private static InventoryManager INSTANCE;
    public static InventoryManager getINSTANCE(){
        if (INSTANCE == null) INSTANCE = new InventoryManager();
        return INSTANCE;
    }

    private final HashMap<String,Weapon> weapons = new HashMap<>();
    private final ArrayList<String> unlockedWeapons = new ArrayList<>();
    private final ArrayList<String> unseenWeapons = new ArrayList<>();


    private InventoryManager(){
        loadWeapons();
    }

    private void loadWeapons(){
        resetState();
    }

    public ArrayList<String> getUnlockedWeapons(){
        return unlockedWeapons;
    }

    public void resetState(){
        weapons.clear();
        unseenWeapons.clear();
        unlockedWeapons.clear();
        // reload weapons
        addWeapon("Nothing", new None());
        addWeapon("Small daggers", new SmallDagger());
        addWeapon("Sword", new Sword());
        addWeapon("Axe", new ThrowingAxe());
        addWeapon("Cross", new BoomerangCross());
        addWeapon("Fireball", new FireBall());
        addWeapon("Bubble", new Bubble());
        addWeapon("Bomb", new Bomb());
        addWeapon("Rocks", new Rocks());
        addWeapon("Midas Sword", new MidasSword());

    }

    private void addWeapon(String name, Weapon weapon){
        weapons.put(name, weapon);
        unseenWeapons.add(name);
    }

    // unlocks a weapon (for the current run)
    public void unlockWeapon(String name){
        unlockedWeapons.add(name);
    }

    public Weapon getUnlockedWeapon(int index){
        return getWeapon(unlockedWeapons.get(index));
    }

    public boolean isWeaponUnlocked(String weaponName){
        return unlockedWeapons.contains(weaponName);
    }

    public Weapon getWeapon(String weaponName){
        return weapons.get(weaponName);
    }

    public String getRandomUnseenWeapon(){
        // weapons can only be seen once per run
        if (unseenWeapons.size() > 0){
            String temp = unseenWeapons.get(Game.getSeededRandom().nextInt(unseenWeapons.size()));
            unseenWeapons.remove(temp);
            return temp;
        }
        // TODO : this is literally breakfasting maybe reset the weapon list??
        return "Small daggers";
    }

    public void unlockStartingWeapon(String startingWeapon){
        unlockWeapon("Nothing");
        unseenWeapons.remove("Nothing");
        unlockWeapon(startingWeapon);
        unseenWeapons.remove(startingWeapon);
    }
}

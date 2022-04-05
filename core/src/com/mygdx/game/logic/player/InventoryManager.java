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
        // TODO: separate permanent unlocks & run unlocks
        resetState();
    }

    public ArrayList<String> getUnlockedWeapons(){
        return unlockedWeapons;
    }

    public void resetState(){
        weapons.clear();
        unseenWeapons.clear();
        // reload weapons
        addWeapon("Nothing", new None());
        addWeapon("Small daggers", new SmallDagger());
        addWeapon("Sword", new Sword());
        addWeapon("Axe", new ThrowingAxe());
        addWeapon("Cross", new BoomerangCross());


        unlockWeapon("Nothing");
        unseenWeapons.remove("Nothing");
        unlockWeapon("Sword");
        unseenWeapons.remove("Sword");
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
        String temp = unseenWeapons.get(Game.getSeededRandom().nextInt(unseenWeapons.size())-1);
        unseenWeapons.remove(temp);
        return temp;
    }
}

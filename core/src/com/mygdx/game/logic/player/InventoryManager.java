package com.mygdx.game.logic.player;

import com.mygdx.game.data.weapons.None;
import com.mygdx.game.data.weapons.SmallDagger;
import com.mygdx.game.data.weapons.Sword;

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
    // TODO : this

    private InventoryManager(){
        loadWeapons();
    }

    private void loadWeapons(){
        // TODO: separate permanent unlocks & run unlocks
        weapons.put("Nothing", new None());
        weapons.put("Small daggers", new SmallDagger());
        weapons.put("Sword", new Sword());


        unlockWeapon("Nothing");
        unlockWeapon("Small daggers");
        unlockWeapon("Sword");
    }

    public ArrayList<String> getUnlockedWeapons(){
        return unlockedWeapons;
    }

    // unlocks a weapon
    // !doesn't check if a weapon exists!
    // TODO: unlock system
    public void unlockWeapon(String name){
        unlockedWeapons.add(name);
    }

    public boolean isWeaponUnlocked(String weaponName){
        return unlockedWeapons.contains(weaponName);
    }

    public Weapon getWeapon(String weaponName){
        return weapons.get(weaponName);
    }
}

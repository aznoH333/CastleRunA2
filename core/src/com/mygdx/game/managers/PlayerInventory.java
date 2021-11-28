package com.mygdx.game.managers;

import com.mygdx.game.data.Weapon;
import com.mygdx.game.data.weapons.None;
import com.mygdx.game.data.weapons.SmallDagger;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerInventory {
    private static PlayerInventory INSTANCE;
    public static PlayerInventory getINSTANCE(){
        if (INSTANCE == null) INSTANCE = new PlayerInventory();
        return INSTANCE;
    }

    private Weapon currentLeftWeapon;
    private Weapon currentRightWeapon;
    private final HashMap<String,Weapon> weapons = new HashMap<>();
    private final ArrayList<String> unlockedWeapons = new ArrayList<>();

    private PlayerInventory(){
        // TODO: starting equipment??
        // TODO: rewrite these to an external class like tile loadlist

        weapons.put("Nothing", new None(""));
        weapons.put("Small daggers", new SmallDagger("dagger0"));

        // temporary
        unlockWeapon("Nothing");
        unlockWeapon("Small daggers");
        equipWeapon("Nothing", true);
        equipWeapon("Small daggers", false);
    }
    // equips a weapon
    // left determines the slot in which a weapon is equipped
    public void equipWeapon(String weaponName, boolean left){
        if (unlockedWeapons.contains(weaponName))
            if (left)   currentLeftWeapon = weapons.get(weaponName);
            else        currentRightWeapon = weapons.get(weaponName);
    }

    // unlocks a weapon
    // !doesn't check if a weapon exists!
    // TODO: unlock system
    public void unlockWeapon(String name){
        unlockedWeapons.add(name);
    }

    public Weapon getLeftWeapon(){
        return currentLeftWeapon;
    }

    public Weapon getRightWeapon(){
        return currentRightWeapon;
    }

    // TODO: ui display
}

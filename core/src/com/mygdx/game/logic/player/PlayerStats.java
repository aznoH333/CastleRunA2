package com.mygdx.game.logic.player;

import com.mygdx.game.data.weapons.None;
import com.mygdx.game.data.weapons.SmallDagger;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerStats {
    private static PlayerStats INSTANCE;
    public static PlayerStats getINSTANCE(){
        if (INSTANCE == null) INSTANCE = new PlayerStats();
        return INSTANCE;
    }

    private Weapon currentLeftWeapon;
    private Weapon currentRightWeapon;
    private final HashMap<String,Weapon> weapons = new HashMap<>();
    private final ArrayList<String> unlockedWeapons = new ArrayList<>();


    private int maxHp = 3;
    private int maxEnergy = 3;
    private int hp = 3;
    private int energy = 3;

    private PlayerStats(){
        // TODO: separate permanent unlocks & run unlocks
        // TODO: starting equipment??
        // TODO: rewrite these to an external class like tile loadlist

        weapons.put("Nothing", new None(""));
        weapons.put("Small daggers", new SmallDagger("dagger0",1,1));

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

    public int getMaxHp() {
        return maxHp;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public int getHp() {
        return hp;
    }

    public int getEnergy() {
        return energy;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }
}

package com.mygdx.game.logic.player;

import com.mygdx.game.data.enums.Controls;
import com.mygdx.game.data.weapons.None;
import com.mygdx.game.data.weapons.SmallDagger;
import com.mygdx.game.data.weapons.Sword;

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
    private int energy = 0;
    private int coins = 0;

    private PlayerStats(){
        // TODO: separate permanent unlocks & run unlocks
        // TODO: starting equipment??
        // TODO: rewrite these to an external class like tile loadlist

        weapons.put("Nothing", new None());
        weapons.put("Small daggers", new SmallDagger());
        weapons.put("Sword", new Sword());

        // temporary
        unlockWeapon("Nothing");
        unlockWeapon("Small daggers");
        unlockWeapon("Sword");
        equipWeapon("Nothing", Controls.AttackLeft);
        equipWeapon("Sword", Controls.AttackRight);
    }
    // equips a weapon
    public void equipWeapon(String weaponName, Controls slot){
        if (unlockedWeapons.contains(weaponName))
            if (slot == Controls.AttackLeft)    currentLeftWeapon = weapons.get(weaponName);
            else                                currentRightWeapon = weapons.get(weaponName);
    }

    public void useWeapon(float x, float y, Controls slot){
        Weapon w;
        if (slot == Controls.AttackLeft)    w = currentLeftWeapon;
        else                                w = currentRightWeapon;

        if (w.getAttackCost() <= energy){
            w.attack(x,y);
            energy -= w.getAttackCost();
        }
    }

    public void useChargedWeapon(float x, float y, Controls slot){
        Weapon w;
        if (slot == Controls.AttackLeft)    w = currentLeftWeapon;
        else                                w = currentRightWeapon;

        if (w.getChargedAttackCost() <= energy){
            w.chargedAttack(x,y);
            energy -= w.getChargedAttackCost();
        }
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

    public int getCoins() {
        return coins;
    }
    public void setCoins(int coins) {
        this.coins = coins;
    }
    public void addCoins(int coins) {
        this.coins += coins;
    }

}

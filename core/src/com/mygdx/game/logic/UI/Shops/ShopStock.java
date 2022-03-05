package com.mygdx.game.logic.UI.Shops;

import com.mygdx.game.logic.player.PlayerStats;

import java.util.Random;

public class ShopStock {

    // shop stock
    // TODO : refil hp/energy
    // TODO : health upgrade & energy upgrade
    // TODO : random weapon
    // TODO : items | weapon upgrades

    private final Random r;
    private final static PlayerStats stats = PlayerStats.getINSTANCE();
    public ShopStock(Random r){
        this.r = r;
    }

    private ShopItem[] items;

    // generate new items for sale
    public void restock(int shopLevel){
        items = new ShopItem[shopLevel];
        for (int i = 0; i < shopLevel; i++) {
            items[i] = new ShopItem("player0", 2, 3, ()->{
                System.out.println("scam lmao");
            });
        }
    }


    public int getLength(){
        return items.length;
    }

    public ShopItem getItem(int index){
        return items[index];
    }

    public void buyItem(int index){
        if (items[index].isInStock() && stats.getCoins() >= items[index].getCost()){
            items[index].buyItem();
            stats.addCoins(-items[index].getCost());
        }
    }

}

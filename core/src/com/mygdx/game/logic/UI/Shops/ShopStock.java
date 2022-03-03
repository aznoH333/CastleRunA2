package com.mygdx.game.logic.UI.Shops;

import java.util.ArrayList;
import java.util.Random;

public class ShopStock {

    // shop stock
    // TODO : refil hp/energy
    // TODO : health upgrade & energy upgrade
    // TODO : random weapon
    // TODO : items | weapon upgrades

    private final Random r;
    public ShopStock(Random r){
        this.r = r;
    }

    private ArrayList<ShopItem> items;

    // generate new items for sale
    public void restock(int shopLevel){
        items = new ArrayList<>(shopLevel);
        for (int i = 0; i < shopLevel; i++) {
            items.add(new ShopItem("player0", 2, 3, ()->{
                System.out.println("scam lmao");
            }));
        }
    }

    public void renderStock(){
        // TODO: this
    }

}

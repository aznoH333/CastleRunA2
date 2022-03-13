package com.mygdx.game.logic.UI.Shops;

import com.mygdx.game.data.ILambdaFunction;
import com.mygdx.game.items.items.FriendlyOrbItem;
import com.mygdx.game.logic.player.InventoryManager;
import com.mygdx.game.logic.player.ItemManager;
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
    private final static InventoryManager inventory = InventoryManager.getINSTANCE();
    private final static ItemManager item = ItemManager.getINSTANCE();
    public ShopStock(Random r){
        this.r = r;
    }

    private ShopItem[] items;

    // generate new items for sale

    public void restock(int shopLevel){
        items = new ShopItem[shopLevel];

        // first items is always a restock
        items[0] = new ShopItem("shop_icon2",15,3, stats::restoreStats, "restore");

        for (int i = 1; i < shopLevel; i++) {

            float random = r.nextFloat();
            ILambdaFunction buyFunction;
            // random permanent upgrade
            if (random < 0.33f){
                if (r.nextFloat() <= 0.5f)      items[i] = new ShopItem("shop_icon0", 20, 1, stats::upgradeHp, "upgrade hp");
                else                            items[i] = new ShopItem("shop_icon1", 20, 1, stats::upgradeEnergy, "upgrade energy");
            }else if (random < 0.66f){
                // random weapon unlock
                // TODO : unlock randomization
                items[i] = new ShopItem("player0", 15, 1, ()->inventory.unlockWeapon("Cross"), "buy weapon");
            }else{
                // random item
                // TODO : item randomization
                items[i] = new ShopItem("player3", 15, 1, ()->{
                    item.addItem(new FriendlyOrbItem());
                }, "buy item");
            }

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

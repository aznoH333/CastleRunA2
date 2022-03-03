package com.mygdx.game.logic.UI.Shops;

import com.mygdx.game.logic.stage.StageManager;

import java.util.Random;

public class Shop {
    private static Shop INSTANCE;

    public static Shop getINSTANCE(){
        if (INSTANCE == null) INSTANCE = new Shop();
        return INSTANCE;
    }

    private Random r;
    private ShopStock shopStock;
    private static final StageManager stage = StageManager.getINSTANCE();

    public void setRandom(Random r){
        this.r = r;
    }

    public void restock(int shopLevel){
        if (shopStock == null) shopStock = new ShopStock(r);
        shopStock.restock(shopLevel);
    }
}

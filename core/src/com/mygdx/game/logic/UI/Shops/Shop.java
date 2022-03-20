package com.mygdx.game.logic.UI.Shops;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.ButtonType;
import com.mygdx.game.logic.UI.Button;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.player.InventoryManager;
import com.mygdx.game.logic.player.PlayerStats;

import java.util.Random;

public class Shop {
    private static Shop INSTANCE;

    public static Shop getINSTANCE(){
        if (INSTANCE == null) INSTANCE = new Shop();
        return INSTANCE;
    }

    private final static Random r = Game.getSeededRandom();
    private ShopStock shopStock;
    private final static DrawingManager spr = DrawingManager.getINSTANCE();
    private final static PlayerStats stats = PlayerStats.getINSTANCE();


    private Button[] buttons = new Button[0];

    public void restock(int shopLevel){
        if (shopStock == null) shopStock = new ShopStock(r);
        shopStock.restock(shopLevel);
        buttons = new Button[shopStock.getLength()];

        for (int i = 0; i < shopStock.getLength(); i++) {
            int finalI = i;
            // TODO : smaller shop cards
            buttons[i] = new Button(ButtonType.LargeItemSelect,shopStock.getItem(i).getSprite(), (i%3 * 232) + 20, (int) (270 + (Math.floor((float)i/3)*272)),()-> shopStock.buyItem(finalI), shopStock.getItem(i).getText());
        }
    }

    public void draw(){
        for (int i = 0; i < shopStock.getLength(); i++) {
            buttons[i].manageInput();
            buttons[i].draw();
        }
        spr.drawText(String.valueOf(stats.getCoins()),64,64);
    }
}

package com.mygdx.game.logic.UI.Shops;

import com.mygdx.game.data.enums.ButtonType;
import com.mygdx.game.logic.UI.Button;
import com.mygdx.game.logic.sprites.SpriteManager;
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
    private final SpriteManager spr = SpriteManager.getINSTANCE();


    public void setRandom(Random r){
        this.r = r;
    }

    private Button[] buttons = new Button[0];

    public void restock(int shopLevel){
        if (shopStock == null) shopStock = new ShopStock(r);
        shopStock.restock(shopLevel);
        buttons = new Button[shopStock.getLength()];

        for (int i = 0; i < shopStock.getLength(); i++) {
            int finalI = i;
            buttons[i] = new Button(ButtonType.Small,"player0",i * 96 + 16, 270,()-> shopStock.buyItem(finalI));
        }
    }

    public void draw(){
        for (int i = 0; i < shopStock.getLength(); i++) {
            buttons[i].manageInput();
            buttons[i].draw();
        }
    }
}

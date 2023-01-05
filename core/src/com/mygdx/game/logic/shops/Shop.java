package com.mygdx.game.logic.shops;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.ButtonType;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.player.PlayerStats;
import com.mygdx.game.ui.UIManager;
import com.mygdx.game.ui.elements.parents.InvisUIParent;
import com.mygdx.game.ui.elements.regularElements.Button;
import com.mygdx.game.ui.elements.regularElements.Sprite;
import com.mygdx.game.ui.elements.regularElements.Text;

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
    private final static UIManager ui = UIManager.getINSTANCE();


    public void restock(int shopLevel){
        if (shopStock == null) shopStock = new ShopStock(r);
        shopStock.restock(shopLevel);
    }

    public void loadUI(){
        InvisUIParent parent = new InvisUIParent();
        ui.addUIElement(parent);
        for (int i = 0; i < shopStock.getLength(); i++){
            ShopItem shp = shopStock.getItem(i);
            int finalI = i;
            Button btn = new Button(16, 1280 - (216 * (i+1)), ButtonType.ShopItem, parent, ()->shopStock.buyItem(finalI));
            Sprite box = new Sprite(16, 64, "shop_ui_backdrop", btn);
            ui.addUIElement(btn);
            ui.addUIElement(box);
            ui.addUIElement(new Sprite(16,16, shp.getSprite(), box));
            ui.addUIElement(new Text(112,128,shp.getText(),btn));
            ui.addUIElement(new Text(ButtonType.ShopItem.width - 96, 128,String.valueOf(shp.getCost()),btn));
        }
    }



}

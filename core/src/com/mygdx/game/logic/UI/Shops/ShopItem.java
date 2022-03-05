package com.mygdx.game.logic.UI.Shops;

import com.mygdx.game.data.ILambdaFunction;

public class ShopItem {

    private final String sprite;
    private final int cost;
    private final ILambdaFunction action;
    private int stock;

    public ShopItem(String sprite, int cost, int stock, ILambdaFunction action){
        this.sprite = sprite;
        this.cost = cost;
        this.action = action;
        this.stock = stock;
    }

    public void buyItem(){
        stock--;
        action.function();
    }

    public boolean isInStock(){
        return stock > 0;
    }

    public int getCost(){
        return cost;
    }

    public String getSprite(){
        return sprite;
    }

    public ILambdaFunction getAction(){return action;}
}

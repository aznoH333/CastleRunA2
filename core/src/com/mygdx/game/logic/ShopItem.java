package com.mygdx.game.logic;

import com.mygdx.game.data.ILambdaFunction;

public class ShopItem {

    private final String sprite;
    private final int cost;
    private final ILambdaFunction action;
    private int stock;
    private final String text;

    public ShopItem(String sprite, int cost, int stock, ILambdaFunction action, String text){
        this.sprite = sprite;
        this.cost = cost;
        this.action = action;
        this.stock = stock;
        this.text = text;
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

    public String getText(){return text;}
}

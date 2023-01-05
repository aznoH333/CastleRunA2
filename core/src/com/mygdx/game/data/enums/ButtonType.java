package com.mygdx.game.data.enums;

import com.mygdx.game.Game;

public enum ButtonType {
    SmallAction((Game.gameWorldWidth - 32) / 2 - 12, 192, "large_button"),
    Small((Game.gameWorldWidth - 32) / 2 - 12, 120, "button"),
    Large(Game.gameWorldWidth - 32, 120, "button"),
    ShopItem(Game.gameWorldWidth - 32, 120, "large_button"),
    Box(102, 120, "button");

    public final float width;
    public final float height;
    public final String sprite;

    ButtonType(float width, float height, String sprite){
        this.width = width;
        this.height = height;
        this.sprite = sprite;
    }
}

package com.mygdx.game.data.enums;

import com.mygdx.game.Game;

public enum ButtonType {
    SmallAction((Game.gameWorldWidth - 32) / 2 - 16, 120),
    Small((Game.gameWorldWidth - 32) / 2 - 16, 120),
    Large(Game.gameWorldWidth - 32, 120),
    LargeItemSelect(668, 200),
    Box(102, 120);

    public final float width;
    public final float height;

    ButtonType(float width, float height){
        this.width = width;
        this.height = height;
    }
}

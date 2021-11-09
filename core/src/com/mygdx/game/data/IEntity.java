package com.mygdx.game.data;
import com.mygdx.game.managers.LevelManager;
import com.mygdx.game.managers.SpriteManager;

import java.util.Random;

public interface IEntity {
    void update(LevelManager lvl, Random r);
    void draw(SpriteManager spr);
    boolean collide(IEntity other);
    float getX();
    void shiftX(float shiftBy);
    float getY();
    int getHP();
    void takeDamage(int damage);
    boolean shifts();
}

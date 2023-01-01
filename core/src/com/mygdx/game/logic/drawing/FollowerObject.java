package com.mygdx.game.logic.drawing;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Queue;
import com.mygdx.game.Game;
import com.mygdx.game.logic.level.LevelManager;


public class FollowerObject {
    // object used to do delayed sprite drawing

    private final Queue<FollowerObjectData> coordinates = new Queue<>();
    private static final DrawingManager spr = DrawingManager.getINSTANCE();
    private static final LevelManager lvl = LevelManager.getINSTANCE();
    private final int zIndex;
    private final long timeDelay;
    private final float opacity;


    public FollowerObject(int zIndex, int timeDelay, float color){
        this.zIndex = zIndex;
        this.timeDelay = timeDelay;
        this.opacity = color;
    }

    public void addCoordinate(float x, float y, String sprite){
        coordinates.addLast(new FollowerObjectData(x, y, sprite, Game.Time() - 1));
    }

    public void draw(){
        if (coordinates.size > 0){
            FollowerObjectData temp = coordinates.first();
            if (temp.getCreationTime() + timeDelay < Game.Time()){
                spr.draw(temp.getSprite(), temp.getX(), temp.getY(), zIndex, 1, 1, new Color(1,1,1, opacity), true);
                coordinates.removeFirst();

            }


            for (FollowerObjectData f: coordinates) {
                f.shiftX(lvl.getAdvanceBy()); // TODO : this is shit, but i am lazy rn.
            }
        }
    }


}

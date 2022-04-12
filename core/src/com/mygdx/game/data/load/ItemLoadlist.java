package com.mygdx.game.data.load;

import com.mygdx.game.items.interfaces.IItem;
import com.mygdx.game.items.items.FireEmblem;
import com.mygdx.game.items.items.FriendlyOrbItem;
import com.mygdx.game.items.items.VampireCharm;

import java.util.ArrayList;

public class ItemLoadlist {
    public static void loadItems (ArrayList<IItem> itemlist){
        itemlist.clear();
        itemlist.add(new FriendlyOrbItem());
        itemlist.add(new VampireCharm());
        itemlist.add(new FireEmblem());
    }
}

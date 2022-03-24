package com.mygdx.game.ui;

import com.mygdx.game.data.enums.ButtonType;
import com.mygdx.game.data.enums.Controls;
import com.mygdx.game.data.enums.GameState;
import com.mygdx.game.data.enums.UIType;
import com.mygdx.game.logic.player.InputManager;
import com.mygdx.game.ui.elements.BottomHudGame;
import com.mygdx.game.ui.elements.Button;
import com.mygdx.game.ui.elements.Sprite;
import com.mygdx.game.ui.interfaces.IUIElement;
import com.mygdx.game.ui.interfaces.IUIParentElement;
import com.mygdx.game.ui.interfaces.IUIUpdatable;

import java.util.ArrayList;

public class UIManager {
    private static UIManager INSTANCE;
    private final static InputManager input = InputManager.getINSTANCE();

    public static UIManager getINSTANCE(){
        if (INSTANCE == null) INSTANCE = new UIManager();
        return INSTANCE;
    }

    private final ArrayList<IUIElement> uiElements = new ArrayList<>();
    private final ArrayList<IUIUpdatable> uiUpdatables = new ArrayList<>();
    private final ArrayList<IUIParentElement> uiParents = new ArrayList<>();


    // dumb constants
    private final static float buttonLX = 16f;
    private final static float buttonRX = 368f;
    private final static float xIconOffset = 136;
    private final static float yIconOffset = 38;



    public void drawUI(){
        for (IUIElement element: uiElements) {
            element.draw();
        }
    }

    public void changeUI(GameState state){
        clearUI();
        switch (state){
            case Game:
                // bottom bar
                addUIElement(new BottomHudGame());
                addUIElement(new Button(buttonLX,182, ButtonType.SmallAction, uiElements.get(0),()-> input.buttonHold(Controls.MoveLeft)));
                addUIElement(new Sprite(xIconOffset, yIconOffset,"icon1", uiElements.get(uiElements.size()-1)));

                addUIElement(new Button(buttonRX,182, ButtonType.SmallAction, uiElements.get(0),()-> input.buttonHold(Controls.MoveRight)));
                addUIElement(new Sprite(xIconOffset, yIconOffset,"icon0", uiElements.get(uiElements.size()-1)));

                addUIElement(new Button(buttonLX,315, ButtonType.SmallAction, uiElements.get(0),()-> input.buttonHold(Controls.AttackLeft)));
                addUIElement(new Sprite(xIconOffset, yIconOffset,"icon3", uiElements.get(uiElements.size()-1)));

                addUIElement(new Button(buttonRX,315, ButtonType.SmallAction, uiElements.get(0),()-> input.buttonHold(Controls.AttackRight)));
                addUIElement(new Sprite(xIconOffset, yIconOffset,"icon2", uiElements.get(uiElements.size()-1)));
                break;
        }
    }

    private void addUIElement(IUIElement element){
        uiElements.add(element);
        for (UIType type: element.getTypes()) {
            switch (type){
                case Parent:
                    uiParents.add((IUIParentElement) element);
                    break;
                case Updatable:
                    uiUpdatables.add((IUIUpdatable) element);
                    break;
            }
        }
    }

    public void open(){
        for (IUIParentElement parent: uiParents) {
            parent.uiOpen();
        }
    }

    public void close(){
        for (IUIParentElement parent: uiParents) {
            parent.uiClose();
        }
    }

    public void updateUI(){
        for (IUIUpdatable element: uiUpdatables) {
            element.update();
        }
    }

    public void clearUI(){
        uiElements.clear();
        uiParents.clear();
        uiUpdatables.clear();
    }
}

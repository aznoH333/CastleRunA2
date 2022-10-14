package com.mygdx.game.ui;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Game;
import com.mygdx.game.data.ILambdaFunction;
import com.mygdx.game.data.enums.BarType;
import com.mygdx.game.data.enums.ButtonType;
import com.mygdx.game.data.enums.Controls;
import com.mygdx.game.data.enums.GameState;
import com.mygdx.game.data.enums.UIActionStatus;
import com.mygdx.game.logic.menus.InventoryScreen;
import com.mygdx.game.logic.menus.NewGameMenu;
import com.mygdx.game.logic.player.InputManager;
import com.mygdx.game.logic.player.PlayerStats;
import com.mygdx.game.logic.player.ProgressManager;
import com.mygdx.game.logic.shops.Shop;
import com.mygdx.game.logic.stage.LevelProgressionManager;
import com.mygdx.game.ui.elements.parents.BottomHud;
import com.mygdx.game.ui.elements.parents.InvisUIParent;
import com.mygdx.game.ui.elements.parents.MessageBox;
import com.mygdx.game.ui.elements.regularElements.Button;
import com.mygdx.game.ui.elements.regularElements.HudBar;
import com.mygdx.game.ui.elements.regularElements.Sprite;
import com.mygdx.game.ui.elements.regularElements.Text;
import com.mygdx.game.ui.elements.parents.TopHud;
import com.mygdx.game.ui.elements.regularElements.TransitionScreen;
import com.mygdx.game.ui.elements.parents.UIBox;
import com.mygdx.game.ui.elements.regularElements.XpBar;
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
    private GameState targetState = null;
    private boolean isTransitioning = false;
    private final static LevelProgressionManager levelMan = LevelProgressionManager.getINSTANCE();
    private final TransitionScreen transition = new TransitionScreen();
    private final static InventoryScreen invScreen = InventoryScreen.getINSTANCE();
    private final static PlayerStats playerStats = PlayerStats.getINSTANCE();
    private ILambdaFunction execOnTransitionFinish = ()->{};
    private final MessageBox msgBox = new MessageBox();
    private static final ProgressManager prgrs = ProgressManager.getINSTANCE();


    // dumb constants
    private final static float buttonLX = 16f;
    private final static float yIconOffset = 38;



    public void drawUI(){
        for (IUIElement element: uiElements) {
            element.draw();
        }
        transition.draw();
        msgBox.draw();
    }

    public void changeUI(GameState state){
        clearUI();
        switch (state){
            case Game:
                // bottom bar
                addUIElement(new BottomHud(-515f,-150f));
                addUIElement(new Button(buttonLX,182, ButtonType.SmallAction, uiElements.get(0),()-> input.buttonHold(Controls.MoveLeft)));
                addUIElement(new Sprite((Game.gameWorldWidth/2 - 64)  / 2, yIconOffset,"icon1", uiElements.get(uiElements.size()-1)));

                addUIElement(new Button(Game.gameWorldWidth/2 + 8,182, ButtonType.SmallAction, uiElements.get(0),()-> input.buttonHold(Controls.MoveRight)));
                addUIElement(new Sprite((Game.gameWorldWidth/2 - 64) / 2, yIconOffset,"icon0", uiElements.get(uiElements.size()-1)));

                addUIElement(new Button(buttonLX,315, ButtonType.SmallAction, uiElements.get(0),()-> input.buttonHold(Controls.AttackLeft)));
                addUIElement(new Sprite((Game.gameWorldWidth/2 - 64)  / 2, yIconOffset,"icon3", uiElements.get(uiElements.size()-1)));

                addUIElement(new Button(Game.gameWorldWidth/2 + 8,315, ButtonType.SmallAction, uiElements.get(0),()-> input.buttonHold(Controls.AttackRight)));
                addUIElement(new Sprite((Game.gameWorldWidth/2 - 64)  / 2, yIconOffset,"icon2", uiElements.get(uiElements.size()-1)));


                // top hud
                TopHud topHud = new TopHud();
                addUIElement(topHud);
                addUIElement(new HudBar(16,32,"barStart0", BarType.RED, topHud, playerStats::getHp, playerStats::getMaxHp));
                addUIElement(new HudBar(Game.gameWorldWidth/2 + 8, 32,  "barStart1", BarType.BLU,  topHud, playerStats::getEnergy, playerStats::getMaxEnergy));
                addUIElement(new XpBar(16, 0, topHud));
                break;
            case StageMenu:
                addUIElement(new BottomHud(-515f,-30f));
                addUIElement(new Button(buttonLX,62,ButtonType.Large,uiElements.get(0),()->transition(GameState.MainMenu)));
                addUIElement(new Sprite((Game.gameWorldWidth/2 - 64), yIconOffset,"icon4", uiElements.get(uiElements.size()-1)));

                addUIElement(new Button(buttonLX,198,ButtonType.Small,uiElements.get(0),()->transition(GameState.EquipMenu)));
                addUIElement(new Text(94,80,"Items",uiElements.get(uiElements.size()-1)));

                addUIElement(new Button(Game.gameWorldWidth/2 + 8,198,ButtonType.Small,uiElements.get(0),()->transition(GameState.Shop)));
                addUIElement(new Text(110,80,"Shop",uiElements.get(uiElements.size()-1)));

                addUIElement(new Button(buttonLX,338,ButtonType.Large,uiElements.get(0),()->{transition(GameState.Game);levelMan.startLevel();}));
                addUIElement(new Sprite((Game.gameWorldWidth/2 - 64), yIconOffset,"icon0", uiElements.get(uiElements.size()-1)));
                break;
            case Shop:
                addUIElement(new BottomHud(-515f,-284));
                addUIElement(new Button(buttonLX, 364, ButtonType.Large,uiElements.get(0),()->transition(GameState.StageMenu)));
                addUIElement(new Sprite((Game.gameWorldWidth/2 - 64), yIconOffset,"icon4", uiElements.get(uiElements.size()-1)));
                Shop.getINSTANCE().loadUI();
                break;
            case EquipMenu:
                addUIElement(new BottomHud(-515f,-150f));
                addUIElement(new Button(buttonLX, 182, ButtonType.Large,uiElements.get(0),()->transition(GameState.StageMenu)));
                addUIElement(new Sprite((Game.gameWorldWidth/2 - 64), yIconOffset,"icon4", uiElements.get(uiElements.size()-1)));

                addUIElement(new Button(buttonLX, 315, ButtonType.Small,uiElements.get(0),()->invScreen.changeSlot(Controls.AttackLeft)));
                addUIElement(new Sprite((Game.gameWorldWidth/2 - 64)  / 2, yIconOffset,"icon3", uiElements.get(uiElements.size()-1)));

                addUIElement(new Button(Game.gameWorldWidth/2 + 8, 315, ButtonType.Small,uiElements.get(0),()->invScreen.changeSlot(Controls.AttackRight)));
                addUIElement(new Sprite((Game.gameWorldWidth/2 - 64)  / 2, yIconOffset,"icon2", uiElements.get(uiElements.size()-1)));
                InventoryScreen.getINSTANCE().loadUI();
                break;

            case GameOver:
                addUIElement(new UIBox(521));
                addUIElement(new Button(16,16,ButtonType.Small,uiElements.get(0),()-> Gdx.app.exit()));
                addUIElement(new Sprite((Game.gameWorldWidth/2 - 64)  / 2, yIconOffset,"icon4", uiElements.get(uiElements.size()-1)));

                addUIElement(new Button(16,168,ButtonType.Small,uiElements.get(0),()-> transition(GameState.NewGameMenu)));
                addUIElement(new Sprite((Game.gameWorldWidth/2 - 64)  / 2, yIconOffset,"icon0", uiElements.get(uiElements.size()-1)));
                break;

            case MainMenu:
                addUIElement(new BottomHud(-515f,0f));
                addUIElement(new Button(buttonLX,32,ButtonType.Large,uiElements.get(0),()->Gdx.app.exit()));
                addUIElement(new Sprite((Game.gameWorldWidth/2 - 64), yIconOffset,"icon4", uiElements.get(uiElements.size()-1)));

                addUIElement(new Button(buttonLX,168,ButtonType.Large,uiElements.get(0),()->{}));
                addUIElement(new Text(94,80,"Options",uiElements.get(uiElements.size()-1)));

                addUIElement(new Button(buttonLX,304,ButtonType.Large,uiElements.get(0),()->transition(GameState.NewGameMenu)));
                addUIElement(new Sprite((Game.gameWorldWidth/2 - 64), yIconOffset,"icon0", uiElements.get(uiElements.size()-1)));
                break;
            case NewGameMenu:

                addUIElement(new BottomHud(-515f,-30f));

                addUIElement(new Button(buttonLX,62, ButtonType.Large, uiElements.get(0),()-> transition(GameState.MainMenu)));
                addUIElement(new Sprite((Game.gameWorldWidth/2 - 64), yIconOffset,"icon4", uiElements.get(uiElements.size()-1)));

                addUIElement(new Button(buttonLX,198,ButtonType.Large,uiElements.get(0),()->transition(GameState.MasteryScreen)));
                addUIElement(new Text((Game.gameWorldWidth/2 - (5*25)),80,"Points",uiElements.get(uiElements.size()-1)));

                addUIElement(new Button(buttonLX,338, ButtonType.Large, uiElements.get(0), ()->ng.startNewGame(Game.getGeneralRandom().nextInt(999))));
                addUIElement(new Sprite((Game.gameWorldWidth/2 - 64), yIconOffset,"icon1", uiElements.get(uiElements.size()-1)));
                break;
            case MasteryScreen:
                addUIElement(new InvisUIParent());


                String[] bonuses = prgrs.getBonuses().keySet().toArray(new String[]{});
                // stats
                for (int i = 0; i < bonuses.length; i++){
                    int finalI = i;
                    addUIElement(new Button(buttonLX, 640 + 258 - (i * 128), ButtonType.Box, uiElements.get(0), ()-> prgrs.refundPoint(bonuses[finalI])));
                    addUIElement(new Sprite(8,8,"player0", uiElements.get(uiElements.size()-1)));

                    addUIElement(new Text((Game.gameWorldWidth/2 - (5*25)),640 + 256 + 32 - (i * 128),bonuses[finalI],uiElements.get(0)));

                    addUIElement(new Button(Game.gameWorldWidth - 120 - buttonLX, 640 + 256 - (i * 128), ButtonType.Box, uiElements.get(0), ()-> prgrs.spendPoint(bonuses[finalI])));
                    addUIElement(new Sprite(8,8,"player0", uiElements.get(uiElements.size()-1)));
                }

                addUIElement(new Button(16,64,ButtonType.Large,uiElements.get(0),()-> transition(GameState.NewGameMenu)));
                addUIElement(new Sprite((Game.gameWorldWidth/2 - 64)  / 2, yIconOffset,"icon0", uiElements.get(uiElements.size()-1)));

                break;// TODO : clean this up
        }
    }

    private static final NewGameMenu ng = NewGameMenu.getINSTANCE();

    public void addUIElement(IUIElement element){
        uiElements.add(element);
        if (element instanceof IUIParentElement) uiParents.add((IUIParentElement) element);
        if (element instanceof IUIUpdatable) uiUpdatables.add((IUIUpdatable) element);
    }

    private boolean openOnTransitionFinish = true;
    private boolean changeStateOnTransitionClose = false;
    public void delayedOpening(){
        openOnTransitionFinish = true;
        transition.uiOpen();
    }

    private void open(){
        for (IUIParentElement parent: uiParents) {
            parent.uiOpen();
        }
    }

    public void close(){
        for (IUIParentElement parent: uiParents) {
            parent.uiClose();
        }
        msgBox.uiClose();
    }

    public void updateUI(){
        for (IUIUpdatable element: uiUpdatables) {
            element.update();
        }
        transition.update();
        msgBox.update();

        if (openOnTransitionFinish && transition.getStatus() == UIActionStatus.Open){
            openOnTransitionFinish = false;
            open();
        }

        if (changeStateOnTransitionClose && transition.getStatus() == UIActionStatus.Closed){
            changeStateOnTransitionClose = false;
            Game.changeState(targetState);
            execOnTransitionFinish.function();
        }

        // transition handling
        if (isTransitioning){
            boolean transitionComplete = true;

            for (IUIParentElement parent: uiParents) {
                if (parent.getStatus() != UIActionStatus.Closed) transitionComplete = false;
            }

            if(transitionComplete){
                isTransitioning = false;
                transition.uiClose();
                changeStateOnTransitionClose = true;
            }
        }


    }

    public void clearUI(){
        uiElements.clear();
        uiParents.clear();
        uiUpdatables.clear();
    }

    public void transition(GameState state, ILambdaFunction execOnTransition){
        close();
        isTransitioning = true;
        targetState = state;
        this.execOnTransitionFinish = execOnTransition;
    }


    public void displayMessage(String text){
        msgBox.setText(text);
        msgBox.uiOpen();
    }

    public void transition(GameState state){
        transition(state, ()->{});
    }

    public boolean isTransitioning(){
        return transition.getStatus() != UIActionStatus.Transitioning && !isTransitioning;
    }
}

package com.mygdx.game.ui.interfaces;

import com.mygdx.game.data.enums.UIActionStatus;

public interface IUIParentElement {
    void uiOpen();
    void uiClose();
    UIActionStatus getStatus();
}

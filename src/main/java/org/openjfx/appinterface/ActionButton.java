package org.openjfx.appinterface;

import javafx.scene.control.Button;

public class ActionButton extends Button {
    public ActionButton(String text, Runnable action) {
        super(text);
        setOnAction(o -> action.run());
    }
}

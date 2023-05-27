package lu.uni.rpg.event.events;

import javafx.scene.input.KeyCode;
import lu.uni.rpg.event.Event;

public class KeyEvent extends Event {
    private final KeyCode keyCode;
    private final boolean pressed;

    public KeyEvent(KeyCode keyCode, boolean pressed) {
        this.keyCode = keyCode;
        this.pressed = pressed;
    }

    public KeyCode getKeyCode() {
        return keyCode;
    }

    public boolean isPressed() {
        return pressed;
    }
}
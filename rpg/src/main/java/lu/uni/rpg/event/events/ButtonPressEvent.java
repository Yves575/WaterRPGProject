package lu.uni.rpg.event.events;

import lu.uni.rpg.event.Event;
import lu.uni.rpg.model.Blocks.Button;

public class ButtonPressEvent extends Event {

    private Button button;

    public ButtonPressEvent(Button pressedButton) {
        this.button = pressedButton;
    }

    public Button getButton() {
        return button;
    }

}

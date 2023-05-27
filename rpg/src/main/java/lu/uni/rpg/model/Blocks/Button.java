package lu.uni.rpg.model.Blocks;

import lu.uni.rpg.RPG;
import lu.uni.rpg.event.events.ButtonPressEvent;

public class Button extends Block {

    private boolean pressed = false;
    private int toX;
    private int toY;

    public Button(int x, int y) {
        super(x, y);
        Obstruct = false;
    }

    @Override
    public String getTexture() {
        if (pressed) {
            return "sprites/button-off-tile.png";
        } else {
            return "sprites/button-on-tile.png";
        }
    }

    @Override
    public String getString() {
        return "Button Block";
    }

    public void setLinkedDoor(int x, int y) {
        toX = x;
        toY = y;
    }

    public int getToX() {
        return toX;
    }

    public int getToY() {
        return toY;
    }

    public void setPressed(boolean b) {
        pressed = b;

        // TODO: Change the door's texture

        RPG.getInstance().getEngine().getEventManager().callEvent(new ButtonPressEvent(this));
    }

    public boolean getPressed() {
        return pressed;
    }

    public boolean canToggle() {
        return false;
    }
}
package lu.uni.rpg.event.events;

import javafx.stage.Stage;
import lu.uni.rpg.event.Event;
import lu.uni.rpg.model.Entities.Player;

public class PlayerMoveEvent extends Event {

    private Player player;
    private int oldX;
    private int oldY;
    private Stage oldStage;

    private int newX;
    private int newY;
    private Stage newStage;
    private boolean changedRoom;

    public PlayerMoveEvent(Player player, int oldX, int oldY, int newX, int newY, boolean changedRoom) {
        this.player = player;
        this.oldX = oldX;
        this.oldY = oldY;
        this.oldStage = oldStage;
        this.newX = newX;
        this.newY = newY;
        this.newStage = newStage;
        this.changedRoom = changedRoom;
    }

    public Player getPlayer() {
        return player;
    }

    public int getOldX() {
        return oldX;
    }

    public int getOldY() {
        return oldY;
    }

    public Stage getOldStage() {
        return oldStage;
    }

    public int getNewX() {
        return newX;
    }

    public int getNewY() {
        return newY;
    }

    public Stage getNewStage() {
        return newStage;
    }

    public boolean hasChangedRoom() {
        return changedRoom;
    }
}

package lu.uni.rpg.controller.Actions;

import javafx.stage.Stage;
import lu.uni.rpg.controller.RpgEngine;
import lu.uni.rpg.model.Blocks.Button;
import lu.uni.rpg.model.Blocks.Door;
import lu.uni.rpg.model.Entities.Player;
import lu.uni.rpg.model.Map;


// ONLY to use if the player is on a Button !
// Aim to open or close Door
@Deprecated
public class DoorChangeAction implements Action, toDefineStage {

    private RpgEngine outer;
    private Stage s;

    public DoorChangeAction(RpgEngine outer) {
        this.outer = outer;
    }

    // Change the state of a button, switch it between true and false
    // Finally change the state of the Door, each Button are linked to a MAIN Door

    // ERROR HERE TO FIX: when a Player is walking on a Button the switch is made, the button
    // disappear but we can still walking on the hidden Button and so switch again, thus a player can
    // switch to off again without knowing it.
    // ~Korvin: I think this is fixed now, but I'm not sure
    @Override
    public void update() {
        Player player = outer.getMap().getRoom().getPlayer();
        Button button = (Button) player.getBlock();

        int x = button.getToX();
        int y = button.getToY();
        boolean pressed = button.getPressed();

        if(!button.canToggle() && button.getPressed()) {
            System.out.println("Button can't be toggled, modify canToggle method");
            return;
        }

        button.setPressed(!pressed);
        ((Door) (Map.MAIN.getRoom().getGrid()[y][x])).setObstruct(pressed);
        outer.render(s);
    }

    @Override
    public void setStage(Stage s) {
        this.s = s;
    }
}
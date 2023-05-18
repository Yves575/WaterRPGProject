package rpg.controller.Actions;

import javafx.stage.Stage;
import rpg.controller.RpgEngine;
import rpg.model.Blocks.Button;
import rpg.model.Blocks.Door;
import rpg.model.Entities.Player;
import rpg.model.Map;

// ONLY to use if the player is on a Button !
// Aim to open or close Door
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
  @Override
  public void update() {
    Player player = outer.getMap().getRoom().getPlayer();
    int x = ((Button) (player.getBlock())).getToX();
    int y = ((Button) (player.getBlock())).getToY();
    boolean pressed = ((Button) player.getBlock()).getPressed();
    ((Button) player.getBlock()).setPressed(!pressed);
    ((Door) (Map.MAIN.getRoom().getGrid()[y][x])).setObstruct(pressed);
    outer.render(s);
  }

  @Override
  public void setStage(Stage s) {
    this.s = s;
  }
}

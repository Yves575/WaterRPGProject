package rpg.controller.Actions;

import java.util.List;
import javafx.stage.Stage;
import rpg.controller.RpgEngine;
import rpg.model.Blocks.Block;
import rpg.model.Entities.Player;

// Action a list of Action when the Player is on a particular Block
public class PositionAction implements Action, toDefineStage {

  private String blockname;
  private List<Action> actions;
  private Player player;
  private RpgEngine outer;

  public PositionAction(List<Action> actions, String block, RpgEngine outer) {
    this.actions = actions;
    blockname = block;
    this.outer = outer;
  }

  @Override
  public void update() {
    player = outer.getMap().getRoom().getPlayer();
    if (((player.getBlock()) instanceof Block)) {
      if ((player.getBlock()).getString() == blockname) {
        for (Action action : actions) {
          action.update();
        }
      }
    }
  }

  // Define Stage for each action that need a stage
  @Override
  public void setStage(Stage s) {
    for (Action action : actions) {
      if (action instanceof toDefineStage) {
        ((toDefineStage) action).setStage(s);
      }
    }
  }
}

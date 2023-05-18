package rpg.controller.Actions;

import javafx.stage.Stage;
import rpg.model.Blocks.Door;
import rpg.model.Map;

// Action when the NegativePlayer die
public class OnNegPlayerDeadAction implements Action, toDefineStage {

  @Override
  public void update() {
    ((Door) (Map.MAIN.getRoom().getGrid()[0][5])).setObstruct(false);
  }

  @Override
  public void setStage(Stage s) {
    // We can use stage to render a killed animation here;
  }
}

package rpg.controller.Actions;

import javafx.stage.Stage;
import rpg.model.Blocks.Door;
import rpg.model.Map;

// Action when the Bulbasaur die :(, it activate the next door
public class OnBulbasaurDeadAction implements Action, toDefineStage {

  @Override
  public void update() {
    ((Door) (Map.MAIN.getRoom().getGrid()[3][10])).setObstruct(false);
  }

  @Override
  public void setStage(Stage s) {
    // We can use stage to render a killed animation here;
  }
}

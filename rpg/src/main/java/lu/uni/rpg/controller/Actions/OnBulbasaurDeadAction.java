package lu.uni.rpg.controller.Actions;

import javafx.stage.Stage;
import lu.uni.rpg.model.Blocks.Door;
import lu.uni.rpg.model.Map;

// Action when the Bulbasaur die :(, it activate the next door
@Deprecated
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
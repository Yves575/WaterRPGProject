package lu.uni.rpg.controller.Actions;

import javafx.stage.Stage;
import lu.uni.rpg.model.Blocks.Door;
import lu.uni.rpg.model.Map;

// Action when the NegativePlayer die
@Deprecated
public class OnNegPlayerDeadAction implements Action, toDefineStage {

    @Override
    public void update() {
        Door door = (Door) (Map.MAIN.getRoom().getGrid()[0][5]);
        door.setObstruct(false);
    }

    @Override
    public void setStage(Stage s) {
        // We can use stage to render a killed animation here;
    }
}
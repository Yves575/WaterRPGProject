package lu.uni.rpg.controller.Actions;

import javafx.stage.Stage;
import lu.uni.rpg.model.Entities.Entity;

// Aim to check if an Entity is still alive
public class CheckAliveAction implements Action, toDefineStage {

    private Entity entity;
    private Action action;

    public CheckAliveAction(Action action, Entity entity) {
        this.action = action;
        this.entity = entity;
    }

    @Override
    public void update() {
        if (!(entity.isAlive())) {
            action.update();
        }
    }

    @Override
    public void setStage(Stage s) {
        ((toDefineStage) action).setStage(s);
    }
}
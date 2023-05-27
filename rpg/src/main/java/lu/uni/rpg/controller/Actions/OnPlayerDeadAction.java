package lu.uni.rpg.controller.Actions;

import javafx.stage.Stage;
import lu.uni.rpg.UI.Renderer;
import lu.uni.rpg.controller.RpgEngine;
import lu.uni.rpg.model.Entities.Player;
import lu.uni.rpg.model.Map;

// Action when the Player die
public class OnPlayerDeadAction implements Action, toDefineStage {

    private RpgEngine outer;
    private Renderer renderer;
    private Stage s;

    public OnPlayerDeadAction(RpgEngine outer, Renderer renderer) {
        this.outer = outer;
        this.renderer = renderer;
    }

    // It remove all the information from the Player when he died, and set a new player to the hub
    @Override
    public void update() {
        Player player = outer.getMap().getRoom().getPlayer();
        outer.getMap().getRoom().getEntities().remove(player);
        outer.getMap().getRoom().setGrid(player.getBlock(), player.getX(), player.getY(), true);
        player.setBlock(null);
        outer.setMap(Map.HUB);
        player.setX(5);
        player.setY(5);
        outer.getMap().getRoom().setPlayer(player);
        renderer.setRoom(outer.getMap().getRoom());
        outer.render(s);
        outer.getMap().getRoom().getPlayer().setAlive(true);
        outer.getMap().getRoom().getPlayer().setHp(3);
    }

    @Override
    public void setStage(Stage s) {
        this.s = s;
    }
}
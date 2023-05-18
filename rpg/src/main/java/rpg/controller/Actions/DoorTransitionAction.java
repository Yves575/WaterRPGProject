package rpg.controller.Actions;

import javafx.stage.Stage;
import rpg.controller.RoomController;
import rpg.controller.RpgEngine;
import rpg.model.Blocks.Block;
import rpg.model.Blocks.Door;
import rpg.model.Entities.Player;
import rpg.model.Map;

// Aim to change Door/Room.
public class DoorTransitionAction implements Action, toDefineStage, toDefineCoord {

  private RpgEngine outer;
  private Stage stage;
  private int x;
  private int y;

  public DoorTransitionAction(RpgEngine outer, RoomController controller) {
    this.outer = outer;
  }

  @Override
  public void setStage(Stage s) {
    this.stage = s;
  }

  @Override
  public void setCoord(int x, int y) {
    this.x = x;
    this.y = y;
  }

  // Check on which door the used is on and based on the destination it will send the Player to it
  @Override
  public void update() {
    Player player = outer.getMap().getRoom().getPlayer();
    Block door = (Block) outer.getMap().getRoom().getGrid()[player.getY() + y][player.getX() + x];
    if (!(door instanceof Door)) {
      throw new IllegalStateException("Unexpected value: Player must go in a door tile");
    }
    outer.getMap().getRoom().getEntities().remove(player);
    String linkedroom = ((Door) door).getLinkedRoomName();
    player.setBlock(null);
    switch (linkedroom) {
      case "HUB":
        outer.setMap(Map.HUB);
        player.setX(5);
        player.setY(1);
        break;
      case "MAIN":
        String name = outer.getMap().getRoom().toString();
        outer.setMap(Map.MAIN);
        switch (name) {
          case "Hub Room":
            player.setX(5);
            player.setY(9);
            break;
          case "Puissance4 Room":
            player.setX(1);
            player.setY(6);
            break;
          case "Zoo Room":
            player.setX(9);
            player.setY(6);
            break;
          case "Poke Room":
            player.setX(1);
            player.setY(3);
            break;
          case "Final Room":
            player.setX(5);
            player.setY(1);
            break;
          case "Rpg Room":
            player.setX(9);
            player.setY(3);
            break;
          default:
            player.setX(3);
            player.setY(3);
            break;
        }
        break;
      case "PUIS":
        outer.setMap(Map.PUIS);
        player.setX(9);
        player.setY(6);
        break;
      case "ZOO":
        outer.setMap(Map.ZOO);
        player.setX(1);
        player.setY(6);
        break;
      case "POKE":
        outer.setMap(Map.POKE);
        player.setX(9);
        player.setY(3);
        break;
      case "RPG":
        outer.setMap(Map.RPG);
        player.setX(1);
        player.setY(3);
        break;
      case "FINAL":
        outer.setMap(Map.FINAL);
        player.setX(5);
        player.setY(9);
        break;
    }
    // Set the player and update the Render of the new current Room
    outer.getMap().getRoom().setPlayer(player);
    outer.getRenderer().setRoom(outer.getMap().getRoom());
    outer.render(stage);
  }
}

package rpg.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.stage.Stage;
import rpg.UI.Renderer;
import rpg.controller.Actions.Action;
import rpg.controller.Actions.AttackAction;
import rpg.controller.Actions.CheckAliveAction;
import rpg.controller.Actions.DoorChangeAction;
import rpg.controller.Actions.DoorTransitionAction;
import rpg.controller.Actions.MoveAction;
import rpg.controller.Actions.OnBulbasaurDeadAction;
import rpg.controller.Actions.OnNegPlayerDeadAction;
import rpg.controller.Actions.OnPlayerDeadAction;
import rpg.controller.Actions.PositionAction;
import rpg.controller.Actions.toDefineCoord;
import rpg.controller.Actions.toDefineStage;
import rpg.model.Entities.Player;
import rpg.model.Map;

// Controller Class, aim to interact between the View and the Objects with Actions.
// An Action is an event/function that is triggered whenever the Player is Moving or Clicking.
// Thus, this Controller Class is divided into two parts, the Actions triggered by the Movements of
// the Player and the Actions triggered Clicks of the Player.
// On top of that, both Movement and Click Actions are variable and some of them might change
// depending on which Room the Player is.
// Indeed, some Actions (e.g. "negplayermove" or "gameover") are only present in specific Room,
// And some other are constant Actions that are always present regardless to the current Room.
public class RoomController {

  // List of Movement Actions & List of Click Actions
  private List<Action> onKeyActions = new ArrayList<>();
  private List<Action> onClickActions = new ArrayList<>();
  // Each type of Actions need its own name room tracker to allow the update of both of the Actions,
  // otherwise only one of the two will be updated
  private String currentName = "HUB";
  private String currentNameClick = "HUB";

  // Get the instance of the Engine & Set the instance of each variable Actions (the fixed Actions
  // will be only used in the constructor as they never change)
  private RpgEngine outer;
  private CheckAliveAction gameover;
  private PositionAction red_pose;
  private PositionAction button_pose;
  private MoveAction negplayermove;
  private AttackAction negplayerattack;
  private CheckAliveAction negkilled;
  private CheckAliveAction bulkilled;

  // Initiate Each Actions in our Program
  public RoomController(Renderer renderer, RpgEngine outer) {
    // get the map for the Entity (line: )
    outer.getMap();

    // Create an instance for each Action
    MoveAction playermove = new MoveAction(renderer, outer.getMap().getRoom().getPlayer(), outer);
    DoorTransitionAction doortransition = new DoorTransitionAction(outer, this);
    PositionAction door_pose =
        new PositionAction(Collections.singletonList(doortransition), "Door", outer);
    AttackAction player_attack =
        new AttackAction(renderer, outer.getMap().getRoom().getPlayer(), outer);
    OnPlayerDeadAction playerdead = new OnPlayerDeadAction(outer, renderer);
    gameover = new CheckAliveAction(playerdead, outer.getMap().getRoom().getPlayer());
    DoorChangeAction doorchange = new DoorChangeAction(outer);
    button_pose = new PositionAction(Collections.singletonList(doorchange), "Button Block", outer);
    negplayermove = new MoveAction(renderer, Map.RPG.getRoom().getEntities().get(0), outer);
    negplayerattack = new AttackAction(renderer, Map.RPG.getRoom().getEntities().get(0), outer);
    red_pose =
        new PositionAction(Collections.singletonList(playerdead), "Puissance4 Red Block", outer);
    OnNegPlayerDeadAction negplayerdead = new OnNegPlayerDeadAction();
    negkilled = new CheckAliveAction(negplayerdead, Map.RPG.getRoom().getEntities().get(0));
    OnBulbasaurDeadAction buldead = new OnBulbasaurDeadAction();
    bulkilled = new CheckAliveAction(buldead, Map.POKE.getRoom().getEntities().get(0));

    // Add the fixed Actions, the one that are used regardless of the room
    onKeyActions.add(playermove);
    onKeyActions.add(door_pose);

    onClickActions.add(player_attack);

    this.outer = outer;
  }

  // Update the Room, compare if the room is the same as the saved one (currentName)
  // If they are different it will update the list of Action regarding the current room
  // NOTE: The order matter, First in the List, Last executed (e.g. we want the NegPlayer to Move
  // before the Player (but on the same timing action))
  private void updateRoomKey() {
    String name = outer.getMap().name();
    if (name != currentNameClick) {
      onKeyActions = onKeyActions.subList(onKeyActions.size() - 2, onKeyActions.size());
      switch (name) {
        case "HUB":
          break;
        case "MAIN":
          break;
        case "PUIS":
          onKeyActions.add(0, gameover);
          onKeyActions.add(0, red_pose);
          onKeyActions.add(0, button_pose);
          break;
        case "POKE":
          onKeyActions.add(0, bulkilled);
          break;
        case "RPG":
          onKeyActions.add(0, gameover);
          onKeyActions.add(0, negplayermove);
          onKeyActions.add(0, negkilled);
          break;
        case "ZOO":
          onKeyActions.add(0, button_pose);
          break;
        case "FINAL":
          break;
        default:
          break;
      }
      currentNameClick = name;
    }
  }

  // Same as above but for the click action, the order also matter, it's why when the List Actions
  // is reset we remove only all but we keep the last element in the List
  private void updateRoomClick() {
    String name = outer.getMap().name();
    if (name != currentName) {
      onClickActions = onClickActions.subList(onClickActions.size() - 1, onClickActions.size());
      switch (name) {
        case "HUB":
          break;
        case "MAIN":
          break;
        case "PUIS":
          break;
        case "POKE":
          break;
        case "RPG":
          onClickActions.add(0, gameover);
          onClickActions.add(0, negplayerattack);
          break;
        case "ZOO":
          break;
        case "FINAL":
          break;
        default:
          break;
      }
      currentName = name;
    }
  }

  // Execute each action in the List, we also give the Coordinate and the Stage for the Actions that
  // need them.
  public void updateActions(List<Action> actions, Stage s, int x, int y) {
    updateRoomKey();
    for (Action action : actions) {
      if (action instanceof toDefineCoord) {
        ((toDefineCoord) action).setCoord(x, y);
      }
      if (action instanceof toDefineStage) {
        ((toDefineStage) action).setStage(s);
      }
      action.update();
    }
  }

  // When a key is pressed, update the Room, update the Actions
  public void onArrowPress(Stage s, int x, int y) {
    updateRoomKey();
    updateActions(onKeyActions, s, x, y);
  }

  // Same for the mouse, we also want to know if the click came from the TOP, BOT, RIGHT, LEFT from
  // the Player
  public void onMouseClick(Stage s, int x, int y) {
    updateRoomClick();
    Player player = outer.getMap().getRoom().getPlayer();
    int px = player.getX();
    int py = player.getY();
    int dx = (x - px) * (x - px);
    int dy = (y - py) * (y - py);
    if ((y < py) && (dy > dx)) {
      updateActions(onClickActions, s, px, py - 1);
    } else if ((y > py) && (dy > dx)) {
      updateActions(onClickActions, s, px, py + 1);
    } else if ((x > px) && (dx >= dy)) {
      updateActions(onClickActions, s, px + 1, py);
    } else {
      updateActions(onClickActions, s, px - 1, py);
    }
  }
}

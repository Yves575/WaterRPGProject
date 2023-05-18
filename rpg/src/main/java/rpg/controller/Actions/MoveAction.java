package rpg.controller.Actions;

import rpg.UI.Renderer;
import rpg.controller.RpgEngine;
import rpg.model.Blocks.Block;
import rpg.model.Blocks.Door;
import rpg.model.Entities.Entity;
import rpg.model.Entities.Player;
import rpg.model.Rooms.Room;

// Aim to make Move the Player or an Entity
public class MoveAction implements Action, toDefineCoord {

  private int toX;
  private int toY;
  private boolean moving;
  private Renderer renderer;
  private Entity entity;
  private RpgEngine outer;
  private boolean direct = false;

  public MoveAction(Renderer renderer, Entity entity, RpgEngine outer) {
    this.renderer = renderer;
    this.entity = entity;
    this.outer = outer;
  }

  @Override
  // (x,y) is either (0,1) or (1,0), it indicate in which sense the Entity must go, therefore we add
  // it to its current coordinate
  public void setCoord(int x, int y) {
    if (!(direct)) {
      toX = entity.getX() + x;
      toY = entity.getY() + y;
    }
  }

  // We can also use DirectCoord if we want a specific direction in the Room
  public void DirectCoord(int x, int y) {
    toX = x;
    toY = y;
    direct = true;
  }

  @Override
  public void update() {
    Room room = outer.getMap().getRoom();
    // First, we have to check if the destination is in the grid
    if ((0 <= toX)
        && (toX < room.getGrid().length)
        && (0 <= toY)
        && (toY < room.getGrid().length)) {
      if ((room.getGrid()[toY][toX] instanceof Block)) {
        if (!((Block) room.getGrid()[toY][toX]).isObstruct()) {
          // We check true if the Block is not obstruct (case if the destination is a block)
          moving = true;
        }
      }
      if ((room.getGrid()[toY][toX] == null) && (entity.isAlive())) {
        // Also check if the Entity is still alive and the destination is null
        moving = true;
      }
      if (moving) {
        // Two case when we move. Some Block are not Obstruct meaning that we can walk on it.
        // However our grid is in 2D and we don't want to erase the Block with our Player. Thus, on
        // 1. Before executing the movement, we 1) Release the Block saved by the Entity 2)Save the
        // next Block 3)Set the next grid position as Entity
        // The Second case 2. appends when a Player use a Door, here we don't want to set the
        // grid[y][x] as Player as he will leave, but we release the last handled Block
        if ((room.getGrid()[toY][toX] instanceof Door) && (entity instanceof Player)) {
          // 2.
          room.setGrid(null, entity.getX(), entity.getY());
          entity.setBlock((Block) room.getGrid()[toY][toX]);
        } else {
          // 1.
          room.setGrid(entity.getBlock(), entity.getX(), entity.getY());
          entity.setBlock((Block) room.getGrid()[toY][toX]);
          room.setGrid(entity, toX, toY);
        }
      }
    }
    // Perform the Move Action (from a depart to an arrival)
    while (moving) {
      renderer.removeEntityRender(entity);
      int sens;
      sens = 1;
      if (entity.getX() > toX) {
        sens = -1;
      }
      if (entity.getX() != toX) {
        entity.setX(entity.getX() + sens);
      }
      sens = 1;
      if (entity.getY() > toY) {
        sens = -1;
      }
      if (entity.getY() != toY) {
        entity.setY(entity.getY() + sens);
      }
      if ((entity.getX() == toX) && (entity.getY() == toY)) {
        moving = false;
      }

      renderer.setEntityRender(entity);
    }
  }
}

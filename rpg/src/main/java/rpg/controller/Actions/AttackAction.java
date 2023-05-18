package rpg.controller.Actions;

import rpg.UI.Renderer;
import rpg.controller.RpgEngine;
import rpg.model.Entities.Entity;
import rpg.model.Entities.NegPlayer;

// AttackAction Class, aim to let the given Entity hit, it checks if another Entity has been hit,
// and do damage if so.
// Notice, this Class need to define the Coordinate of the hit impact
public class AttackAction implements Action, toDefineCoord {

  private RpgEngine outer;
  private Entity attacker;
  private int x;
  private int y;
  private Renderer renderer;
  private boolean coordAlreadyDefine = false;

  // Initiate with coordinate given by the click key
  public AttackAction(Renderer renderer, Entity attacker, RpgEngine outer) {
    this.renderer = renderer;
    this.attacker = attacker;
    this.outer = outer;
  }
  // Initiate with coordinate given directly by the constructor.
  public AttackAction(Renderer renderer, Entity attacker, RpgEngine outer, int x, int y) {
    this.renderer = renderer;
    this.attacker = attacker;
    this.outer = outer;
    this.x = x;
    this.y = y;
    coordAlreadyDefine = true;
  }

  // Check if it hit an Entity, then check if this Entity is still Alive
  @Override
  public void update() {
    Object[][] grid = outer.getMap().getRoom().getGrid();
    renderer.setHitAnimation(x, y);
    if (grid[y][x] instanceof Entity) {
      ((Entity) grid[y][x]).takeDamage(attacker.getDamage());
      if (!((Entity) grid[y][x]).isAlive()) {
        renderer.removeEntityRender((Entity) grid[y][x]);
        outer.getMap().getRoom().getEntities().remove((Entity) grid[y][x]);
        outer.getMap().getRoom().setGrid(null, x, y);
      }
    }
  }

  // We set the Attack Coordinate depending of the Player Click, we added a special case for the
  // NegativePlayer that must hit the same direction (but not same position) as the Player.
  @Override
  public void setCoord(int x, int y) {
    if ((!(attacker instanceof NegPlayer)) || (coordAlreadyDefine)) {
      this.x = x;
      this.y = y;
    } else {
      int px = outer.getMap().getRoom().getPlayer().getX();
      int py = outer.getMap().getRoom().getPlayer().getY();
      if (x == px) {
        if (y < py) {
          this.y = attacker.getY() - 1;
        } else {
          this.y = attacker.getY() + 1;
        }
        this.x = attacker.getX();
      }
      if (y == py) {
        if (x < px) {
          this.x = attacker.getX() + 1;
        } else {
          this.x = attacker.getX() - 1;
        }
        this.y = attacker.getY();
      }
    }
  }
}

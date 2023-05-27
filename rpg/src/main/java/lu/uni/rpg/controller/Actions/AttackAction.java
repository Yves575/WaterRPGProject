package lu.uni.rpg.controller.Actions;


import lu.uni.rpg.RPG;
import lu.uni.rpg.UI.Renderer;
import lu.uni.rpg.controller.RpgEngine;
import lu.uni.rpg.event.events.EntityDeathEvent;
import lu.uni.rpg.event.events.PlayerAttackEntityEvent;
import lu.uni.rpg.model.Entities.Entity;
import lu.uni.rpg.model.Entities.NegPlayer;
import lu.uni.rpg.model.Entities.Player;

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
        Object[][] grid = outer.getMap().getRoom().getEntityGrid();
        renderer.setHitAnimation(x, y);
        Player player = outer.getMap().getRoom().getPlayer();
        boolean playerIsAttacker = player.asEntity().equals(attacker);

        if (grid[y][x] instanceof Entity) {
            Entity e = (Entity) grid[y][x];
            int damage = attacker.getDamage();
            e.takeDamage(damage);

            if (!e.isAlive()) {
                if(playerIsAttacker) {
                    RPG.getInstance().getEngine().getEventManager().callEvent(new PlayerAttackEntityEvent((Player) attacker, e, true, damage));
                }

                e.kill();

                RPG.getInstance().getEngine().getEventManager().callEvent(new EntityDeathEvent(e, attacker));
            } else {
                if(playerIsAttacker) {
                    RPG.getInstance().getEngine().getEventManager().callEvent(new PlayerAttackEntityEvent((Player) attacker, e, false, damage));
                }
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
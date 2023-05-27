package lu.uni.rpg.event.events;

import lu.uni.rpg.event.Event;
import lu.uni.rpg.model.Entities.Entity;
import lu.uni.rpg.model.Entities.Player;

public class PlayerAttackEntityEvent extends Event {
    private Player player;
    private Entity attackedEntity;
    private boolean isEntityDead;
    private int damage;

    public PlayerAttackEntityEvent(Player player, Entity attackedEntity, boolean isEntityDead, int damage) {
        this.player = player;
        this.attackedEntity = attackedEntity;
        this.isEntityDead = isEntityDead;
        this.damage = damage;
    }

    public Player getPlayer() {
        return player;
    }

    public Entity getAttackedEntity() {
        return attackedEntity;
    }

    public boolean isEntityDead() {
        return isEntityDead;
    }

    public int getDamage() {
        return damage;
    }
}

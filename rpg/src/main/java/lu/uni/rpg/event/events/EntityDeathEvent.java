package lu.uni.rpg.event.events;

import lu.uni.rpg.event.Event;
import lu.uni.rpg.model.Entities.Entity;

/**
 * This event is kind of useless because PlayerAttackEvent exists, however it's still here for the sake of consistency,
 * it's also a bit more lightweight
 */
@Deprecated // in favor of PlayerAttackEvent
public class EntityDeathEvent extends Event {
    private Entity entity;
    private Entity attacker;

    public EntityDeathEvent(Entity entity, Entity attacker) {
        this.entity = entity;
        this.attacker = attacker;
    }

    public Entity getEntity() {
        return entity;
    }
    public Entity  getAttacker() { return attacker; }
}

package lu.uni.rpg.event.events;

import lu.uni.rpg.event.Event;
import lu.uni.rpg.model.Blocks.Door;
import lu.uni.rpg.model.Entities.Entity;

public class EntityEnterDoorEvent extends Event {
    private Door door;
    private Entity entity;

    public EntityEnterDoorEvent(Entity entity, Door door) {
        this.door = door;
        this.entity = entity;
    }

    public Door getEnteredDoor() {
        return door;
    }
    public Entity getEntity() {
        return entity;
    }
}

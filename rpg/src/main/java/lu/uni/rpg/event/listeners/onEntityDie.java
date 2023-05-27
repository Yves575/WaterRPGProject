package lu.uni.rpg.event.listeners;

import lu.uni.rpg.event.EventHandler;
import lu.uni.rpg.event.EventManager;
import lu.uni.rpg.event.Listener;
import lu.uni.rpg.event.events.CustomEvent;
import lu.uni.rpg.event.events.EntityDeathEvent;
import lu.uni.rpg.event.events.EntityEnterDoorEvent;
import lu.uni.rpg.model.Blocks.Door;
import lu.uni.rpg.model.Entities.Bulbasaur;
import lu.uni.rpg.model.Entities.Player;
import lu.uni.rpg.model.Map;

public class onEntityDie implements Listener {

    private EventManager eventManager;

    public onEntityDie(EventManager eventManager) {
        this.eventManager = eventManager;
        eventManager.registerEvents(this);
    }

    @EventHandler
    public void onEntityDie(EntityDeathEvent event) {
        if(event.getEntity() instanceof Player) {
            // Respawn player in starting room
            Door hubDoor = new Door(-1, -1);
            hubDoor.setLinkedRoomName("HUB");

            eventManager.callEvent(new EntityEnterDoorEvent(event.getEntity(), hubDoor)); // Respawn trick
        }

        if(event.getEntity() instanceof Bulbasaur) {
            // Unlock door
            ((Door) (Map.MAIN.getRoom().getGrid()[3][10])).setObstruct(false);
        }
    }
}

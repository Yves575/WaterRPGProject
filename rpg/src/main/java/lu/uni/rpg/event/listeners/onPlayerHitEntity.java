package lu.uni.rpg.event.listeners;

import lu.uni.rpg.event.EventHandler;
import lu.uni.rpg.event.EventManager;
import lu.uni.rpg.event.Listener;
import lu.uni.rpg.event.events.PlayerAttackEntityEvent;

public class onPlayerHitEntity implements Listener {
    private EventManager eventManager;

    public onPlayerHitEntity(EventManager eventManager) {
        this.eventManager = eventManager;
        eventManager.registerEvents(this);
    }

    @EventHandler
    public void onPlayerHitEntity(PlayerAttackEntityEvent event) {
        System.out.println("Player hit entity for " + event.getDamage() + " damage, the entity did " + (event.isEntityDead() ? "die" : "not die"));
    }
}

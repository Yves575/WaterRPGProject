package lu.uni.rpg.event.listeners;

import lu.uni.rpg.event.EventHandler;
import lu.uni.rpg.event.EventManager;
import lu.uni.rpg.event.Listener;
import lu.uni.rpg.event.events.CustomEvent;
import lu.uni.rpg.event.events.MapChangeEvent;

public class onPlayerChangeMap implements Listener {
    private EventManager eventManager;

    public onPlayerChangeMap(EventManager eventManager) {
        this.eventManager = eventManager;
        eventManager.registerEvents(this);
    }

    @EventHandler
    public void onPlayerChangeMap(MapChangeEvent event) {
        System.out.println("Player changed map from " + event.getOldMap() + " to " + event.getNewMap() + "!");
    }
}

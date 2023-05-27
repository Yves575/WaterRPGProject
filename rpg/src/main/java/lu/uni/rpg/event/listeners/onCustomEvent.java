package lu.uni.rpg.event.listeners;

import lu.uni.rpg.event.EventHandler;
import lu.uni.rpg.event.EventManager;
import lu.uni.rpg.event.Listener;
import lu.uni.rpg.event.events.CustomEvent;

public class onCustomEvent implements Listener {
    private EventManager eventManager;

    public onCustomEvent(EventManager eventManager) {
        this.eventManager = eventManager;
        eventManager.registerEvents(this);
    }

    @EventHandler
    public void onCustomEvent(CustomEvent event) {
        String eventData = event.getEventData();
        // Handle the custom event
        System.out.println("Custom Event received: " + eventData);
    }
}

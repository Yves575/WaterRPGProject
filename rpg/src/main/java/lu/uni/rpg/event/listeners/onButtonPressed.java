package lu.uni.rpg.event.listeners;

import lu.uni.rpg.event.EventHandler;
import lu.uni.rpg.event.EventManager;
import lu.uni.rpg.event.Listener;
import lu.uni.rpg.event.events.ButtonPressEvent;
import lu.uni.rpg.event.events.CustomEvent;
import lu.uni.rpg.model.Blocks.Button;

public class onButtonPressed implements Listener {

    private EventManager eventManager;

    public onButtonPressed(EventManager eventManager) {
        this.eventManager = eventManager;
        eventManager.registerEvents(this);
    }

    @EventHandler
    public void onCustomEvent(ButtonPressEvent event) {
        Button button = event.getButton();

        System.out.println("Button pressed");
    }

}

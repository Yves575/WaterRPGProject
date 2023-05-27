package lu.uni.rpg.event.events;

import lu.uni.rpg.event.Event;

public class CustomEvent extends Event {
    private String eventData;

    public CustomEvent(String eventData) {
        this.eventData = eventData;
    }

    public String getEventData() {
        return eventData;
    }
}

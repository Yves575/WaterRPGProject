package lu.uni.rpg.event.events;

import lu.uni.rpg.event.Event;

public class ValidatedMoveEvent extends Event {
    private PlayerMoveEvent originalEvent;

    public ValidatedMoveEvent(PlayerMoveEvent originalEvent) {
        this.originalEvent = originalEvent;
    }

    public PlayerMoveEvent getOriginalEvent() {
        return originalEvent;
    }
}

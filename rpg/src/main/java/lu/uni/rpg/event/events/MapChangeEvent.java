package lu.uni.rpg.event.events;

import lu.uni.rpg.event.Event;
import lu.uni.rpg.model.Map;

public class MapChangeEvent extends Event {

    private Map oldMap;
    private Map newMap;

    public MapChangeEvent(Map oldMap, Map newMap) {
        this.oldMap = oldMap;
        this.newMap = newMap;
    }

    public String getNewMap() {
        return newMap.toString();
    }

    public String getOldMap() {
        return oldMap.toString();
    }

}

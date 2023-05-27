package lu.uni.rpg.event.listeners;

import lu.uni.rpg.RPG;
import lu.uni.rpg.controller.RpgEngine;
import lu.uni.rpg.event.EventHandler;
import lu.uni.rpg.event.EventManager;
import lu.uni.rpg.event.Listener;
import lu.uni.rpg.event.events.EntityEnterDoorEvent;
import lu.uni.rpg.model.Blocks.Door;
import lu.uni.rpg.model.Entities.Entity;
import lu.uni.rpg.model.Entities.Player;

import java.util.HashMap;
import java.util.Map;

public class onEntityEnterDoor implements Listener {

    private EventManager eventManager;

    public onEntityEnterDoor(EventManager eventManager) {
        this.eventManager = eventManager;
        eventManager.registerEvents(this);
    }

    @EventHandler
    public void onEntityEnterDoor(EntityEnterDoorEvent event) {
        Door enteredDoor = event.getEnteredDoor();
        Entity entity = event.getEntity();
        String roomName = enteredDoor.getLinkedRoomName();

        Map<String, int[]> roomPositions = new HashMap<>();
        roomPositions.put("HUB", new int[]{5, 9});
        roomPositions.put("MAIN", new int[]{5, 9});
        roomPositions.put("PUIS", new int[]{9, 6});
        roomPositions.put("ZOO", new int[]{1, 6});
        roomPositions.put("POKE", new int[]{9, 3});
        roomPositions.put("RPG", new int[]{1, 3});
        roomPositions.put("FINAL", new int[]{5, 9});

        int[] defaultPosition = new int[]{3, 3};

        int[] position = roomPositions.getOrDefault(roomName, defaultPosition);

        RpgEngine outer = RPG.getInstance().getEngine();

        if(outer.getMap().getRoom().hasEntity(entity)) {
            outer.getMap().getRoom().removeEntity(entity); // Remove the entity from the current room
        }
        lu.uni.rpg.model.Map chosenMap = lu.uni.rpg.model.Map.valueOf(roomName); // Get the map from the enum

        outer.setMap(chosenMap); // Set the map to the chosen one
        entity.setRoom(outer.getMap().getRoom());
        entity.setX(position[0]);
        entity.setY(position[1]);

        outer.getMap().getRoom().setPlayer((Player) entity);
        outer.getRenderer().setRoom(outer.getMap().getRoom());
        outer.render(RPG.getInstance().getEngine().getController().getStage());
    }

}

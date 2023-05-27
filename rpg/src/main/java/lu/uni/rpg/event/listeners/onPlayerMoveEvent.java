package lu.uni.rpg.event.listeners;

import lu.uni.rpg.RPG;
import lu.uni.rpg.UI.Renderer;
import lu.uni.rpg.controller.RpgEngine;
import lu.uni.rpg.event.EventHandler;
import lu.uni.rpg.event.EventManager;
import lu.uni.rpg.event.Listener;
import lu.uni.rpg.event.events.EntityEnterDoorEvent;
import lu.uni.rpg.event.events.PlayerMoveEvent;
import lu.uni.rpg.event.events.ValidatedMoveEvent;
import lu.uni.rpg.model.Blocks.Block;
import lu.uni.rpg.model.Blocks.Button;
import lu.uni.rpg.model.Blocks.Door;
import lu.uni.rpg.model.Blocks.PuisRed;
import lu.uni.rpg.model.Entities.Entity;
import lu.uni.rpg.model.Entities.Player;
import lu.uni.rpg.model.Map;
import lu.uni.rpg.model.Rooms.Room;

/**
 * This class contains the core logic for player movement. It is also responsible for dispatching the
 * {@link ValidatedMoveEvent} and {@link EntityEnterDoorEvent} events.
 */
public class onPlayerMoveEvent implements Listener {
    private EventManager eventManager;
    private boolean moving;
    private Renderer renderer;
    private Entity entity;
    private RpgEngine outer;
    private boolean direct = false;

    public onPlayerMoveEvent(EventManager eventManager, RpgEngine engine) {
        this.eventManager = eventManager;
        this.outer = engine;
        renderer = engine.getRenderer();
        eventManager.registerEvents(this);
    }

    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent event) {
        int toX = event.getNewX();
        int toY = event.getNewY();
        this.entity = event.getPlayer().asEntity();
        System.out.println("Player moved from " + event.getOldX() + "," + event.getOldY() + " to " + event.getNewX() + "," + event.getNewY() + "! " + event.hasChangedRoom());

        Room room = outer.getMap().getRoom();
        int gridLength = room.getGrid().length;

        if (isValidDestination(toX, toY, gridLength)) {
            Block destinationBlock = (Block) room.getGrid()[toY][toX];
            Entity placedEntity = (Entity) room.getEntityGrid()[toY][toX];
            if(placedEntity != null) {
                return;
            }

            if(destinationBlock instanceof Block) {
                if(destinationBlock.isObstruct()) {
                    event.setCancelled(true);
                    return;
                }

                this.eventManager.callEvent(new ValidatedMoveEvent(event));

                if (destinationBlock instanceof Door && entity instanceof Player) {
                    System.out.println(entity.getRoom());
                    eventManager.callEvent(new EntityEnterDoorEvent(entity, (Door) entity.getRoom().getBlockAt(toX, toY)));
                    event.setCancelled(true); // The player gets teleported so he doesn't truly move
                    return;
                }

                if(destinationBlock instanceof PuisRed) {
                    entity.kill();
                    return;
                }

                if(destinationBlock instanceof Button) {
                    Button destinationBtn = (Button) destinationBlock;
                    destinationBtn.setPressed(true);
                    ((Door) (Map.MAIN.getRoom().getGrid()[destinationBtn.getToY()][destinationBtn.getToX()])).setObstruct(!destinationBtn.getPressed());
                }
            }

            if(!event.isCancelled()) {
                renderer.removeEntityRender(entity);
                entity.getRoom().moveEntity(entity, toX, toY);
                renderer.setEntityRender(entity);
            }
        }
    }

    private boolean isValidDestination(int x, int y, int gridLength) {
        return 0 <= x && x < gridLength && 0 <= y && y < gridLength;
    }

    private boolean isLocationObstructed(Block block) {
        return block != null && !((Block) block).isObstruct();
    }

}

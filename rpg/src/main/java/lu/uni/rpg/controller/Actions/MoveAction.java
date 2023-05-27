package lu.uni.rpg.controller.Actions;


import lu.uni.rpg.UI.Renderer;
import lu.uni.rpg.controller.RpgEngine;
import lu.uni.rpg.model.Blocks.Block;
import lu.uni.rpg.model.Blocks.Door;
import lu.uni.rpg.model.Entities.Entity;
import lu.uni.rpg.model.Entities.Player;
import lu.uni.rpg.model.Rooms.Room;

// Aim to make Move the Player or an Entity
@Deprecated
public class MoveAction implements Action, toDefineCoord {

    private int toX;
    private int toY;
    private boolean moving;
    private Renderer renderer;
    private Entity entity;
    private RpgEngine outer;
    private boolean direct = false;

    public MoveAction(Renderer renderer, Entity entity, RpgEngine outer) {
        this.renderer = renderer;
        this.entity = entity;
        this.outer = outer;
    }

    @Override
    // (x,y) is either (0,1) or (1,0), it indicate in which sense the Entity must go, therefore we add
    // it to its current coordinate
    public void setCoord(int x, int y) {
        if (!(direct)) {
            toX = entity.getX() + x;
            toY = entity.getY() + y;
        }
    }

    // We can also use DirectCoord if we want a specific direction in the Room
    public void DirectCoord(int x, int y) {
        toX = x;
        toY = y;
        direct = true;
    }

    @Override
    public void update() {
        Room room = outer.getMap().getRoom();
        int gridLength = room.getGrid().length;

        if (isValidDestination(toX, toY, gridLength)) {
            Block destinationBlock = (Block) room.getGrid()[toY][toX];
            boolean moving = false;

            if (destinationBlock instanceof Block && !((Block) destinationBlock).isObstruct()) {
                moving = true;
            } else if (destinationBlock == null && entity.isAlive()) {
                moving = true;
            }

            if (moving) {
                if (destinationBlock instanceof Door && entity instanceof Player) {
                    room.setGrid(null, entity.getX(), entity.getY(), true);
                    entity.setBlock((Block) destinationBlock);
                } else {
                    Block currentBlock = entity.getBlock();
                    room.setGrid(currentBlock, entity.getX(), entity.getY(), true);
                    entity.setBlock((Block) destinationBlock);
                    room.setGrid(entity, toX, toY, true);
                }
            }

            while (moving) {
                renderer.removeEntityRender(entity);
                int xDirection = entity.getX() > toX ? -1 : 1;
                int yDirection = entity.getY() > toY ? -1 : 1;

                if (entity.getX() != toX) {
                    entity.setX(entity.getX() + xDirection);
                }
                if (entity.getY() != toY) {
                    entity.setY(entity.getY() + yDirection);
                }

                if (entity.getX() == toX && entity.getY() == toY) {
                    moving = false;
                }

                renderer.setEntityRender(entity);
            }
        }
    }

    private boolean isValidDestination(int x, int y, int gridLength) {
        return 0 <= x && x < gridLength && 0 <= y && y < gridLength;
    }
}
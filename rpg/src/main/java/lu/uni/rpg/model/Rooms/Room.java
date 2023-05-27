package lu.uni.rpg.model.Rooms;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.SubScene;
import lu.uni.rpg.RPG;
import lu.uni.rpg.controller.Actions.Action;
import lu.uni.rpg.controller.Actions.RPGAction;
import lu.uni.rpg.model.Blocks.Block;
import lu.uni.rpg.model.Blocks.Fence;
import lu.uni.rpg.model.Blocks.Wall;
import lu.uni.rpg.model.Entities.Entity;
import lu.uni.rpg.model.Entities.Player;

// Class of each Room
public abstract class Room {
    protected static final int WIDTH = 11;
    protected static final int HEIGHT = 11;

    protected Object[][] grid;
    protected Object[][] entityGrid;
    protected List<Entity> entities = new ArrayList<>();
    protected Player player;
    private Block cachedBlock;

    // Create a grid of 'null' values (->Floor) and set the Wall in the border.
    public Room() {
        this.grid = new Object[WIDTH][HEIGHT];
        this.entityGrid = new Object[WIDTH][HEIGHT];
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if ((i == 0) || (j == 0) || (i == WIDTH - 1) || (j == HEIGHT - 1)) {
                    grid[i][j] = new Wall(j, i);
                } else {
                    grid[i][j] = null;
                }
            }
        }
    }

    public Object[][] getGrid() {
        return grid;
    }

    public Object[][] getEntityGrid() {
        return entityGrid;
    }

    public void setGrid(Object o, int x, int y, boolean isEntity) {
        if(isEntity) {
            this.entityGrid[y][x] = o;
        } else {
            this.grid[y][x] = o;
        }
    }

    // Set the Player
    public void setPlayer(Player player) {
        this.player = player;
        //grid[this.player.getY()][this.player.getX()] = this.player;
        this.entityGrid[this.player.getY()][this.player.getX()] = this.player;
        entities.add(player);
    }

    public Player getPlayer() {
        return player;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public abstract String toString();

    // Summon Entity
    public void summonEntity(Entity entity, int x, int y) {
        this.entityGrid[x][y] = entity;
        getEntities().add(entity);
    }

    // Remove Entity from room
    public void removeEntity(Entity entity) {
        RPG.getInstance().getEngine().getRenderer().removeEntityRender(entity);
        entities.remove(entity);
        setGrid(null, entity.getX(), entity.getY(), true);
    }

    // Move an entity's X-Y, seems to automatically re-render the room
    public void moveEntity(Entity entity, int x, int y) {
        setGrid(null, entity.getX(), entity.getY(), true);
        setGrid(entity, x, y, true);

        entity.setX(x);
        entity.setY(y);
    }

    // Set a block at X/Y
    public void setBlock(Block block, int x, int y) {
        setGrid(block, x, y, false);
    }

    // utility method to get the entity at a given position
    public Entity getEntityAt(int x, int y) {
        for (Entity entity : entities) {
            if (entity.getX() == x && entity.getY() == y) {
                return entity;
            }
        }
        return null;
    }

    public Block getBlockAt(int x, int y) {
        if(grid[y][x] instanceof Block) {
            return (Block) grid[y][x];
        } else {
            return null;
        }
    }

    public boolean hasEntity(Entity entity) {
        return entities.contains(entity);
    }

}
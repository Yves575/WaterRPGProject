package lu.uni.rpg.model.Entities;

import lu.uni.rpg.model.Inventories.PlayerInventory;
import lu.uni.rpg.model.Rooms.Room;

// Class of the Player
public class Player extends Entity {

    private PlayerInventory inventory = new PlayerInventory();

    public Player(int x, int y, Room room) {
        super(x, y, room);
        damage = 1;
        hp = 3;
    }

    @Override
    public String getTexture() {
        return "sprites/player.png";
    }

    public PlayerInventory getInventory() {
        return inventory;
    }

    public Entity asEntity() {
        return (Entity) this;
    }
}
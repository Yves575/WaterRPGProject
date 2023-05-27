package lu.uni.rpg.model.Entities;

import lu.uni.rpg.model.Rooms.Room;

// Class of the Little sister
public class Little extends Entity {

    public Little(int x, int y, Room room) {
        super(x, y, room);
        damage = 0;
        hp = 1;
    }

    @Override
    public String getTexture() {
        return "sprites/little.png";
    }
}
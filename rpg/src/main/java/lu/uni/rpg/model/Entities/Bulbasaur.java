package lu.uni.rpg.model.Entities;

import lu.uni.rpg.model.Rooms.Room;

// Class of Bulbasaur
public class Bulbasaur extends Entity {

    public Bulbasaur(int x, int y, Room room) {
        super(x, y, room);
        damage = 0;
        hp = 1;
    }

    @Override
    public String getTexture() {
        return "sprites/pixel-bulbasaur.png";
    }
}
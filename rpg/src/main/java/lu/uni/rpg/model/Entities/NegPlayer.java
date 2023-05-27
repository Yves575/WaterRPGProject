package lu.uni.rpg.model.Entities;

import lu.uni.rpg.model.Rooms.Room;

// Class of the NegativePlayer
public class NegPlayer extends Entity {

    public NegPlayer(int x, int y, Room room) {
        super(x, y, room);
        damage = 99;
        hp = 1;
    }

    @Override
    public String getTexture() {
        return "sprites/neg_player.png";
    }
}
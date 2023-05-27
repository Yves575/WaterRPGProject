package lu.uni.rpg.model.Entities;

import lu.uni.rpg.model.Rooms.Room;

public class FakeFence extends Entity {

    public FakeFence(int x, int y, Room room) {
        super(x, y, room);
        damage = 0;
        hp = 1;
    }

    @Override
    public String getTexture() {
        return "sprites/fake-fence.png";
    }
}
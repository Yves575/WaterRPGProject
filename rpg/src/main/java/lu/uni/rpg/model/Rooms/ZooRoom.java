package lu.uni.rpg.model.Rooms;

import lu.uni.rpg.model.Blocks.Button;
import lu.uni.rpg.model.Blocks.Door;
import lu.uni.rpg.model.Blocks.Fence;
import lu.uni.rpg.model.Entities.*;

// Define the Zoo Room
public class ZooRoom extends Room {
    public ZooRoom() {
        super();
        // Set the Fences, Lion, little sister
        Door door = new Door(0, 6);
        this.setBlock(door, 0, 6);
        door.setLinkedRoomName("MAIN");

        Button button = new Button(2, 2);
        this.setBlock(button, 2, 2);
        button.setLinkedDoor(0, 3);

        for (int i = 1; i < 10; i++) {
            if (i == 7) {
                this.summonEntity(new FakeFence(i, 5, this), 5, i);
            } else {
                this.setBlock(new Fence(i, 5), i, 5);
            }
        }

        this.summonEntity(new Little(7, 7, this), 7, 7);
        this.summonEntity(new Lion(7, 3, this), 3, 7);
    }

    @Override
    public String toString() {
        return "Zoo Room";
    }
}
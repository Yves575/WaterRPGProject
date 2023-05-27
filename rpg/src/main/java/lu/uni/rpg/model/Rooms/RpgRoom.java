package lu.uni.rpg.model.Rooms;

import lu.uni.rpg.model.Blocks.Door;
import lu.uni.rpg.model.Blocks.Wall;
import lu.uni.rpg.model.Entities.Lion;
import lu.uni.rpg.model.Entities.NegPlayer;
import lu.uni.rpg.model.Rooms.Room;

// Define RPG Room
public class RpgRoom extends Room {
    public RpgRoom() {
        super();
        // Define Door, Wall and the Negative Player Entity
        grid[3][0] = new Door(0, 3);
        ((Door) grid[3][0]).setLinkedRoomName("MAIN");

        grid[1][1] = new Wall(1, 1);
        grid[9][9] = new Wall(9, 9);
        grid[9][1] = new Wall(1, 9);
        grid[1][9] = new Wall(9, 1);

        //grid[3][9] = new NegPlayer(9, 3);
        //getEntities().add((Entity) grid[3][9]);

        NegPlayer lion = new NegPlayer(9, 3, this);
        this.summonEntity(lion, 3, 9);
    }

    @Override
    public String toString() {
        return "Rpg Room";
    }
}
package rpg.model.Rooms;

import rpg.model.Blocks.Button;
import rpg.model.Blocks.Door;
import rpg.model.Blocks.Fence;
import rpg.model.Entities.Entity;
import rpg.model.Entities.FakeFence;
import rpg.model.Entities.Lion;
import rpg.model.Entities.Little;

// Define the Zoo Room
public class ZooRoom extends Room {
  public ZooRoom() {
    super();
    // Set the Fences, Lion, little sister
    grid[6][0] = new Door(0, 6);
    ((Door) grid[6][0]).setLinkedRoomName("MAIN");

    grid[2][2] = new Button(2, 2);
    ((Button) grid[2][2]).setLinkedDoor(0, 3);

    for (int i = 1; i < 10; i++) {
      if (i == 7) {
        grid[5][i] = new FakeFence(i, 5);
        getEntities().add((Entity) grid[5][i]);
      } else {
        grid[5][i] = new Fence(i, 5);
      }
    }
    grid[7][7] = new Little(7, 7);
    grid[3][7] = new Lion(7, 3);
    getEntities().add((Entity) grid[7][7]);
    getEntities().add((Entity) grid[3][7]);
  }

  @Override
  public String toString() {
    return "Zoo Room";
  }
}

package rpg.model.Rooms;

import rpg.model.Blocks.Door;
import rpg.model.Blocks.Trophy;

// Class of the Final Room
public class FinalRoom extends Room {
  public FinalRoom() {
    super();
    // Define Door and Trophy
    grid[10][5] = new Door(5, 10);
    ((Door) grid[10][5]).setLinkedRoomName("MAIN");

    grid[4][5] = new Trophy(5, 4);
  }

  @Override
  public String toString() {
    return "Final Room";
  }
}

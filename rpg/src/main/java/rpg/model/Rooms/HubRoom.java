package rpg.model.Rooms;

import rpg.model.Blocks.Door;
// Class of the Hub Room

public class HubRoom extends Room {

  public HubRoom() {
    super();
    // Define Door
    grid[0][5] = new Door(5, 0);
    ((Door) grid[0][5]).setLinkedRoomName("MAIN");
  }

  @Override
  public String toString() {
    return "Hub Room";
  }
}

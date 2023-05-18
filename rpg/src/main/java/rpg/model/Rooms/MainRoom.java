package rpg.model.Rooms;

import rpg.model.Blocks.Door;
// Class of the Main Room

public class MainRoom extends Room {

  public MainRoom() {
    super();
    // Define the 6 Doors
    grid[0][5] = new Door(5, 0);
    ((Door) grid[0][5]).setLinkedRoomName("FINAL");
    ((Door) grid[0][5]).setObstruct(true);
    ;

    grid[6][0] = new Door(0, 6);
    ((Door) grid[6][0]).setLinkedRoomName("PUIS");

    grid[3][0] = new Door(0, 3);
    ((Door) grid[3][0]).setLinkedRoomName("POKE");
    ((Door) grid[3][0]).setObstruct(true);

    grid[3][10] = new Door(10, 3);
    ((Door) grid[3][10]).setLinkedRoomName("RPG");
    ((Door) grid[3][10]).setObstruct(true);

    grid[6][10] = new Door(10, 6);
    ((Door) grid[6][10]).setLinkedRoomName("ZOO");
    ((Door) grid[6][10]).setObstruct(true);

    grid[10][5] = new Door(5, 10);
    ((Door) grid[10][5]).setLinkedRoomName("HUB");
  }

  @Override
  public String toString() {
    return "Main Room";
  }
}

package rpg.model.Rooms;

import rpg.model.Blocks.Door;
import rpg.model.Entities.Bulbasaur;
import rpg.model.Entities.Entity;
// Class of the Pokemon Room

public class PokeRoom extends Room {
  public PokeRoom() {
    super();
    // Define Door and Pokemon
    grid[3][10] = new Door(10, 3);
    ((Door) grid[3][10]).setLinkedRoomName("MAIN");

    grid[5][5] = new Bulbasaur(5, 5);
    getEntities().add((Entity) grid[5][5]);
  }

  @Override
  public String toString() {
    return "Poke Room";
  }
}

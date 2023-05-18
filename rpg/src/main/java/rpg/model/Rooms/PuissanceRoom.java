package rpg.model.Rooms;

import rpg.model.Blocks.Button;
import rpg.model.Blocks.Door;
import rpg.model.Blocks.PuisEmpty;
import rpg.model.Blocks.PuisRed;
import rpg.model.Blocks.PuisYellow;
import rpg.model.Blocks.Wall;

// Puissance 4 Room
public class PuissanceRoom extends Room {
  public PuissanceRoom() {
    super();

    // Define the grid of the Puissance4
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 7; j++) {
        grid[3 + i][2 + j] = new PuisEmpty(j, i);
      }
    }

    grid[6][10] = new Door(10, 6);
    ((Door) grid[6][10]).setLinkedRoomName("MAIN");

    grid[4][3] = new PuisRed(3, 4);
    grid[4][6] = new PuisRed(6, 4);
    grid[4][4] = new PuisRed(4, 4);
    grid[3][3] = new PuisRed(3, 5);
    grid[3][5] = new PuisRed(5, 5);
    grid[3][6] = new PuisRed(6, 5);
    grid[6][2] = new PuisRed(2, 6);
    grid[6][5] = new PuisRed(5, 6);
    grid[6][6] = new PuisRed(6, 6);
    grid[7][4] = new PuisRed(4, 7);
    grid[7][8] = new PuisRed(8, 7);
    grid[8][2] = new PuisRed(2, 8);
    grid[8][6] = new PuisRed(6, 8);

    grid[3][5] = new PuisYellow(5, 3);
    grid[4][5] = new PuisYellow(5, 4);
    grid[5][4] = new PuisYellow(4, 5);
    grid[5][5] = new PuisYellow(5, 5);
    grid[6][3] = new PuisYellow(3, 6);
    grid[6][4] = new PuisYellow(4, 6);
    grid[6][8] = new PuisYellow(8, 6);
    grid[7][2] = new PuisYellow(2, 7);
    grid[7][3] = new PuisYellow(3, 7);
    grid[7][5] = new PuisYellow(5, 7);
    grid[7][6] = new PuisYellow(6, 7);
    grid[7][7] = new PuisYellow(7, 7);
    grid[8][3] = new PuisYellow(3, 8);
    grid[8][4] = new PuisYellow(4, 8);
    grid[8][5] = new PuisYellow(5, 8);
    grid[8][7] = new PuisYellow(7, 8);
    grid[8][8] = new PuisYellow(8, 8);

    // Define Button and Walls
    grid[2][5] = new Button(5, 2);
    ((Button) grid[2][5]).setLinkedDoor(10, 6);
    grid[1][1] = new Wall(1, 1);
    grid[2][9] = new Wall(9, 2);
    grid[2][1] = new Wall(1, 2);
    grid[1][9] = new Wall(9, 1);
    grid[9][1] = new Wall(1, 9);
    grid[9][6] = new Wall(6, 9);
    grid[9][9] = new Wall(9, 9);
    ;
  }

  @Override
  public String toString() {
    return "Puissance4 Room";
  }
}

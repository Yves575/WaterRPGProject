package rpg.model.Rooms;

import java.util.ArrayList;
import java.util.List;
import rpg.model.Blocks.Wall;
import rpg.model.Entities.Entity;
import rpg.model.Entities.Player;

// Class of each Room
public abstract class Room {
  protected static final int WIDTH = 11;
  protected static final int HEIGHT = 11;

  protected Object[][] grid;
  protected List<Entity> entities = new ArrayList<>();
  protected Player player;

  // Create a grid of 'null' values (->Floor) and set the Wall in the border.
  public Room() {
    this.grid = new Object[WIDTH][HEIGHT];
    for (int i = 0; i < WIDTH; i++) {
      for (int j = 0; j < HEIGHT; j++) {
        if ((i == 0) || (j == 0) || (i == WIDTH - 1) || (j == HEIGHT - 1)) {
          grid[i][j] = new Wall(j, i);
        } else {
          grid[i][j] = null;
        }
      }
    }
  }

  public Object[][] getGrid() {
    return grid;
  }

  public void setGrid(Object o, int x, int y) {
    this.grid[y][x] = o;
  }

  // Set the Player
  public void setPlayer(Player player) {
    this.player = player;
    grid[this.player.getY()][this.player.getX()] = this.player;
    entities.add(player);
  }

  public Player getPlayer() {
    return player;
  }

  public List<Entity> getEntities() {
    return entities;
  }

  public abstract String toString();
}

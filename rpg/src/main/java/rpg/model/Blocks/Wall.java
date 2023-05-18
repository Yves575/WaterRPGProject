package rpg.model.Blocks;

// Class of the Wall
public class Wall extends Block {

  public Wall(int x, int y) {
    super(x, y);
    Obstruct = true;
  }

  @Override
  public String getTexture() {
    return "sprites/wall-tile.png";
  }

  @Override
  public String getString() {
    return "Wall";
  }
}

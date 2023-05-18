package rpg.model.Blocks;

// Class of a Fence
public class Fence extends Block {

  public Fence(int x, int y) {
    super(x, y);
    Obstruct = true;
  }

  @Override
  public String getTexture() {
    return "sprites/fence-tile.png";
  }

  @Override
  public String getString() {
    return "Fence Block";
  }
}

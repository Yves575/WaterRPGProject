package rpg.model.Blocks;

// Class of the Puissance 4 Red Block
public class PuisRed extends Block {

  public PuisRed(int x, int y) {
    super(x, y);
    Obstruct = false;
  }

  @Override
  public String getTexture() {
    return "sprites/p4-red-tile.png";
  }

  @Override
  public String getString() {
    return "Puissance4 Red Block";
  }
}

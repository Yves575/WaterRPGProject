package rpg.model.Blocks;

// Class of the Puissance 4 Empty Block
public class PuisEmpty extends Block {

  public PuisEmpty(int x, int y) {
    super(x, y);
    Obstruct = true;
  }

  @Override
  public String getTexture() {
    return "sprites/p4-empty-tile.png";
  }

  @Override
  public String getString() {
    return "Puissance4 Block";
  }
}

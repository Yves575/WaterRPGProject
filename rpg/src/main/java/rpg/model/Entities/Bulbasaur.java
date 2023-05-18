package rpg.model.Entities;

// Class of Bulbasaur
public class Bulbasaur extends Entity {

  public Bulbasaur(int x, int y) {
    super(x, y);
    damage = 0;
    hp = 1;
  }

  @Override
  public String getTexture() {
    return "sprites/pixel-bulbasaur.png";
  }
}

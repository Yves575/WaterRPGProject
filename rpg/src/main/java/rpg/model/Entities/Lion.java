package rpg.model.Entities;

// Class of the Lion
public class Lion extends Entity {

  public Lion(int x, int y) {
    super(x, y);
    damage = 0;
    hp = 1;
  }

  @Override
  public String getTexture() {
    return "sprites/lion.png";
  }
}

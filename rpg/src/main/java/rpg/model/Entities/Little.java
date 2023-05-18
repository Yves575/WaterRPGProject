package rpg.model.Entities;

// Class of the Little sister
public class Little extends Entity {

  public Little(int x, int y) {
    super(x, y);
    damage = 0;
    hp = 1;
  }

  @Override
  public String getTexture() {
    return "sprites/little.png";
  }
}

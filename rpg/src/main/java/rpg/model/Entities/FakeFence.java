package rpg.model.Entities;

public class FakeFence extends Entity {

  public FakeFence(int x, int y) {
    super(x, y);
    damage = 0;
    hp = 1;
  }

  @Override
  public String getTexture() {
    return "sprites/fake-fence.png";
  }
}

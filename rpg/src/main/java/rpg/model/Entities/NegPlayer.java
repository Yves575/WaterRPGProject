package rpg.model.Entities;

// Class of the NegativePlayer
public class NegPlayer extends Entity {

  public NegPlayer(int x, int y) {
    super(x, y);
    damage = 99;
    hp = 1;
  }

  @Override
  public String getTexture() {
    return "sprites/neg_player.png";
  }
}

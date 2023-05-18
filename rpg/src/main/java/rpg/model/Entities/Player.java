package rpg.model.Entities;

// Class of the Player
public class Player extends Entity {

  public Player(int x, int y) {
    super(x, y);
    damage = 1;
    hp = 3;
  }

  @Override
  public String getTexture() {
    return "sprites/player.png";
  }
}

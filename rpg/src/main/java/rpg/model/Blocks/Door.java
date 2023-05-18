package rpg.model.Blocks;

// Class of a Door
public class Door extends Block {
  private String linkedRoomString;

  public Door(int x, int y) {
    super(x, y);
    Obstruct = false;
  }

  @Override
  public String getTexture() {
    if (Obstruct) {
      return "sprites/door-close-tile.png";
    } else return "sprites/door-open-tile.png";
  }

  public void setLinkedRoomName(String room) {
    this.linkedRoomString = room;
  }

  public String getLinkedRoomName() {
    return linkedRoomString;
  }

  @Override
  public String getString() {
    return "Door";
  }
}

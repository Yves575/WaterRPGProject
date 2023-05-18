package rpg.model.Blocks;

// Class of a Block
public abstract class Block {
  private int x;
  private int y;
  protected boolean Obstruct;

  public abstract String getTexture();

  public Block(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public abstract String getString();

  public boolean isObstruct() {
    return Obstruct;
  }

  public void setObstruct(boolean isObstruct) {
    this.Obstruct = isObstruct;
  }
}

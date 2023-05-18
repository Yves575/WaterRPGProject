package rpg.model.Entities;

import rpg.model.Blocks.Block;

// Class Entity
public abstract class Entity {
  protected int x;
  protected int y;
  protected int damage;
  protected int hp;
  protected Block onBlock = null;
  protected boolean alive = true;

  public Entity(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getDamage() {
    return damage;
  }

  public void setDamage(int damage) {
    this.damage = damage;
  }

  public void takeDamage(int damage) {
    if (damage < hp) {
      hp -= damage;
    } else {
      alive = false;
      hp = 0;
    }
  }

  public void setHp(int hp) {
    this.hp = hp;
  }

  public boolean isAlive() {
    return alive;
  }

  public void setAlive(boolean alive) {
    this.alive = alive;
  }

  public abstract String getTexture();

  public void setBlock(Block block) {
    onBlock = block;
  }

  public Block getBlock() {
    return onBlock;
  }
}
